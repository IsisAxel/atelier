package mg.atelier.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import mg.atelier.model.Clients;

public interface ClientsRepository extends JpaRepository<Clients, Integer> {
}