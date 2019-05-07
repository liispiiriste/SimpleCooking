package ee.rakendus.example.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "category")
public class Categories {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    @Column(name="name", unique = true)
    private String name;

    @ManyToMany
    List<Recipe> recipes;

    public Categories(){}


    public Long getId() { return id;}

    public void setId(Long id) {this.id = id;}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

