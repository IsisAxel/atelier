package mg.atelier.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import mg.atelier.model.Reparation;
import mg.atelier.model.Return;

@Repository
public interface ReturnRepository extends JpaRepository<Return, Integer> {
    Return findByReparation(Reparation reparation);
}