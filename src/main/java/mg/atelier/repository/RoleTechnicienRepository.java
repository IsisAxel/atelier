package mg.atelier.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import mg.atelier.model.RoleTechnicien;

@Repository
public interface RoleTechnicienRepository extends JpaRepository<RoleTechnicien, Integer> {

}
