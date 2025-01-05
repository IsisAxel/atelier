package mg.atelier.service;

import mg.atelier.model.ComputerComponent;
import mg.atelier.repository.ComputerComponentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ComputerComponentService {

    @Autowired
    private ComputerComponentRepository computerComponentRepository;

    public ComputerComponent createComputerComponent(ComputerComponent computerComponent) {
        return computerComponentRepository.save(computerComponent);
    }

    public List<ComputerComponent> getAllComputerComponents() {
        return computerComponentRepository.findAll();
    }

    public ComputerComponent getById(int id) {
        return computerComponentRepository.findById(id).orElse(null);
    }
}