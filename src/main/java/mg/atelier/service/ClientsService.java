package mg.atelier.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import mg.atelier.exception.EntityNotFoundException;
import mg.atelier.exception.NotNullException;
import mg.atelier.exception.UniqueException;
import mg.atelier.model.Clients;
import mg.atelier.repository.ClientsRepository;

@Service
public class ClientsService {

    @Autowired
    private ClientsRepository clientsRepository;

    private void checkNotNull(Clients client) throws NotNullException {
        if (client.getName() == null || client.getName().trim().isEmpty()) {
            throw new NotNullException("nom");
        }
        if (client.getEmail() == null || client.getEmail().trim().isEmpty()) {
            throw new NotNullException("email");
        }
    }

    public Clients createClient(Clients client) throws UniqueException , NotNullException {
        try {
            checkNotNull(client);
            return clientsRepository.save(client);
        } catch (DataIntegrityViolationException e) {
            throw new UniqueException("email",client.getEmail());
        }
    }

    public List<Clients> getAllClients() {
        return clientsRepository.findAll();
    }

    public Clients getClientById(int id) throws EntityNotFoundException {
        return clientsRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Client"));
    }

    public Clients updateClient(int id, Clients clientDetails) throws UniqueException , NotNullException , EntityNotFoundException{
        Clients client = clientsRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Client"));
        client.setName(clientDetails.getName());
        client.setEmail(clientDetails.getEmail());
        try {
            checkNotNull(client);
            return clientsRepository.save(client);
        } catch (DataIntegrityViolationException e) {
            throw new UniqueException("email",client.getEmail());
        }
    }
}