package models.dinosaurs;

import models.Dino;
import models.Enums.SpeciesType;
import models.paddocks.Paddock;

public class TRex extends Dino {

    public TRex() {
    }

    public TRex(String name, SpeciesType species, Paddock paddock) {
        super(name, species, paddock);
    }


}
