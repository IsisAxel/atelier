package mg.atelier.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.ServletContext;
import mg.atelier.model.Component;
import mg.atelier.model.ComponentStock;
import mg.atelier.model.ComponentType;
import mg.atelier.model.ComputerType;
import mg.atelier.service.ComponentService;
import mg.atelier.service.ComponentTypeService;
import mg.atelier.service.ComputerTypeService;

@Controller
@RequestMapping("/component")
public class ComponentController {
    @Autowired
    private final ServletContext servletContext;
    @Autowired
    private ComponentService componentService;

    @Autowired
    private ComponentTypeService componentTypeService;

    @Autowired
    private ComputerTypeService computerTypeService;

    public ComponentController(ServletContext servletContext, ComponentService componentService,
            ComponentTypeService componentTypeService, ComputerTypeService computerTypeService) {
        this.servletContext = servletContext;
        this.componentService = componentService;
        this.componentTypeService = componentTypeService;
        this.computerTypeService = computerTypeService;
    }

    @GetMapping("/all")
    public String getAllComponents(Model model) {
        return "component/list"; 
    }

    // @GetMapping("/prepareUpdate/{idComponent}")
    // public String prepareUpdateComponent(@PathVariable("idComponent") int idComponent, Model model) {
    //     try {
    //         Component component = componentService.getComponentById(idComponent);
    //         List<ComputerType> computerTypes = computerTypeService.getAllComputerTypes();
    //         List<ComponentType> componentTypes = componentTypeService.getAllComponentTypes();

    //         model.addAttribute("component", component);
    //         model.addAttribute("computerTypes", computerTypes);
    //         model.addAttribute("componentTypes", componentTypes);
    //     } catch (Exception e) {
    //         model.addAttribute("error", "Erreur : " + e.getMessage());
    //     }
    //     return "updateComponent"; 
    // }

    // @PostMapping("/update")
    // public String updateComponent(@ModelAttribute Component component, Model model) {
    //     try {
    //         componentService.updateComponent(component.getIdComponent() , component);
    //         model.addAttribute("success", "Composant mis a jour avec succes");
    //         List<Component> components = (List<Component>) servletContext.getAttribute("components");
    //         model.addAttribute("components", components);
    //     } catch (Exception e) {
    //         model.addAttribute("error", "Erreur : " + e.getMessage());
    //     }
    //     return "component/list"; 
    // }

    @GetMapping("/prepareInsert")
    public String prepareInsertComponent(Model model) {
        return "component/insert"; 
    }

    @PostMapping("/insert")
    public String insertComponent(@ModelAttribute Component component , Model model) {
        try {
            componentService.createComponent(component);
            model.addAttribute("success", "Composant insere avec succes");
            List<ComponentStock> components = componentService.getAllComponentStocks();
            servletContext.setAttribute("components", components);
            return "component/list"; 
        } catch (Exception e) {
            model.addAttribute("error", "Erreur : " + e.getMessage());
            return "component/insert"; 
        }
    }
}
