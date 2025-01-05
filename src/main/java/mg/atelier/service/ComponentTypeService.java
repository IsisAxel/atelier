package mg.atelier.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mg.atelier.exception.EntityNotFoundException;
import mg.atelier.model.ComponentType;
import mg.atelier.repository.ComponentTypeRepository;

@Service
public class ComponentTypeService {
    @Autowired
    private ComponentTypeRepository componentTypeRepository;

    public List<ComponentType> getAllComponentTypes() {
        return componentTypeRepository.findAll();
    }

    public List<ComponentType> getComponentTypeByType(String type) {
        return componentTypeRepository.findByType(type);
    }

    public ComponentType getComponentTypeById(int id) throws EntityNotFoundException{
        return componentTypeRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Type de composant"));
    }
}
