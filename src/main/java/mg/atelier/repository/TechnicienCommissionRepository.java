package mg.atelier.repository;

import mg.atelier.model.TechnicienCommission;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface TechnicienCommissionRepository  extends JpaRepository<TechnicienCommission, Integer> {

    @Query(value = "SELECT * FROM technicien_commission_function(:startDate, :endDate , 200000)", nativeQuery = true)
    List<TechnicienCommission> findTechnicienCommissionBetweenDates(@Param("startDate") LocalDate startDate, 
                                                                   @Param("endDate") LocalDate endDate);
}
