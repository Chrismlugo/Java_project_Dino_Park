import models.Enums.SpeciesType;
import models.Park;
import models.dinosaurs.TRex;
import models.paddocks.Paddock;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TestTRex {
    TRex tRex;
    Paddock paddock;
    Park park;

    @Before
    public void before() {
        park = new Park("MADE UP PARK");
        paddock = new Paddock("Paddock 9", SpeciesType.CARNIVORE, park);
        tRex = new TRex("Biggy Smalls", paddock);
        paddock.getDinosaurs().add(tRex);
    }

    @Test
    public void canRampage() {
        assertEquals(1, paddock.countDinosaurs());
        tRex.rampage();
        assertEquals(0, paddock.countDinosaurs());
    }


}
