package mg.atelier.repository;

import mg.atelier.model.Reparation;
import mg.atelier.model.ReparationDetail;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReparationDetailRepository extends JpaRepository<ReparationDetail, Integer> {

    List<ReparationDetail> findByReparation(Reparation reparation);
}