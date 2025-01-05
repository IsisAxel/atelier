package mg.atelier.model;

import jakarta.persistence.*;

@Entity
@Table(name = "reparation_details")
public class ReparationDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_reparation_detail")
    private int idReparationDetail;

    @ManyToOne
    @JoinColumn(name = "id_reparation", nullable = false)
    private Reparation reparation;

    @ManyToOne
    @JoinColumn(name = "id_reparation_type", nullable = false)
    private ReparationType reparationType;

    @Column(name = "price", nullable = false)
    private double price;

    public ReparationDetail(int idReparationDetail, Reparation reparation, ReparationType reparationType,
            double price) {
        this.idReparationDetail = idReparationDetail;
        this.reparation = reparation;
        this.reparationType = reparationType;
        this.price = price;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public ReparationDetail() {
    }

    public int getIdReparationDetail() {
        return idReparationDetail;
    }

    public void setIdReparationDetail(int idReparationDetail) {
        this.idReparationDetail = idReparationDetail;
    }

    public Reparation getReparation() {
        return reparation;
    }

    public void setReparation(Reparation reparation) {
        this.reparation = reparation;
    }

    public ReparationType getReparationType() {
        return reparationType;
    }

    public void setReparationType(ReparationType reparationType) {
        this.reparationType = reparationType;
    }
}