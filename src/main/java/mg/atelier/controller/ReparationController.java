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
import java.util.ArrayList;
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
    private ComputerUsageService computerUsageService;

    @Autowired
    private ComputerComponentService computerComponentService;

    @Autowired
    private ReparationTypePriceService reparationTypePriceService;
    @Autowired
    private final ServletContext servletContext;

    public ReparationController(ComputerService computerService, ComponentService componentService,
            ReparationService reparationService, ReparationDetailService reparationDetailService,
            ReparationTypeService reparationTypeService, ComputerComponentService computerComponentService,
            ReparationTypePriceService reparationTypePriceService, ServletContext servletContext,ComputerUsageService computerUsageService) {
        this.computerService = computerService;
        this.componentService = componentService;
        this.reparationService = reparationService;
        this.reparationDetailService = reparationDetailService;
        this.reparationTypeService = reparationTypeService;
        this.computerComponentService = computerComponentService;
        this.reparationTypePriceService = reparationTypePriceService;
        this.servletContext = servletContext;
        this.computerUsageService=computerUsageService;
    }

    @GetMapping("/all")
    public String getAllReparations(Model model) {
        return "reparation/list";
    }
    @GetMapping("/updateList")
    public String updateList(Model model) {
        model.addAttribute("success", "Reparation ajoutee avec succes");
        List<Reparation> reparations = reparationService.getAllReparations();
        servletContext.setAttribute("reparations", reparations);
        List<ComponentStock> components = componentService.getAllComponentStocks();
        servletContext.setAttribute("components", components);
        return "/reparation/list";
    }

    @GetMapping("/prepareInsert")
    public String prepareInsertReparation(Model model) {
        return "reparation/insert";
    }

    @GetMapping("/details/{id}")
    public String getReparationDetails(@PathVariable("id") int id, @RequestParam String typeReturn, Model model) {
        try {
            Reparation reparation = reparationService.getReparationById(id);
            List<ReparationDetail> reparationDetails = reparationDetailService.getByReparationDetailsByReparation(reparation);
            model.addAttribute("reparation", reparation);
            model.addAttribute("reparationDetails", reparationDetails);
            model.addAttribute("typeReturn", typeReturn);
            return "reparation/details";        
        } catch (Exception e) {
            model.addAttribute("error", "Erreur : " + e.getMessage());
            return "reparation/list";
        }
    }

    @PostMapping("/insert")
    public String insertReparation(@ModelAttribute Computer computer,
                                   @ModelAttribute ReparationForm reparationForm,
                                   Model model,@ModelAttribute Technicien technicien) {
        try {
            LocalDateTime date = reparationForm.getStartDate();
            LocalDateTime initialDate = reparationForm.getStartDate();
            double amount = 0.0;

            Reparation reparation = new Reparation();
            reparation.setStartDate(date);

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
            reparation.setComputer(computer);
            computer = computerService.createComputer(computer);
    
            for (ComputerComponent component : reparationForm.getComponents()) {
                component.setComputer(computer);
                computerComponentService.createComputerComponent(component);
            }



          


            reparation.setEndDate(date);
            reparation.setTotalAmount(amount);
            reparation.setTechnicien(technicien);
            reparation = reparationService.createReparation(reparation);
            
            for (int idReparationDetail : reparationForm.getReparations()) {
                ReparationDetail reparationDetail = new ReparationDetail();

                ReparationTypePrice reparationTypePrice = reparationTypePriceService.getReparationTypePriceByIdReparationTypeAndDate(idReparationDetail, initialDate);

                reparationDetail.setReparationType(reparationTypeService.getReparationTypeById(idReparationDetail));
                reparationDetail.setReparation(reparation);
                reparationDetail.setPrice(reparationTypePrice.getPrice());
                reparationDetailService.createReparationDetail(reparationDetail);
            }
    
            // model.addAttribute("success", "Reparation ajoutee avec succes");
            // List<Reparation> reparations = reparationService.getAllReparations();
            // servletContext.setAttribute("reparations", reparations);
            // List<ComponentStock> components = componentService.getAllComponentStocks();
            // servletContext.setAttribute("components", components);
            return "redirect:/reparation/updateList";
        } catch (Exception e) {
            model.addAttribute("error", "Erreur : " + e.getMessage());
            return "reparation/insert";
        }
    }
    
    @PostMapping("/recherche")
    public String rechercheType(Model model,int idReparationTypePrice,int idComputerUsage) {
        ReparationTypePrice reparationTypePrice=null;
        try {
         reparationTypePrice=reparationTypePriceService.getReparationTypePriceById(idReparationTypePrice); 
        } catch (Exception e) {
            idReparationTypePrice=0;
        }
        try {
            ComputerUsage computerUsage= computerUsageService.getComputerUsageById(idComputerUsage);
            
        } catch (Exception e) {
            idComputerUsage=0;
        }
        List<ReparationDetail> reparationDetails=null;
        if( idReparationTypePrice==0){
            reparationDetails = reparationDetailService.getAllReparationDetails();
        }else{
             reparationDetails = reparationDetailService.getByReparationDetailsByReparationType(reparationTypePrice.getReparationType());
        }
        
        List<Reparation> reparations= new ArrayList<>() ;
        for (ReparationDetail rep : reparationDetails) {
           Reparation tmp=rep.getReparation();
           if(!reparations.contains(tmp)){
            if(rep.getReparation().getComputer().getComputerUsage().getIdComputerUsage()==idComputerUsage || idComputerUsage==0){

                reparations.add(tmp);
            }
           }
        }
        model.addAttribute("reparationFiltre", reparations);
        return "reparation/list";
    }
    // @PostMapping("/search")
    // public String searchType(Model model,int idComponent) {
    //     Component comp=new Component();
    //     comp.setIdComponent(idComponent);
    //     ReparationType reparation=reparationTypeService.getReparationTypeByComponent(comp);
    //     List<ReparationDetail> reparationDetails = reparationDetailService.getByReparationDetailsByReparationType(reparation);
    //     List<Reparation> reparations= new ArrayList<>() ;
    //     for (ReparationDetail rep : reparationDetails) {
    //        Reparation tmp=rep.getReparation();
    //        if(!reparations.contains(tmp)){
    //         if(rep.getReparationType().getComponent().getIdComponent()==reparation.getComponent().getIdComponent())
    //        {
    //         reparations.add(tmp);
    //        }
    //       }
    //     }
    //     model.addAttribute("reparationFiltre", reparations);
    //     return "reparation/list";
    // }
}