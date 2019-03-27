package ee.rakendus.example.categories;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "category")
public class Categories {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    @Column(name="category")
    private String category;

    protected Categories(){}

    public Categories(String category){
        this.category = category;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}

