package mg.atelier.controller;

import mg.atelier.model.*;
import mg.atelier.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.ServletContext;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/reparation")
public class ReparationController {

    @Autowired
    private ComputerService computerService;
    @Autowired
    private ComponentService componentService;

    @Autowired
    private ReparationService reparationService;

    @Autowired
    private ReparationDetailService reparationDetailService;

    @Autowired
    private ReparationTypeService reparationTypeService;

    @Autowired
    private ComputerComponentService computerComponentService;

    @Autowired
    private ReparationTypePriceService reparationTypePriceService;
    @Autowired
    private final ServletContext servletContext;

    public ReparationController(ComputerService computerService, ComponentService componentService,
            ReparationService reparationService, ReparationDetailService reparationDetailService,
            ReparationTypeService reparationTypeService, ComputerComponentService computerComponentService,
            ReparationTypePriceService reparationTypePriceService, ServletContext servletContext) {
        this.computerService = computerService;
        this.componentService = componentService;
        this.reparationService = reparationService;
        this.reparationDetailService = reparationDetailService;
        this.reparationTypeService = reparationTypeService;
        this.computerComponentService = computerComponentService;
        this.reparationTypePriceService = reparationTypePriceService;
        this.servletContext = servletContext;
    }

    @GetMapping("/all")
    public String getAllReparations(Model model) {
        return "reparation/list";
    }

    @GetMapping("/prepareInsert")
    public String prepareInsertReparation(Model model) {
        return "reparation/insert";
    }

    @GetMapping("/details/{id}")
    public String getReparationDetails(@PathVariable("id") int id, Model model) {
        try {
            Reparation reparation = reparationService.getReparationById(id);
            List<ReparationDetail> reparationDetails = reparationDetailService.getByReparationDetailsByReparation(reparation);
            model.addAttribute("reparation", reparation);
            model.addAttribute("reparationDetails", reparationDetails);
            return "reparation/details";        
        } catch (Exception e) {
            model.addAttribute("error", "Erreur : " + e.getMessage());
            return "reparation/list";
        }
    }

    @PostMapping("/insert")
    public String insertReparation(@ModelAttribute Computer computer,
                                   @ModelAttribute ReparationForm reparationForm,
                                   Model model) {
        try {
            LocalDateTime date = reparationForm.getStartDate();
            LocalDateTime initialDate = reparationForm.getStartDate();
            double amount = 0.0;

            HashMap<ComponentStock, Integer> mapCheck = new HashMap<>();

            for (int idReparationDetail : reparationForm.getReparations()) {
                ReparationTypePrice reparationTypePrice = reparationTypePriceService.getReparationTypePriceByIdReparationTypeAndDate(idReparationDetail, initialDate);
                date = date.plusSeconds(reparationTypePrice.getReparationType().getDuration());
                amount += reparationTypePrice.getPrice();
                ComponentStock componentStock = componentService.getComponentStockById(reparationTypePrice.getReparationType().getComponent().getIdComponent());
                if (mapCheck.containsKey(componentStock)) {
                    mapCheck.put(componentStock, mapCheck.get(componentStock) + 1);
                } else {
                    mapCheck.put(componentStock, 1);
                }
            }

            
            for (Map.Entry<ComponentStock, Integer> entry : mapCheck.entrySet()) {
                if (entry.getValue() > entry.getKey().getStock()) {
                    throw new Exception("Stock insuffisant pour le composant: " + entry.getKey().getName());
                }
            }

            computer = computerService.createComputer(computer);
    
            for (ComputerComponent component : reparationForm.getComponents()) {
                component.setComputer(computer);
                computerComponentService.createComputerComponent(component);
            }



            Reparation reparation = new Reparation();
            reparation.setComputer(computer);
            reparation.setStartDate(date);


            reparation.setEndDate(date);
            reparation.setTotalAmount(amount);
            reparation = reparationService.createReparation(reparation);
            
            for (int idReparationDetail : reparationForm.getReparations()) {
                ReparationDetail reparationDetail = new ReparationDetail();

                ReparationTypePrice reparationTypePrice = reparationTypePriceService.getReparationTypePriceByIdReparationTypeAndDate(idReparationDetail, initialDate);

                reparationDetail.setReparationType(reparationTypeService.getReparationTypeById(idReparationDetail));
                reparationDetail.setReparation(reparation);
                reparationDetail.setPrice(reparationTypePrice.getPrice());
                reparationDetailService.createReparationDetail(reparationDetail);
            }
    
            model.addAttribute("success", "Reparation ajoutee avec succes");
            List<Reparation> reparations = reparationService.getAllReparations();
            servletContext.setAttribute("reparations", reparations);
            List<ComponentStock> components = componentService.getAllComponentStocks();
            servletContext.setAttribute("components", components);
            return "/reparation/list";
        } catch (Exception e) {
            model.addAttribute("error", "Erreur : " + e.getMessage());
            return "reparation/insert";
        }
    }
}