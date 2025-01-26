package mg.atelier.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "reparation")
public class Reparation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_reparation")
    private int idReparation;

    @ManyToOne
    @JoinColumn(name = "id_computer", nullable = false)
    private Computer computer;

    @ManyToOne
    @JoinColumn(name = "id_technicien", nullable = false)
    private Technicien technicien;

    public Reparation(int idReparation, Computer computer, Technicien technicien, LocalDateTime startDate,
            LocalDateTime endDate, double totalAmount) {
        this.idReparation = idReparation;
        this.computer = computer;
        this.technicien = technicien;
        this.startDate = startDate;
        this.endDate = endDate;
        this.totalAmount = totalAmount;
    }

    public Technicien getTechnicien() {
        return technicien;
    }

    public void setTechnicien(Technicien technicien) {
        this.technicien = technicien;
    }

    @Column(name = "start_date", nullable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime startDate;

    @Column(name = "end_date")
    private LocalDateTime endDate;

    @Column(name = "total_amount", nullable = false)
    private double totalAmount;

    public Reparation(int idReparation) {
        this.idReparation = idReparation;
    }

    public Reparation() {
    }

    public Reparation(int idReparation, Computer computer, LocalDateTime startDate,
            LocalDateTime endDate, double totalAmount) {
        this.idReparation = idReparation;
        this.computer = computer;
        this.startDate = startDate;
        this.endDate = endDate;
        this.totalAmount = totalAmount;
    }

    public int getIdReparation() {
        return idReparation;
    }

    public void setIdReparation(int idReparation) {
        this.idReparation = idReparation;
    }

    public Computer getComputer() {
        return computer;
    }

    public void setComputer(Computer computer) {
        this.computer = computer;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDateTime endDate) {
        this.endDate = endDate;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }
}