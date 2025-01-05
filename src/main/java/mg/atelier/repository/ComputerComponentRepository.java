package mg.atelier.repository;

import mg.atelier.model.ComputerComponent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ComputerComponentRepository extends JpaRepository<ComputerComponent, Integer> {
}