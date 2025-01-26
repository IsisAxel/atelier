package mg.atelier.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
public class TechnicienCommission {

    @Id 
    @Column(name="id_technicien")
    private int idTechnicien;

    
    @ManyToOne
    @JoinColumn(name = "id_genre", nullable = false)
    private Genre genre;

    private String name;

    private double commission;


    @ManyToOne
    @JoinColumn(name = "id_role_technicien", nullable = false)
    private RoleTechnicien roleTechnicien;
    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    public RoleTechnicien getRoleTechnicien() {
        return roleTechnicien;
    }

    public void setRoleTechnicien(RoleTechnicien roleTechnicien) {
        this.roleTechnicien = roleTechnicien;
    }

    public int getIdTechnicien() {
        return idTechnicien;
    }

    public void setIdTechnicien(int idTechnicien) {
        this.idTechnicien = idTechnicien;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getCommission() {
        return commission;
    }

    public void setCommission(double commission) {
        this.commission = commission;
    }
}

