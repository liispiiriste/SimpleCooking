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

    @Column(name="quantity")
    private int quantity;

    @Column(name="measurement")
    private String measurement;

    protected Materials(){}
    public Materials(String materials) {
        this.materials = materials;
        this.quantity= quantity;
        this.measurement= measurement;
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

    public int getQuantity()
    {
        return quantity;
    }

    public void setQuantity(int quantity)
    {
        this.quantity = quantity;
    }
    public String getMeasurement()
    {
        return measurement;
    }

    public void setMeasurement(String measurement)
    {
        this.measurement = measurement;
    }
}
