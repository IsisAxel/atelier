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
@Table(name = "computers")
public class Computer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_computer")
    private int idComputer;

    @ManyToOne
    @JoinColumn(name = "id_type_computer", nullable = false)
    private ComputerType computerType;

    @Column(name = "model", nullable = false, length = 50)
    private String model;

    @ManyToOne
    @JoinColumn(name = "id_client", nullable = false)
    private Clients client;

    @ManyToOne
    @JoinColumn(name = "id_brand", nullable = false)
    private Brand brand;

    public Computer(int idComputer, ComputerType computerType, String model, Clients client, Brand brand) {
        this.idComputer = idComputer;
        this.computerType = computerType;
        this.model = model;
        this.client = client;
        this.brand = brand;
    }

    public Computer() {
    }

    public int getIdComputer() {
        return idComputer;
    }

    public void setIdComputer(int idComputer) {
        this.idComputer = idComputer;
    }

    public ComputerType getComputerType() {
        return computerType;
    }

    public void setComputerType(ComputerType computerType) {
        this.computerType = computerType;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Clients getClient() {
        return client;
    }

    public void setClient(Clients client) {
        this.client = client;
    }

    public Brand getBrand() {
        return brand;
    }

    public void setBrand(Brand brand) {
        this.brand = brand;
    }
}