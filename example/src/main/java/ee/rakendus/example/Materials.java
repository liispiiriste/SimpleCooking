package ee.rakendus.example;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "materials")
public class Materials {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    @Column(name="materials")
    private String materials;

    protected Materials(){}
    public Materials(String materials) {
        this.materials = materials;
    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMaterials()
    {
        return materials;
    }

    public void setMaterials(String materials)
    {
        this.materials = materials;
    }

}
