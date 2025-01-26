package mg.atelier.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "recommendation")
public class Recommendation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @ManyToOne
    @JoinColumn(name = "id_component", nullable = false)
    private Component component;
    private LocalDateTime period;
    public Recommendation(Component component, LocalDateTime period) {
        this.component = component;
        this.period = period;
    }
    public Recommendation() {
    }
    public Recommendation(int id, Component component, LocalDateTime period) {
        this.id = id;
        this.component = component;
        this.period = period;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public Component getComponent() {
        return component;
    }
    public void setComponent(Component component) {
        this.component = component;
    }
    public LocalDateTime getPeriod() {
        return period;
    }
    public void setPeriod(LocalDateTime period) {
        this.period = period;
    }
 
}
