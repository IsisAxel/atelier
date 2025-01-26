package mg.atelier.controller;


import mg.atelier.model.TechnicienCommission;
import mg.atelier.model.Genre;
import mg.atelier.service.TechnicienCommissionService;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/techniciens")
public class TechnicienCommissionController {

    @Autowired
    private final TechnicienCommissionService technicienCommissionService;

    public TechnicienCommissionController(TechnicienCommissionService technicienCommissionService) {
        this.technicienCommissionService = technicienCommissionService;
    }

    @GetMapping("/all")
    public String getAll(Model model) {
        LocalDate start = LocalDate.of(1000, Month.JANUARY, 1);
        LocalDate end = LocalDate.of(3030, Month.DECEMBER, 31);
        List<TechnicienCommission> tus= technicienCommissionService.getTechnicienCommissions(start,end);
        model.addAttribute("techniciensCommission", tus);
        return "technicien/list";
    }
    @PostMapping("/filter")
    public String getTechniciensByDate(
        @RequestParam(value="startDate" , required=false)LocalDate startDate,
        @RequestParam(value="endDate" , required = false)LocalDate endDate,
        int idReparateur,
        Model model
    ) {
        if (startDate == null) {
            startDate = LocalDate.of(1000, Month.JANUARY, 1);
        }
        if (endDate == null) {
            endDate = LocalDate.of(3030, Month.DECEMBER, 31);
        }
        List<TechnicienCommission> filteredTechniciens = technicienCommissionService.getTechnicienCommissions(startDate, endDate);
        if (idReparateur > 0) {
            filteredTechniciens = filteredTechniciens.stream()
            .filter(technicien -> technicien.getIdTechnicien() == idReparateur)
            .collect(Collectors.toList());
        }
        model.addAttribute("techniciensCommission", filteredTechniciens);
        return "technicien/list";
    }
    @PostMapping("/filter/genre")
    public String getCommissionsByDate(
        @RequestParam(value="start" , required=false)LocalDate start,
        @RequestParam(value="end" , required = false)LocalDate end,
        Model model,
        int idGenre
    ) {
       if (start == null) {
            start = LocalDate.of(1000, Month.JANUARY, 1);
        }
        if (end == null) {
            end = LocalDate.of(3030, Month.DECEMBER, 31);
        }
        List<TechnicienCommission> filteredTechniciens = technicienCommissionService.getTechnicienCommissions(start, end);
        TechnicienCommission reste=new TechnicienCommission();
        double total=0;
        double alltotal=0;
        for(TechnicienCommission tc : filteredTechniciens ){
            if(tc.getGenre().getIdGenre()==idGenre){
                total+=tc.getCommission();
            }
            alltotal+=tc.getCommission();
        }
        if(idGenre==1){
            Genre g=new Genre();
            g.setGenre("homme");
            reste.setGenre(g);
            reste.setCommission(total);
        }else if(idGenre==2){
            Genre g=new Genre();
            g.setGenre("femme");
            reste.setGenre(g);
            reste.setCommission(total);
        }else{
            Genre g=new Genre();
            g.setGenre("tous");
            reste.setGenre(g);
            reste.setCommission(alltotal);

        }
        model.addAttribute("etat", reste);
        return "technicien/genre";
    }
    @GetMapping("/filter/genre/all")
    public String getCommissions(
        Model model
    ) {
        LocalDate start = LocalDate.of(1000, Month.JANUARY, 1);
        LocalDate end = LocalDate.of(3030, Month.DECEMBER, 31);
        List<TechnicienCommission> filteredTechniciens = technicienCommissionService.getTechnicienCommissions(start, end);
        TechnicienCommission reste=new TechnicienCommission();
        double total=0;
        double alltotal=0;
        for(TechnicienCommission tc : filteredTechniciens ){
            alltotal+=tc.getCommission();
        }

            Genre g=new Genre();
            g.setGenre("tous");
            reste.setGenre(g);
            reste.setCommission(alltotal);
        model.addAttribute("etat", reste);
        return "technicien/genre";
    }
}
