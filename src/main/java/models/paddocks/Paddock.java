package models.paddocks;

import models.Dino;
import models.Enums.SpeciesType;

import java.util.ArrayList;
import java.util.List;

public class Paddock {
    private int id;
    private String name;
    private List<Dino> dinosaurs;
    private SpeciesType species;
    private int food_stock;

    public Paddock() {
    }

    public Paddock(String name, SpeciesType species, int food_stock) {
        this.name = name;
        this.species = species;
        this.food_stock = food_stock;
        this.dinosaurs = new ArrayList<Dino>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Dino> getDinosaurs() {
        return dinosaurs;
    }

    public void setDinosaurs(List<Dino> dinosaurs) {
        this.dinosaurs = dinosaurs;
    }

    public SpeciesType getSpecies() {
        return species;
    }

    public void setSpecies(SpeciesType species) {
        this.species = species;
    }

    public int getFood_stock() {
        return food_stock;
    }

    public void setFood_stock(int food_stock) {
        this.food_stock = food_stock;
    }
}
