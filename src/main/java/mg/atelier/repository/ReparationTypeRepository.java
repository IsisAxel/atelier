package mg.atelier.repository;

import mg.atelier.model.ReparationType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReparationTypeRepository extends JpaRepository<ReparationType, Integer> {
}