package mg.atelier.service;

import mg.atelier.exception.NotNullException;
import mg.atelier.exception.UniqueException;
import mg.atelier.model.ReparationType;
import mg.atelier.model.Component;
import mg.atelier.repository.ReparationTypeRepository;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReparationTypeService {

    @Autowired
    private ReparationTypeRepository reparationTypeRepository;

    private void checkNotNull(ReparationType reparationType) throws NotNullException {
        if (reparationType.getType() == null || reparationType.getType().trim().isEmpty()) {
            throw new NotNullException("type");
        }
        if (reparationType.getComponent() == null || reparationType.getComponent().getIdComponent() == 0) {
            throw new NotNullException("type de composant");
        }
    }

    public List<ReparationType> getAllReparationTypes() {
        return reparationTypeRepository.findAll();
    }

    public ReparationType getReparationTypeById(int id) {
        return reparationTypeRepository.findById(id).orElse(null);
    }
    public ReparationType getReparationTypeByComponent(Component component) {
        return reparationTypeRepository.findByComponent(component);
    }
    public ReparationType createReparationType(ReparationType reparationType) throws UniqueException , NotNullException {
        try {
            checkNotNull(reparationType);
            return reparationTypeRepository.save(reparationType);
        } catch (DataIntegrityViolationException e) {
            if (e.getCause() instanceof ConstraintViolationException) {
                ConstraintViolationException constraintViolationException = (ConstraintViolationException) e.getCause();
                String constraintName = constraintViolationException.getConstraintName();
                if (constraintName.contains("reparation_types_type_key")) {
                    throw new UniqueException("type", reparationType.getType());
                }
            }
            throw new RuntimeException("Une erreur s'est produite : " + e.getMessage(), e);
        }
    }
}