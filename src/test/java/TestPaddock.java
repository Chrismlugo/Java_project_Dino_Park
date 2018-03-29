import models.Dino;
import models.Enums.SpeciesType;
import models.Park;
import models.dinosaurs.TRex;
import models.paddocks.Paddock;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class TestPaddock {
    Paddock paddock;
    Dino dino;
    Park park;

    @Before
    public void setUp() throws Exception {
        park = new Park("TEST PARK");
        paddock = new Paddock("Boneyard", SpeciesType.CARNIVORE, park);
        dino = new TRex("Hendo", paddock);
    }

    @Test
    public void paddockStartsEmpty() throws Exception {
        assertEquals(0, paddock.countDinosaurs());
        assertEquals(0, paddock.getFoodStock());
    }

    @Test
    public void paddockCanTakeDino() {
        paddock.addDino(dino);
        assertEquals(1, paddock.countDinosaurs());
    }

    @Test
    public void paddockCanAddFoodStock(){
        paddock.stockPaddock(10);
        assertEquals(10, paddock.getFoodStock());
    }

    @Test
    public void paddockCanFeedDinos(){
        assertEquals(0, dino.getBelly());
        paddock.stockPaddock(20);
        paddock.addDino(dino);
        paddock.feedDinos();
        assertNotEquals(0, dino.getBelly());
    }

    @Test
    public void foodlessPaddockCantFeedDinos(){
        paddock.addDino(dino);
        paddock.feedDinos();
        assertEquals(0, dino.getBelly());
    }

    @Test
    public void dinoCanEscape() {
        paddock.addDino(dino);
        assertEquals(1, paddock.countDinosaurs());
        paddock.setPaddockSecure(false);
        paddock.breakout();
        assertEquals(0, paddock.countDinosaurs());
    }

    @Test
    public void dinoCantBreakoutIfSecure(){
        paddock.addDino(dino);
        paddock.breakout();
        assertEquals(1, paddock.countDinosaurs());
    }
}

