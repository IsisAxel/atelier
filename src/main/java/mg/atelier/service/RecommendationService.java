package mg.atelier.service;

import mg.atelier.model.Recommendation;
import mg.atelier.repository.RecommendationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class RecommendationService {

    @Autowired
    private RecommendationRepository recommendationRepository;

    public List<Recommendation> getAllRecommendations() {
        return recommendationRepository.findAll();
    }

    public Recommendation createRecommendation(Recommendation Recommendation) {
        return recommendationRepository.save(Recommendation);
    }
    public List<Recommendation> getAllRecommendations(LocalDateTime date) {
        List<Recommendation> rec= recommendationRepository.findAll();
        List<Recommendation> rec1=new ArrayList<Recommendation>();
        int year=date.getYear();
        int mois=date.getMonthValue();
       for (Recommendation r : rec) {
        if(r.getPeriod().getYear()==year && r.getPeriod().getMonthValue()==mois){
            rec1.add(r);
        }
        
       }
       return rec1;
    }
    public List<Recommendation> getAllRecommendations(int year) {
        List<Recommendation> rec= recommendationRepository.findAll();
        List<Recommendation> rec1=new ArrayList<Recommendation>();
       for (Recommendation r : rec) {
        if(r.getPeriod().getYear()==year){
            rec1.add(r);
        }
        
       }
       return rec1;
    }
}