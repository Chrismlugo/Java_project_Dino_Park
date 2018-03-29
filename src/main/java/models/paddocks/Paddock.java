package models.paddocks;

import com.codeclan.db.DBHelper;
import models.Dino;
import models.Enums.SpeciesType;
import models.Park;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Entity
@Table(name="paddocks")
public class Paddock {
    private int id;
    private String name;
    private List<Dino> dinosaurs;
    private SpeciesType species;
    private int foodStock;
    private boolean paddockSecure;
    private Park park;

    public Paddock() {}

    public Paddock(String name, SpeciesType species, Park park) {
        this.name = name;
        this.species = species;
        this.foodStock = 0;
        this.dinosaurs = new ArrayList<>();
        this.paddockSecure = true;
        this.park = park;
        breakout();
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

    @OneToMany(mappedBy = "paddock")
    @LazyCollection(LazyCollectionOption.FALSE)
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
    public int getFoodStock() {
        return foodStock;
    }

    public void setFoodStock(int foodStock) {
        this.foodStock = foodStock;
    }

    @ManyToOne
    @JoinColumn(name = "park_id", nullable = false)
    public Park getPark() {
        return park;
    }

    public void setPark(Park park) {this.park = park;}

    public void feedDinos(){
        for(Dino dino: this.dinosaurs){
            Random ran = new Random();
            int foodAmount = ran.nextInt((5) + 1);
            if(this.foodStock >= foodAmount) {
                dino.feed(foodAmount);
                this.foodStock -= foodAmount;
                DBHelper.saveOrUpdate(dino);
            }
        }
    }

    public void addDino(Dino dino){
        this.dinosaurs.add(dino);
    }

    public void stockPaddock(int amount){
       this.foodStock += amount;
    }

    public boolean isPaddockSecure() {
        for(Dino dino: this.dinosaurs) {
            if (dino.hungry()){
                this.paddockSecure = false;
            }
        }
        return this.paddockSecure;
    }

    public boolean getPaddockSecure(){
        return this.paddockSecure;
    }

    public void setPaddockSecure(boolean paddockSecure) {
        this.paddockSecure = paddockSecure;
    }

    public int countDinosaurs(){
        return this.dinosaurs.size();
    }

    public void breakout(){
        if(!this.paddockSecure){
            park.setRampagers(dinosaurs);
            dinosaurs.clear();
        }
    }

}
