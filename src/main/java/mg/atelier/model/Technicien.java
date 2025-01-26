package mg.atelier.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "technicien")
public class Technicien 
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_technicien")
    private int idTechnicien;

    @Column(nullable = false, length = 50)
    private String name;

    @ManyToOne
    @JoinColumn(name = "id_role_technicien", nullable = false)
    private RoleTechnicien roleTechnicien;

    @ManyToOne
    @JoinColumn(name = "id_genre", nullable = false)
    private Genre genre;

    public Technicien(int idTechnicien, String name, RoleTechnicien roleTechnicien, Genre genre) {
        this.idTechnicien = idTechnicien;
        this.name = name;
        this.roleTechnicien = roleTechnicien;
        this.genre = genre;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    public Technicien(int idTechnicien, String name, RoleTechnicien roleTechnicien) {
        this.idTechnicien = idTechnicien;
        this.name = name;
        this.roleTechnicien = roleTechnicien;
    }

    public Technicien(int idTechnicien, String name) {
        this.idTechnicien = idTechnicien;
        this.name = name;
    }

    public Technicien() {
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

    public RoleTechnicien getRoleTechnicien() {
        return roleTechnicien;
    }

    public void setRoleTechnicien(RoleTechnicien roleTechnicien) {
        this.roleTechnicien = roleTechnicien;
    }
}
