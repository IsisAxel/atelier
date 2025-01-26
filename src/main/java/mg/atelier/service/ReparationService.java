package mg.atelier.service;

import mg.atelier.model.Reparation;
import mg.atelier.repository.ReparationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mg.atelier.exception.EntityNotFoundException;

import java.time.LocalDate;
import java.util.List;

@Service
public class ReparationService {

    @Autowired
    private ReparationRepository reparationRepository;

    public Reparation createReparation(Reparation reparation) {
        return reparationRepository.save(reparation);
    }

    public List<Reparation> getAllReparations() {
        return reparationRepository.findAll();
    }

    public Reparation getReparationById(int id) throws EntityNotFoundException{
        return reparationRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Reparation"));
    }
    public List<Reparation> getReparationsByDate(LocalDate date) {
        return reparationRepository.findByStartDate(date);
    }
}