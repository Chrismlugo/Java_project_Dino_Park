package models;

import models.Enums.SpeciesType;
import models.paddocks.Paddock;

import javax.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
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

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
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

    @Column(name = "hunger")
    public int getBelly() {
        return belly;
    }

    public void setBelly(int belly) {
        this.belly = belly;
    }

    @Column(name = "species")
    public SpeciesType getSpecies() {
        return species;
    }

    public void setSpecies(SpeciesType species) {
        this.species = species;
    }

    @ManyToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "paddock_id", nullable = false)
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

    public String hungerLevel(){
        String paddock = getPaddock().getName();
        if(belly == 0){
            return String.format( "UNSAFE:RAMPAGE IMMINENT RE-STOCK %s IMMEDIATELY", paddock);

        } if(belly < 4){
            return String.format("POTENTIALLY UNSAFE: RE-STOCK  %s ", paddock);

        } if(belly >= 4){
            return String.format( "MODERATE: check %s for RE-STOCK", paddock);

        } if(belly >= 8){
            return String.format("SAFE...");

        }else{

        }
        return null;
    }
}
