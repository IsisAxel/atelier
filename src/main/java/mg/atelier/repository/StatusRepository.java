package mg.atelier.repository;

import mg.atelier.model.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StatusRepository extends JpaRepository<Status, Integer> {


    Status findByStatus(String name);
}