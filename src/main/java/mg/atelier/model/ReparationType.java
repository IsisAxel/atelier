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
@Table(name = "reparation_types")
public class ReparationType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_reparation_type")
    private int idReparationType;

    @Column(name = "type", nullable = false, length = 100)
    private String type;

    @ManyToOne
    @JoinColumn(name = "id_component")
    private Component component;

    @Column(name = "duration", nullable = false)
    private long duration;

    public ReparationType() {
    }

    public ReparationType(int idReparationType, String type, Component component, long duration) {
        this.idReparationType = idReparationType;
        this.type = type;
        this.component = component;
        this.duration = duration;
    }

    public int getIdReparationType() {
        return idReparationType;
    }

    public void setIdReparationType(int idReparationType) {
        this.idReparationType = idReparationType;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Component getComponent() {
        return component;
    }

    public void setComponent(Component component) {
        this.component = component;
    }

    public long getDuration() {
        return duration;
    }

    public void setDuration(long duration) {
        this.duration = duration;
    }

    public String getCleanDuration() {
        long hours = duration / 3600;
        long minutes = (duration % 3600) / 60;
        return String.format("%d heures %d minutes", hours, minutes);
    }
}