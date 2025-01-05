package mg.atelier.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import mg.atelier.model.ComponentStock;

public interface ComponentStockRepository extends JpaRepository<ComponentStock, Integer> {
}
