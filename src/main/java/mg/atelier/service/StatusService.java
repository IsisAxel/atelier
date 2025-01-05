package mg.atelier.service;

import mg.atelier.model.Status;
import mg.atelier.repository.StatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StatusService {

    @Autowired
    private StatusRepository statusRepository;

    public List<Status> getAllStatuses() {
        return statusRepository.findAll();
    }

    public Status getStatusByStatus(String name) {
        return statusRepository.findByStatus(name);
    }

    public Status getNormalStatus() {
        return getStatusByStatus("normal");
    }

    public Status getAnormalStatus() {
        return getStatusByStatus("anormal");
    }
}