package mg.atelier.controller;

import java.time.LocalDate;
import java.util.ArrayList;
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
import mg.atelier.model.Clients;
import mg.atelier.model.Reparation;
import mg.atelier.service.ClientsService;
import mg.atelier.service.ReparationService;

@Controller
@RequestMapping("/client")
public class ClientController 
{
    @Autowired
    private final ServletContext servletContext;
    @Autowired
    private ClientsService clientsService;
    @Autowired
    private ReparationService reparationService;


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
            // model.addAttribute("client", createdClient);
            model.addAttribute("success", "Client inserer avec succes");
            List<Clients> clients = clientsService.getAllClients();
            servletContext.setAttribute("clients", clients);
            return "redirect:/client/all";
        } catch (Exception e) {
            model.addAttribute("error", "Erreur : " + e.getMessage());
            return "client/insert";
        }
    }
    @PostMapping("/recherche")
    public String Recherche(Model model,@RequestParam LocalDate date) {
        List<Clients> clients=new ArrayList<>();
        List<Reparation> reparations=reparationService.getReparationsByDate(date);

    for (Reparation reparation : reparations) {
        Clients client = reparation.getComputer().getClient();
        if (!clients.contains(client)) {
            clients.add(client); 
        }
    }
        model.addAttribute("clients",clients);
        return "client/list";
    }
}