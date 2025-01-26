package mg.atelier.controller;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import mg.atelier.service.ComputerUsageService;
import mg.atelier.service.ReparationDetailService;
import mg.atelier.service.ReparationTypePriceService;
import mg.atelier.service.ReturnService;
import mg.atelier.model.ComputerUsage;
import mg.atelier.model.Reparation;
import mg.atelier.model.ReparationDetail;
import mg.atelier.model.ReparationTypePrice;
import mg.atelier.model.Return;

@Controller
@RequestMapping("/return")
public class ReturnController 
{
    @Autowired
    private ReturnService returnService;
    @Autowired
    private ReparationDetailService reparationDetailService;
    @Autowired
    private ReparationTypePriceService reparationTypePriceService;
    @Autowired
    private ComputerUsageService computerUsageService;
    
    @GetMapping("/all")	
    public String getAllReturn(Model model) {
        List<Return> returns = returnService.getAllReturn();
        model.addAttribute("returns",returns);
        return "return/list";
    }

    @GetMapping("/prepareInsert/{id}")
    public String prepareInsertReturn(@PathVariable("id") int id, Model model) {
        model.addAttribute("idReparation",id);
        return "reparation/insertReturn";
    }

    @PostMapping("/insert")
    public String insertReturn(@RequestParam int idReparation , @RequestParam LocalDateTime returnDate , Model model) {
        try {
            Reparation reparation = new Reparation(idReparation);
            Return return1 = returnService.getReturnByReparation(reparation);
            if (return1 == null || return1.getIdReturn() == 0) {
                Return returnn = new Return(returnDate, reparation);
                returnService.createReturn(returnn);
                List<Return> returns = returnService.getAllReturn();
                model.addAttribute("returns",returns);
                model.addAttribute("success","Retour inserer avec succes");
                return "reparation/list";
            } 
            throw new Exception("Reparation deja inserer dans retour");
        } catch (Exception e) {
            model.addAttribute("error",e.getMessage());
            model.addAttribute("idReparation",idReparation);
            return "reparation/insertReturn";
        }
    }

    @PostMapping("/recherche")
    public String rechercheType(Model model,int idReparationTypePrice,int idComputerUsage) 
    {
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
        
        List<Return> returns = returnService.getAllReturnByComputerUsageAndReparationType(reparationDetails,idComputerUsage);
        model.addAttribute("returns", returns);
        return "return/list";
    }
}