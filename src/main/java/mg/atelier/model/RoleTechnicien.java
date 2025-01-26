package mg.atelier.model;
import jakarta.persistence.*;

@Entity
@Table(name = "role_technicien")
public class RoleTechnicien {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_role_technicien")
    private int idRoleTechnicien;

    @Column(name = "name", length = 50)
    private String name;

    @Column(name = "commission", nullable = false)
    private Double commission;

    public int getIdRoleTechnicien() {
        return idRoleTechnicien;
    }

    public void setIdRoleTechnicien(int idRoleTechnicien) {
        this.idRoleTechnicien = idRoleTechnicien;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getCommission() {
        return commission;
    }

    public void setCommission(Double commission) {
        this.commission = commission;
    }
}
