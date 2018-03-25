package models.dinosaurs;

import models.Dino;
import models.Enums.SpeciesType;
import models.paddocks.Paddock;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="rexes")
public class TRex extends Dino {

    public TRex() {
    }

    public TRex(String name, SpeciesType species, Paddock paddock) {
        super(name, species, paddock);
    }
}
