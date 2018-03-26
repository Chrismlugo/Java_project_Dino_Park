import models.Enums.SpeciesType;
import models.dinosaurs.TRex;
import models.paddocks.Paddock;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

public class TestTRex {
    TRex tRex;
    Paddock paddock;

    @Before
    public void before() {

        paddock = new Paddock("Paddock 9", SpeciesType.CARNIVORE);
        tRex = new TRex("Biggy Smalls", SpeciesType.CARNIVORE, paddock);
    }

    @Test
    public void tRexCanRampage() {
        tRex.addRampageStates("mental");
        tRex.addRampageStates("quite miffed");
        assert(tRex.getRampageStates().contains(tRex.rampage()));
    }

}
