package ee.rakendus.example;


import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "recipe")
public class Recipe {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank
    @Column(name = "name")
    private String name;

    @NotBlank
    @Column(name = "description")
    private String description;

    @NotBlank
    @Column(name = "materials")
    private String materials;

    @NotBlank
    @Column(name = "category")
    private String category;

    @NotNull
    @Column(name = "price")
    private int price;

    @NotNull
    @Column(name = "portion")
    private int portion;


    protected Recipe() {
    }

    public Recipe(String name, String description, String materials, String category, int price, int portion) {
        this.name = name;
        this.description = description;
        this.materials = materials;
        this.category = category;
        this.price = price;
        this.portion = portion;
    }

    //region getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getMaterials() {
        return materials;
    }

    public void setMaterials(String materials) {
        this.materials = materials;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getPortion() {
        return portion;
    }

    public void setPortion(int portion) {
        this.portion = portion;
    }
    //endregion

    @Override
    public String toString() {
        return String.format(
                "Recipe[id=%d, name='%s', description='%s', materials='%s', category='%s',price='%d', portion='%d']",
                id, name, description, materials, category,price, portion);
    }
}
