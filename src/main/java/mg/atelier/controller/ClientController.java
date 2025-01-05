package mg.atelier.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.ServletContext;
import mg.atelier.model.Clients;
import mg.atelier.service.ClientsService;

@Controller
@RequestMapping("/client")
public class ClientController 
{
    @Autowired
    private final ServletContext servletContext;
    @Autowired
    private ClientsService clientsService;

    public ClientController(ServletContext servletContext, ClientsService clientsService) {
        this.servletContext = servletContext;
        this.clientsService = clientsService;
    }

    @GetMapping("/all")
    public String getAllClients(Model model) {
        return "client/list";
    }

    @GetMapping("/prepareInsert")
    public String prepareInsertClient(Model model) {
        return "client/insert";
    }

    @PostMapping("/insert")
    public String insertClient(@ModelAttribute Clients client, Model model) {
        try {
            Clients createdClient = clientsService.createClient(client);
            model.addAttribute("client", createdClient);
            model.addAttribute("success", "Client inserer avec succes");
            List<Clients> clients = clientsService.getAllClients();
            servletContext.setAttribute("clients", clients);
            return "client/list";
        } catch (Exception e) {
            model.addAttribute("error", "Erreur : " + e.getMessage());
            return "client/insert";
        }
    }
}