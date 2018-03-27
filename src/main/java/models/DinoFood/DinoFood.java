package models.DinoFood;

import models.Dino;
import models.Enums.FoodType;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
@Entity
@Table(name="food")
public class DinoFood {
    int id;
    private List<Dino> eaters;
    private FoodType foodType;

    public DinoFood() {
    }

    public DinoFood(FoodType foodtype) {
        this.foodType = foodType;
        this.eaters = new ArrayList<>();
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public FoodType getFoodType() {
        return foodType;
    }

    public void setFoodType(FoodType foodType) {
        this.foodType = foodType;
    }

    @ManyToMany
    @LazyCollection(LazyCollectionOption.FALSE)
    @JoinTable(name = "dino_food",
            joinColumns = {@JoinColumn(name = "food_id", nullable = false, updatable = false)},
            inverseJoinColumns = {@JoinColumn(name = "dino_id", nullable = false, updatable = false)})
    public List<Dino> getEaters() {
        return eaters;
    }

    public void setEaters(List<Dino> eaters) {
        this.eaters = eaters;
    }
}
