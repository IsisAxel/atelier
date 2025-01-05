package mg.atelier.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "computer_type")
public class ComputerType 
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_computer_type")
    private int idComputerType;

    @Column(nullable = false, length = 50)
    private String type;

    public ComputerType() {
    }

    public ComputerType(int idComputerType, String type) {
        this.idComputerType = idComputerType;
        this.type = type;
    }

    public int getIdComputerType() {
        return idComputerType;
    }

    public void setIdComputerType(int idComputerType) {
        this.idComputerType = idComputerType;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    } 
}
