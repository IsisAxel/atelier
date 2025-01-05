package mg.atelier.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "component_type")
public class ComponentType
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_component_type")
    private int idComponentType;

    @Column(nullable = false, length = 50)
    private String type;

    public ComponentType() {
    }

    public ComponentType(int idComponentType, String type) {
        this.idComponentType = idComponentType;
        this.type = type;
    }

    public int getIdComponentType() {
        return idComponentType;
    }

    public void setIdComponentType(int idComponentType) {
        this.idComponentType = idComponentType;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    } 
}
