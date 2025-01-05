package mg.atelier.controller;

import mg.atelier.model.Achat;
import mg.atelier.model.Component;
import mg.atelier.model.ComponentStock;
import mg.atelier.service.AchatService;
import mg.atelier.service.ComponentService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.ServletContext;

@Controller
@RequestMapping("/achat")
public class AchatController {
    @Autowired
    private final ServletContext servletContext;
    @Autowired
    private AchatService achatService;

    @Autowired
    private ComponentService componentService;

    public AchatController(ServletContext servletContext, AchatService achatService,
            ComponentService componentService) {
        this.servletContext = servletContext;
        this.achatService = achatService;
        this.componentService = componentService;
    }

    @GetMapping("/prepareInsert")
    public String prepareInsertAchat(@ModelAttribute("idComponent") int idComponent, Model model) {
        try {
            Component component = componentService.getComponentById(idComponent);
            model.addAttribute("component", component);
            return "component/achat";   
        } catch (Exception e) {
            model.addAttribute("error", "Erreur : " + e.getMessage());
            return "component/list";
        }
    }

    @PostMapping("/insert")
    public String createAchat(@ModelAttribute Achat achat, @RequestParam("idComponent") int idComponent, Model model) {
        try {
            Component component = componentService.getComponentById(idComponent);
            achat.setComponent(component);
            achatService.createAchat(achat);
            model.addAttribute("success", "Achat ajouté avec succès");
            List<ComponentStock> components = componentService.getAllComponentStocks();
            servletContext.setAttribute("components", components);
            return "component/list"; 
        } catch (Exception e) {
            model.addAttribute("error", "Erreur : " + e.getMessage());
            return "component/achat";
        }
    }
}