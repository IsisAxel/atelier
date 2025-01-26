package mg.atelier.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mg.atelier.model.RoleTechnicien;
import mg.atelier.repository.RoleTechnicienRepository;

import java.util.List;
import java.util.Optional;

@Service
public class RoleTechnicienService {
    
    @Autowired
    private RoleTechnicienRepository roleTechnicienRepository;

    public List<RoleTechnicien> getAll() {
        return roleTechnicienRepository.findAll();
    }

    public Optional<RoleTechnicien> getById(int id) {
        return roleTechnicienRepository.findById(id);
    }
}
