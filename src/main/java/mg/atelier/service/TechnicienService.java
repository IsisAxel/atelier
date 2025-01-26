package mg.atelier.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mg.atelier.exception.EntityNotFoundException;
import mg.atelier.model.Technicien;
import mg.atelier.repository.TechnicienRepository;

@Service
public class TechnicienService {
    @Autowired
    private TechnicienRepository technicienRepository;

    public List<Technicien> getAllTechniciens() {
        return technicienRepository.findAll();
    }

    public Technicien getTechnicienById(int id) throws EntityNotFoundException {
        return technicienRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Technicien"));
    }
}