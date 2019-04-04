package ee.rakendus.example.categories;

import ee.rakendus.example.Recipe;
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

    @Column(name="name")
    private String name;

    @Column(name="breakfast")
    private String breakfast;

    @Column(name="drinks")
    private String drinks;

    @Column(name="cakes")
    private String cakes;

    @Column(name="dessert")
    private String dessert;

    @Column(name="pasta")
    private String pasta;

    @Column(name="pies")
    private String pies;

    @Column(name="meal")
    private String meal;

    @Column(name="salad")
    private String salad;

    @Column(name="snacks")
    private String snacks;

    @Column(name="soup")
    private String soup;

    @Column(name="pie")
    private String pie;

    @Column(name="sandwichCake")
    private String sandwichCake;

    @Column(name="formdish")
    private String formdish;

    @Column(name="other")
    private String other;

    @ManyToMany
    List<Recipe> recipes;

    protected Categories(){}


    public Long getId() { return id;}

    public void setId(Long id) {this.id = id;}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBreakfast() {
        return breakfast;
    }

    public void setBreakfast(String breakfast) {
        this.breakfast = breakfast;
    }

    public String getDrinks() {
        return drinks;
    }

    public void setDrinks(String drinks) {
        this.drinks = drinks;
    }

    public String getCakes() {
        return cakes;
    }

    public void setCakes(String cakes) {
        this.cakes = cakes;
    }

    public String getDessert() {
        return dessert;
    }

    public void setDessert(String dessert) {
        this.dessert = dessert;
    }

    public String getPasta() {
        return pasta;
    }

    public void setPasta(String pasta) {
        this.pasta = pasta;
    }

    public String getPies() {
        return pies;
    }

    public void setPies(String pies) {this.pies = pies; }

    public String getMeal() {
        return meal;
    }

    public void setMeal(String meal) {
        this.meal = meal;
    }

    public String getSalad() {
        return salad;
    }

    public void setSalad(String salad) {
        this.salad = salad;
    }

    public String getSoup() {
        return soup;
    }

    public void setSoup(String soup) {
        this.soup = soup;
    }

    public String getSnacks() {
        return snacks;
    }

    public void setSnacks(String snacks) {
        this.snacks = snacks;
    }

    public String getPie() {
        return pie;
    }

    public void setPie(String pie) {
        this.pie = pie;
    }

    public String getSandwichCake() {
        return sandwichCake;
    }

    public void setSandwichCake(String sandwichCake) {
        this.sandwichCake = sandwichCake;
    }

    public String getFormdish() {
        return formdish;
    }

    public void setFormdish(String formdish) {
        this.formdish = formdish;
    }

    public String getOther() {return other;}

    public void setOther(String other) {
        this.other = other;
    }
}

