package mg.atelier.model;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "reparation_type_prices")
public class ReparationTypePrice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_reparation_type_price")
    private int idReparationTypePrice;

    @ManyToOne
    @JoinColumn(name = "id_reparation_type", nullable = false)
    private ReparationType reparationType;

    @Column(name = "price", nullable = false)
    private double price;

    @Column(name = "effective_date", nullable = false)
    private LocalDateTime effectiveDate;

    public ReparationTypePrice() {
    }

    public ReparationTypePrice(int idReparationTypePrice, ReparationType reparationType, double price, LocalDateTime effectiveDate) {
        this.idReparationTypePrice = idReparationTypePrice;
        this.reparationType = reparationType;
        this.price = price;
        this.effectiveDate = effectiveDate;
    }

    public int getIdReparationTypePrice() {
        return idReparationTypePrice;
    }

    public void setIdReparationTypePrice(int idReparationTypePrice) {
        this.idReparationTypePrice = idReparationTypePrice;
    }

    public ReparationType getReparationType() {
        return reparationType;
    }

    public void setReparationType(ReparationType reparationType) {
        this.reparationType = reparationType;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public LocalDateTime getEffectiveDate() {
        return effectiveDate;
    }

    public void setEffectiveDate(LocalDateTime effectiveDate) {
        this.effectiveDate = effectiveDate;
    }
}