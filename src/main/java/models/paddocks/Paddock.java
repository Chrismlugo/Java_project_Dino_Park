package models.paddocks;

import com.codeclan.db.DBHelper;
import models.Dino;
import models.DinoFood.DinoFood;
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
    private ArrayList<DinoFood> foodStock;
    private boolean paddockSecure;

    public Paddock() {}

    public Paddock(String name, SpeciesType species) {
        this.name = name;
        this.species = species;
        this.foodStock = new ArrayList<>();
        this.dinosaurs = new ArrayList<>();
        this.paddockSecure = true;
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

    @Column(name="foodStock")
    public ArrayList<DinoFood> getFoodStock() {
        return foodStock;
    }

    public void setFoodStock(ArrayList<DinoFood> foodStock) {
        this.foodStock = foodStock;
    }

    public void feedDinos(){
        for(Dino dino: this.dinosaurs){
            if(this.foodStock.size() > 0) {
                DinoFood intake = this.foodStock.remove(0);
                dino.feed(intake);
                DBHelper.saveOrUpdate(dino);
            }
        }
    }

    public void stockPaddock(){
        for(int i = 0; i < 20; i++){
        DinoFood dinoFood = new DinoFood();
        this.foodStock.add(dinoFood);
    }
}


    public boolean isPaddockSecure() {
        return paddockSecure;
    }

    public void setPaddockSecure(boolean paddockSecure) {
        this.paddockSecure = paddockSecure;
    }

    public int countDinosaurs(){
      return this.dinosaurs.size();

    }

    public void breakout(){
        List<Dino>  rampagers = new ArrayList<>();
        if(this.paddockSecure == false){
            rampagers = dinosaurs;
        }
        dinosaurs.clear();
    }
}
