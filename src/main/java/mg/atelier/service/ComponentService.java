package mg.atelier.service;

import java.util.List;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import mg.atelier.exception.EntityNotFoundException;
import mg.atelier.exception.NotNullException;
import mg.atelier.exception.UniqueException;
import mg.atelier.model.Component;
import mg.atelier.model.ComponentStock;
import mg.atelier.repository.ComponentRepository;
import mg.atelier.repository.ComponentStockRepository;

@Service
public class ComponentService
{
    @Autowired
    private ComponentRepository componentRepository;

    @Autowired
    private ComponentStockRepository componentStockRepository;

    private void checkNotNull(Component component) throws NotNullException {
        if (component.getComponentType() == null) {
            throw new NotNullException("type de composant");
        }
        else if(component.getComponentType().getIdComponentType() == 0){
            throw new NotNullException("type de composant");
        }

        if (component.getComputerType() == null) {
            throw new NotNullException("type d'ordinateur");
        }
        else if(component.getComputerType().getIdComputerType() == 0){
            throw new NotNullException("type d'ordinateur");
        }

        if (component.getName() == null || component.getName().trim().isEmpty()) {
            throw new NotNullException("nom");
        }

        if (component.getSerialNumber() == null || component.getSerialNumber().trim().isEmpty()) {
            throw new NotNullException("numero de serie");
        }
    }

    public List<Component> getAllComponents() {
        return componentRepository.findAll();
    }

    public Page<Component> getAllComponentsPaginated(int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("idComponent").ascending());
        return componentRepository.findAll(pageable);
    }

    public Component getComponentById(int id) throws EntityNotFoundException{
        return componentRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Composant"));
    }

    public Component createComponent(Component component) throws UniqueException, NotNullException {
        try {
            checkNotNull(component);
            return componentRepository.save(component);
        } catch (DataIntegrityViolationException e) {
            if (e.getCause() instanceof ConstraintViolationException) {
                ConstraintViolationException constraintViolationException = (ConstraintViolationException) e.getCause();
                String constraintName = constraintViolationException.getConstraintName();
                if (constraintName.contains("components_serial_number_key")) {
                    throw new UniqueException("numero de serie", component.getSerialNumber());
                }
            }
            throw new RuntimeException("Une erreur s'est produite : " + e.getMessage(), e);
        }
    }

    public Component updateComponent(int id, Component componentDetails) throws EntityNotFoundException {
        Component component = componentRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Composant"));
        component.setComponentType(componentDetails.getComponentType());
        component.setComputerType(componentDetails.getComputerType());
        component.setName(componentDetails.getName());
        component.setSerialNumber(componentDetails.getSerialNumber());
        return componentRepository.save(component);
    }

    public List<ComponentStock> getAllComponentStocks() {
        return componentStockRepository.findAll();
    }

    public ComponentStock getComponentStockById(int id) throws EntityNotFoundException {
        return componentStockRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Composant avec stock"));
    }
}
