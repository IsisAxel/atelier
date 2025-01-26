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
@Table(name = "return")
public class Return 
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_return")
    private int idReturn;  
    
    @Column(name = "return_date")
    private LocalDateTime returnDate;

    @ManyToOne
    @JoinColumn(name = "id_reparation", nullable = false)
    private Reparation reparation;

    public Return(LocalDateTime returnDate, Reparation reparation) {
        this.returnDate = returnDate;
        this.reparation = reparation;
    }

    public Return() {
    }

    public Return(int idReturn, LocalDateTime returnDate, Reparation reparation) {
        this.idReturn = idReturn;
        this.returnDate = returnDate;
        this.reparation = reparation;
    }

    public int getIdReturn() {
        return idReturn;
    }

    public void setIdReturn(int idReturn) {
        this.idReturn = idReturn;
    }

    public LocalDateTime getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(LocalDateTime returnDate) {
        this.returnDate = returnDate;
    }

    public Reparation getReparation() {
        return reparation;
    }

    public void setReparation(Reparation reparation) {
        this.reparation = reparation;
    }
}