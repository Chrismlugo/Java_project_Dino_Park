package models;

import com.codeclan.db.DBHelper;
import models.DinoFood.DinoFood;
import models.Enums.SpeciesType;
import models.Enums.StomachSize;
import models.paddocks.Paddock;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Dino {
    private int id;
    private String name;
    private List<DinoFood> belly;
    private SpeciesType species;
    private Paddock paddock;
    private StomachSize stomachSize;

    public Dino() {
    }

    public Dino(String name, SpeciesType species, Paddock paddock, StomachSize stomachSize) {
        this.name = name;
        this.species = species;
        this.paddock = paddock;
        this.belly = new ArrayList<>();
        this.stomachSize = stomachSize;

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

    @ManyToMany
    @LazyCollection(LazyCollectionOption.FALSE)
    @JoinTable(name = "dino_food",
            joinColumns = {@JoinColumn(name = "dino_id", nullable = false, updatable = false)},
            inverseJoinColumns = {@JoinColumn(name = "food_id", nullable = false, updatable = false)})
    public List<DinoFood> getBelly() {
        return belly;
    }

    public void setBelly(List<DinoFood> belly) {
        this.belly = belly;
    }

    public void feed(DinoFood food) {
        if(this.belly.size() < this.stomachCapacity()){
        this.belly.add(food);
        }
    }

    @Column(name = "species")
    public SpeciesType getSpecies() {
        return species;
    }

    public void setSpecies(SpeciesType species) {
        this.species = species;
    }

    @Column(name= "stomach_size")
    public StomachSize getStomachSize() {
        return stomachSize;
    }

    public void setStomachSize(StomachSize stomachSize) {
        this.stomachSize = stomachSize;
    }

    @ManyToOne
    @JoinColumn(name = "paddock_id", nullable = false)
    public Paddock getPaddock() {
        return paddock;
    }

    public void setPaddock(Paddock paddock) {
        this.paddock = paddock;
    }



    public int stomachCapacity() {
        return this.stomachSize.getSize();
    }




    public String hungerLevel() {
        String paddock = getPaddock().getName();
        String transfer = "or check for paddock transfer";
        if (belly.size() == 0) {
            if (species == SpeciesType.HERBIVORE) {
                return String.format("Unhealthy: Check %s food store %s", paddock, transfer);
            }

            return String.format("Unhealthy: Check %s food store ", paddock);

        }
        if (belly.size() < 4) {

            return String.format("Potential Health Risk: check food store in  %s ", paddock);

        }
        if (belly.size() >= 4) {
            return String.format("Moderately healthy: check %s ", paddock);

        }
        if (belly.size() >= 5) {
            return String.format("Healthy");

        } else {

        }
        return null;

    }
}
