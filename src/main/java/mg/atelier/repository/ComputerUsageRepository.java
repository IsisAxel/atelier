package mg.atelier.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import mg.atelier.model.ComputerUsage;
import java.util.List;

public interface ComputerUsageRepository extends JpaRepository<ComputerUsage, Integer> {
 
}
