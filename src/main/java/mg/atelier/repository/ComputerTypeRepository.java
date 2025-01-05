package mg.atelier.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import mg.atelier.model.ComputerType;
import java.util.List;

public interface ComputerTypeRepository extends JpaRepository<ComputerType, Integer> {
    List<ComputerType> findByType(String type);
}