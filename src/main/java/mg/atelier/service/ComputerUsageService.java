
package mg.atelier.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mg.atelier.exception.EntityNotFoundException;
import mg.atelier.model.ComputerUsage;
import mg.atelier.repository.ComputerUsageRepository;

@Service
public class ComputerUsageService {
    @Autowired
    private ComputerUsageRepository computerUsageRepository;

    public List<ComputerUsage> getAllComputerUsages() {
        return computerUsageRepository.findAll();
    }

    public ComputerUsage getComputerUsageById(int id) throws EntityNotFoundException{
        return computerUsageRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Usage d'ordinateur"));
    }
}