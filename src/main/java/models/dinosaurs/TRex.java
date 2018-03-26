package models.dinosaurs;

import models.Dino;
import models.Enums.SpeciesType;
import models.paddocks.Paddock;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.Collections;

@Entity
@Table(name="rexes")
public class TRex extends Dino {

    private ArrayList<String> rampageStates;

    public TRex() {
    }

    public TRex(String name, SpeciesType species, Paddock paddock) {
        super(name, species, paddock);
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

    public String rampage(){
        Collections.shuffle(this.rampageStates);
        return rampageStates.get(0);
    }

}
