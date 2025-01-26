package mg.atelier.controller;

import java.lang.ProcessBuilder.Redirect;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import mg.atelier.model.Achat;
import mg.atelier.model.Component;
import mg.atelier.model.ComponentStock;
import mg.atelier.model.Recommendation;
import mg.atelier.service.RecommendationService;

@Controller
@RequestMapping("/recommendation")
public class RecommendationController {
    @Autowired
    private RecommendationService recommendationService;

    @PostMapping("/insert")
    public String createAchat(Model model,@RequestParam LocalDateTime date,@RequestParam int idComponent) {
        try {
        Component cp=new Component();
        cp.setIdComponent(idComponent);
        Recommendation recommendation=new Recommendation(cp,date);
        recommendationService.createRecommendation(recommendation);
        model.addAttribute("sucess","insertion reussi");
        return "redirect:/recommendation/all";
        } catch (Exception e) {
            model.addAttribute("error", "Erreur : " + e.getMessage());
            return "recommendation/insert";
        }
    }
    @GetMapping("/prepareInsert")
    public String prepareInsert(Model model) {
        return "recommendation/insert";
    }

    @GetMapping("/all")
    public String getAll(Model model) {
     List<Recommendation> rec= recommendationService.getAllRecommendations(); 
     model.addAttribute("recommendations",rec);
        return "recommendation/list";
    }

    @PostMapping("/recherche")
    public String Recherche(Model model,@RequestParam LocalDateTime date,int year) {
        List<Recommendation> rec=null;
        if(year>0){
            rec= recommendationService.getAllRecommendations(year); 
        }else{
            rec= recommendationService.getAllRecommendations(date); 
        }
        model.addAttribute("recommendations",rec);
        return "recommendation/list";
    }
    
}
