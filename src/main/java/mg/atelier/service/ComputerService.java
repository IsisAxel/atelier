package mg.atelier.service;

import mg.atelier.exception.NotNullException;
import mg.atelier.model.Computer;
import mg.atelier.repository.ComputerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;

import java.util.List;

@Service
public class ComputerService {

    @Autowired
    private ComputerRepository computerRepository;

    private void checkNotNull(Computer computer) throws NotNullException {
        if (computer.getClient() == null || computer.getClient().getIdClient() == 0) {
            throw new NotNullException("Client");   
        }
        if (computer.getBrand() == null || computer.getBrand().getIdBrand() == 0) {
            throw new NotNullException("Marque");
        }
        if (computer.getComputerType() == null || computer.getComputerType().getIdComputerType() == 0) {
            throw new NotNullException("Type d'ordinateur");
        }
        if (computer.getModel() == null || computer.getModel().trim().isEmpty()) {
            throw new NotNullException("Modele");
        }
    }

    public Computer createComputer(Computer computer) throws NotNullException{
        checkNotNull(computer);
        return computerRepository.save(computer);
    }

    public List<Computer> getAllComputers() {
        return computerRepository.findAll();
    }

    public Computer getById(int id) {
        return computerRepository.findById(id).orElse(null);
    }

    @Transactional
    public void flush() {
        computerRepository.flush();
    }
}