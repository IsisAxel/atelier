package mg.atelier.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import mg.atelier.model.ComponentType;

public interface ComponentTypeRepository extends JpaRepository<ComponentType, Integer> {
    List<ComponentType> findByType(String type);
}
