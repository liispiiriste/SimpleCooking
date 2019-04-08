package ee.rakendus.example.image;

import javax.persistence.*;

@Entity
@Table(name = "image")
public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private String name;

    @Column(name = "recipeId")
    private Long recipeId;

    public Image(String name, Long recipeId) {
        this.name = name;
        this.recipeId = recipeId;
    }
}
