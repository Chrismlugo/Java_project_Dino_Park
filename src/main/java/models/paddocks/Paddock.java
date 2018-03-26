package models.paddocks;

import com.codeclan.db.DBHelper;
import models.Dino;
import models.Enums.SpeciesType;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
@Entity
@Table(name="paddocks")
public class Paddock {
    private int id;
    private String name;
    private List<Dino> dinosaurs;
    private SpeciesType species;
    private int foodStock;

    public Paddock() {
        this.foodStock = 0;
        this.dinosaurs = new ArrayList<Dino>();
    }

    public Paddock(String name, SpeciesType species) {
        this.name = name;
        this.species = species;
        this.foodStock = 0;
        this.dinosaurs = new ArrayList<Dino>();
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @OneToMany(mappedBy = "paddock", fetch = FetchType.EAGER)
    public List<Dino> getDinosaurs() {
        return dinosaurs;
    }

    public void setDinosaurs(List<Dino> dinosaurs) {
        this.dinosaurs = dinosaurs;
    }

    @Column(name = "species")
    public SpeciesType getSpecies() {
        return species;
    }

    public void setSpecies(SpeciesType species) {
        this.species = species;
    }

    @Column(name = "food_stock")
    public int getFoodStock() {
        return foodStock;
    }

    public void setFoodStock(int food_stock) {
        this.foodStock = food_stock;
    }

    public void feedDinos(){
        for(Dino dino: this.dinosaurs){
            if(this.foodStock > 0) {
                int belly = dino.getBelly();
                int food = belly + 1;
                int intake = belly + food;
                dino.setBelly(intake);
                this.foodStock -= food;
                DBHelper.saveOrUpdate(dino);
            }


        }
    }

    public void stockPaddock(){
        this.foodStock += 20;
    }



}
