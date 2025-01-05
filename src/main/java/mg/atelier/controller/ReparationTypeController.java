package mg.atelier.controller;

import mg.atelier.model.ReparationType;
import mg.atelier.model.ReparationTypePrice;
import mg.atelier.service.ReparationTypePriceService;
import mg.atelier.service.ReparationTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.ServletContext;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

@Controller
@RequestMapping("/reparationType")
public class ReparationTypeController {
    @Autowired
    private final ServletContext servletContext;
    @Autowired
    private ReparationTypeService reparationTypeService;
    @Autowired
    private ReparationTypePriceService reparationTypePriceService;

    public ReparationTypeController(ServletContext servletContext, ReparationTypeService reparationTypeService,
            ReparationTypePriceService reparationTypePriceService) {
        this.servletContext = servletContext;
        this.reparationTypeService = reparationTypeService;
        this.reparationTypePriceService = reparationTypePriceService;
    }

    @GetMapping("/all")
    public String getAllReparationTypes(Model model) {
        return "reparationType/list";
    }

    @GetMapping("/prepareInsert")
    public String prepareInsertReparationType(Model model) {
        return "reparationType/insert";
    }

    @PostMapping("/insert")
    public String insertReparationType(@ModelAttribute ReparationType reparationType , @RequestParam("price") double price , @RequestParam("effectiveDate") LocalDateTime date, @RequestParam("durationHours") int durationHours, @RequestParam("durationMinutes") int durationMinutes, Model model) {
        try {
            long durationSeconds = Duration.ofHours(durationHours).plusMinutes(durationMinutes).getSeconds();
            reparationType.setDuration(durationSeconds);
            reparationType = reparationTypeService.createReparationType(reparationType);
            ReparationTypePrice reparationTypePrice = new ReparationTypePrice(0, reparationType, price, date);
            reparationTypePriceService.createReparationTypePrice(reparationTypePrice);
            model.addAttribute("success", "Type de reparation insere avec succes");
            List<ReparationTypePrice> reparationTypes = reparationTypePriceService.getReparationTypePriceByDate(LocalDateTime.now());
            servletContext.setAttribute("reparationTypes", reparationTypes);
            return "reparationType/list";
        } catch (Exception e) {
            model.addAttribute("error", "Erreur : " + e.getMessage());
            return "reparationType/insert";
        }
    }

    @GetMapping("/prepareUpdatePrice")
    public String prepareUpdatePrice(@RequestParam("id") int id, @RequestParam("price") double price, Model model) {
        ReparationTypePrice reparationTypePrice = new ReparationTypePrice();
        reparationTypePrice.setIdReparationTypePrice(id);
        reparationTypePrice.setPrice(price);
        model.addAttribute("reparationTypePrice", reparationTypePrice);
        return "reparationType/update";
    }

    @PostMapping("/updatePrice")
    public String updatePrice(@RequestParam("id") int id, @RequestParam("price") double price, Model model) {
        try {
            reparationTypePriceService.updateReparationTypePrice(id,price);
            model.addAttribute("success", "Prix mis a jour avec succes");
            List<ReparationTypePrice> reparationTypes = reparationTypePriceService.getReparationTypePriceByDate(LocalDateTime.now());
            servletContext.setAttribute("reparationTypes", reparationTypes);
            return "reparationType/list";
        } catch (Exception e) {
            model.addAttribute("error", "Erreur : " + e.getMessage());
            return "reparationType/update";
        }
    }
}