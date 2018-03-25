package models.dinosaurs;

import models.Dino;
import models.Enums.SpeciesType;
import models.paddocks.Paddock;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "raptors")
public class Raptor extends Dino {

    public Raptor() {
    }

    public Raptor(String name, SpeciesType species, Paddock paddock) {
        super(name, species, paddock);
    }

}