package mg.atelier.service;

import mg.atelier.model.Achat;
import mg.atelier.repository.AchatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AchatService {

    @Autowired
    private AchatRepository achatRepository;

    public List<Achat> getAllAchats() {
        return achatRepository.findAll();
    }

    public Achat createAchat(Achat achat) {
        return achatRepository.save(achat);
    }
}