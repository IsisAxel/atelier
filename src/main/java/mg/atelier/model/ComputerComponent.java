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
@Table(name = "computer_components")
public class ComputerComponent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_computer_component")
    private int idComputerComponent;

    @ManyToOne
    @JoinColumn(name = "id_computer", nullable = false)
    private Computer computer;

    @ManyToOne
    @JoinColumn(name = "id_component", nullable = false)
    private Component component;

    @ManyToOne
    @JoinColumn(name = "id_status", nullable = false)
    private Status status;

    public ComputerComponent() {
    }

    public ComputerComponent(int idComputerComponent, Computer computer, Component component, Status status) {
        this.idComputerComponent = idComputerComponent;
        this.computer = computer;
        this.component = component;
        this.status = status;
    }

    public int getIdComputerComponent() {
        return idComputerComponent;
    }

    public void setIdComputerComponent(int idComputerComponent) {
        this.idComputerComponent = idComputerComponent;
    }

    public Computer getComputer() {
        return computer;
    }

    public void setComputer(Computer computer) {
        this.computer = computer;
    }

    public Component getComponent() {
        return component;
    }

    public void setComponent(Component component) {
        this.component = component;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

}