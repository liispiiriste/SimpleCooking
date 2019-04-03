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

    @Column(name="breakfast")
    private String breakfast;

    @Column(name="drink")
    private String drink;

    @Column(name="cake")
    private String cake;

    @Column(name="dessert")
    private String dessert;

    @Column(name="pasta")
    private String pasta;

    @Column(name="pie")
    private String pie;

    @Column(name="mainCourse")
    private String mainCourse;

    @Column(name="salad")
    private String salad;

    @Column(name="snacks")
    private String snacks;

    @Column(name="soup")
    private String soup;

    @Column(name="bCake")
    private String bCake;

    @Column(name="sCake")
    private String sCake;

    @Column(name="casserole")
    private String casserole;

    @Column(name="other")
    private String other;

    protected Categories(){}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

}

