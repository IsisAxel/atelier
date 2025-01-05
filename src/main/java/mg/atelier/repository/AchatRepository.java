package mg.atelier.repository;

import mg.atelier.model.Achat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AchatRepository extends JpaRepository<Achat, Integer> {
}