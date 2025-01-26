package mg.atelier.model;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "computer_usage")
public class ComputerUsage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_usage")
    private int idComputerUsage;

    @Column(name = "usage_name", nullable = false, unique = true, length = 50)
    private String usageName;
    public ComputerUsage() {}

    public ComputerUsage(String usageName) {
        this.usageName = usageName;
    }

    public int getIdComputerUsage() {
        return idComputerUsage;
    }

    public void setIdComputerUsage(int id) {
        this.idComputerUsage = id;
    }

    public String getUsageName() {
        return usageName;
    }

    public void setUsageName(String usageName) {
        this.usageName = usageName;
    }

}
