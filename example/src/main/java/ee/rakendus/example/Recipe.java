package ee.rakendus.example;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Recipe {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String description;
    private String materials;
    private int price;
    private int portion;


    protected Recipe() {
    }

    public Recipe(String name, String description, String materials, int price, int portion) {
        this.name = name;
        this.description = description;
        this.materials = materials;
        this.price = price;
        this.portion = portion;
    }

    @Override
    public String toString() {
        return String.format(
                "Recipe[id=%d, name='%s', description='%s', materials='%s', price='%d', portion='%d']",
                id, name, description, materials, price, portion);
    }
}
