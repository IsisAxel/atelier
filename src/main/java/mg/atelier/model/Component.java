package mg.atelier.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;

@Entity
@Table(name = "components")
public class Component {

    @Id
    @Column(name = "id_component")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idComponent;

    @ManyToOne
    @JoinColumn(name = "id_type", nullable = false, updatable = false)
    private ComponentType componentType;

    @ManyToOne
    @JoinColumn(name = "id_type_computer", nullable = false, updatable = false)
    private ComputerType computerType;

    @Column(name = "name", nullable = false, length = 100)
    private String name;

    @Column(name = "serial_number", nullable = false, length = 50)
    private String serialNumber;

    public int getIdComponent() {
        return idComponent;
    }

    public void setIdComponent(int idComponent) {
        this.idComponent = idComponent;
    }

    public ComponentType getComponentType() {
        return componentType;
    }

    public void setComponentType(ComponentType componentType) {
        this.componentType = componentType;
    }

    public ComputerType getComputerType() {
        return computerType;
    }

    public void setComputerType(ComputerType computerType) {
        this.computerType = computerType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }
}