package models.dinosaurs;

import models.Dino;
import models.Enums.SpeciesType;
import models.paddocks.Paddock;

public class Triceratops extends Dino {

    public Triceratops() {}

    public Triceratops(String name, SpeciesType species, Paddock paddock) {
        super(name, species, paddock);
    }
}
