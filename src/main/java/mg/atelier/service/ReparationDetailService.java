package mg.atelier.service;

import mg.atelier.model.Reparation;
import mg.atelier.model.ReparationType;
import mg.atelier.model.ReparationDetail;
import mg.atelier.repository.ReparationDetailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.persistence.EntityNotFoundException;

import java.util.List;

@Service
public class ReparationDetailService {

    @Autowired
    private ReparationDetailRepository reparationDetailRepository;

    public ReparationDetail createReparationDetail(ReparationDetail reparationDetail) {
        return reparationDetailRepository.save(reparationDetail);
    }

    public List<ReparationDetail> getAllReparationDetails() {
        return reparationDetailRepository.findAll();
    }

    public List<ReparationDetail> getByReparationDetailsByReparation(Reparation reparation) {
        return reparationDetailRepository.findByReparation(reparation);
    }
    public List<ReparationDetail> getByReparationDetailsByReparationType(ReparationType reparationType) {
        return reparationDetailRepository.findByReparationType(reparationType);
    }


    public ReparationDetail getReparationDetailById(int id) {
        return reparationDetailRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Detail de reparation"));
    }
}