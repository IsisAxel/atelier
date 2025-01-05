package mg.atelier.repository;

import mg.atelier.model.ReparationTypePrice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ReparationTypePriceRepository extends JpaRepository<ReparationTypePrice, Integer> {
    @Query("SELECT rtp FROM ReparationTypePrice rtp WHERE rtp.reparationType.idReparationType = :idReparationType AND rtp.effectiveDate <= :date ORDER BY rtp.effectiveDate DESC LIMIT 1")
    ReparationTypePrice getReparationTypePriceByIdReparationTypeAndDate(@Param("idReparationType") int idReparationType, @Param("date") LocalDateTime date);

    @Query(value = "SELECT * FROM get_latest_reparation_type_prices(:date)", nativeQuery = true)
    List<ReparationTypePrice> getReparationTypePriceByDate(@Param("date") LocalDateTime date);
}