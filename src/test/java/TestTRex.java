import models.Enums.FoodType;
import models.Enums.SpeciesType;
import models.dinosaurs.TRex;
import models.paddocks.Paddock;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TestTRex {
    TRex tRex;
    Paddock paddock;
    DinoFood dinoFood;

    @Before
    public void before() {

        paddock = new Paddock("Paddock 9", SpeciesType.CARNIVORE);
        tRex = new TRex("Biggy Smalls", paddock);
        dinoFood = new DinoFood(FoodType.PEPPERONI);
        paddock.getFoodStock().add(dinoFood);
        paddock.getDinosaurs().add(tRex);
    }

    @Test
    public void canRampage() {
        assertEquals(1, paddock.countDinosaurs());
        tRex.rampage();
        assertEquals(0, paddock.countDinosaurs());
    }



}
