package models.dinosaurs;

import models.Dino;
import models.DinoFood.DinoFood;
import models.Enums.SpeciesType;
import models.Enums.StomachSize;
import models.behaviours.IRampage;
import models.paddocks.Paddock;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.Collections;

@Entity
@Table(name="rexes")
public class TRex extends Dino implements IRampage {

    private ArrayList<String> rampageStates;

    public TRex() {
    }

    public TRex(String name, SpeciesType species, Paddock paddock, StomachSize stomachSize) {
        super(name, species, paddock, stomachSize);

        this.setSpecies(SpeciesType.CARNIVORE);
        this.setStomachSize(StomachSize.SMALL);

    this.rampageStates = new ArrayList<>();
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

    public void rampage(){
    for(DinoFood food : this.getBelly()){
      if(food.)
    }

    }

}
