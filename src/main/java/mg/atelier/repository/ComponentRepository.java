package mg.atelier.repository;

import mg.atelier.model.Component;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ComponentRepository extends JpaRepository<Component, Integer> {
    @Query(value = "SELECT * FROM components_with_stock", nativeQuery = true)
    List<Component> findAllWithStock();
}