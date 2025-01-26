package mg.atelier.repository;

import mg.atelier.model.Reparation;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ReparationRepository extends JpaRepository<Reparation, Integer> {
    @Query("SELECT r FROM Reparation r WHERE DATE(r.startDate) = :date")
    List<Reparation> findByStartDate(@Param("date") LocalDate date);
}