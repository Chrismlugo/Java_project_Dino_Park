package models.dinosaurs;

import models.Dino;
import models.Enums.SpeciesType;
import models.paddocks.Paddock;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "diplodocuses")
public class Diplodocus extends Dino {

    public Diplodocus() {
    }

    public Diplodocus(String name, SpeciesType species, Paddock paddock) {
        super(name, species, paddock);

    }
}
