package mg.atelier.model;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "achat")
public class Achat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_achat")
    private int idAchat;

    @ManyToOne
    @JoinColumn(name = "id_component", nullable = false)
    private Component component;

    @Column(name = "quantity", nullable = false)
    private int quantity;

    @Column(name = "unit_price", nullable = false)
    private double unitPrice;

    @Column(name = "purchase_date", nullable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime purchaseDate;

    public Achat() {
    }

    public Achat(int idAchat, Component component, int quantity, double unitPrice, LocalDateTime purchaseDate) {
        this.idAchat = idAchat;
        this.component = component;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
        this.purchaseDate = purchaseDate;
    }

    public int getIdAchat() {
        return idAchat;
    }

    public void setIdAchat(int idAchat) {
        this.idAchat = idAchat;
    }

    public Component getComponent() {
        return component;
    }

    public void setComponent(Component component) {
        this.component = component;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public LocalDateTime getPurchaseDate() {
        return purchaseDate;
    }

    public void setPurchaseDate(LocalDateTime purchaseDate) {
        this.purchaseDate = purchaseDate;
    }
}