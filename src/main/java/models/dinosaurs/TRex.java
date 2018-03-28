package models.dinosaurs;

import models.Dino;
import models.Enums.SpeciesType;
import models.Enums.StomachSize;
import models.paddocks.Paddock;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.Random;

@Entity
@Table(name="rexes")
public class TRex extends Dino {

    private ArrayList<String> rampageStates;

    public TRex() {
    }

    public TRex(String name, Paddock paddock) {
        super(name, SpeciesType.CARNIVORE, paddock, StomachSize.LARGE);
        this.rampageStates = new ArrayList<>();
        rampage();
    }

    public ArrayList<String> getRampageStates() {
        return rampageStates;
    }

    public void addRampageStates(String string) {
        this.rampageStates.add(string);
    }

    public void setRampageStates(ArrayList<String> rampageStates) {
        this.rampageStates = rampageStates;
    }

    public void damagePaddock() {
        getPaddock().setPaddockSecure(false);
    }

    public void rampage() {

        if (getBelly() < getStomachSize().getSize()) {
            Random ran = new Random();
            int randomNumber = ran.nextInt((3) + 1);
            if (randomNumber == 3) {
                damagePaddock();

            }
        }
    }
}


