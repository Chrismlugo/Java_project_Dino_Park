package models;

import models.Enums.SpeciesType;
import models.paddocks.Paddock;

public abstract class Dino {
    private int id;
    private String name;
    private int belly;
    private SpeciesType species;
    private Paddock paddock;

    public Dino() {
    }

    public Dino(String name, SpeciesType species, Paddock paddock) {
        this.name = name;
        this.species = species;
        this.paddock = paddock;
        this.belly = 0;
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

    public int getBelly() {
        return belly;
    }

    public void setBelly(int belly) {
        this.belly = belly;
    }

    public SpeciesType getSpecies() {
        return species;
    }

    public void setSpecies(SpeciesType species) {
        this.species = species;
    }

    public Paddock getPaddock() {
        return paddock;
    }

    public void setPaddock(Paddock paddock) {
        this.paddock = paddock;
    }

    public void eat() {
        this.belly += 1;
        getPaddock().feedDino();
    }
}
