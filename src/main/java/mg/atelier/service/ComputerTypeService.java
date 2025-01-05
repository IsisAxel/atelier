package mg.atelier.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mg.atelier.exception.EntityNotFoundException;
import mg.atelier.model.ComputerType;
import mg.atelier.repository.ComputerTypeRepository;

@Service
public class ComputerTypeService {
    @Autowired
    private ComputerTypeRepository computerTypeRepository;

    public List<ComputerType> getAllComputerTypes() {
        return computerTypeRepository.findAll();
    }

    public List<ComputerType> getComputerTypeByType(String type) {
        return computerTypeRepository.findByType(type);
    }

    public ComputerType getComputerTypeById(int id) throws EntityNotFoundException{
        return computerTypeRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Type d'ordinateur"));
    }
}