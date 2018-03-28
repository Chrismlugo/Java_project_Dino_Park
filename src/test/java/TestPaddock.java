import models.Dino;
import models.Enums.FoodType;
import models.Enums.SpeciesType;
import models.dinosaurs.TRex;
import models.paddocks.Paddock;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TestPaddock {
    Paddock paddock;
    Dino dino;
    DinoFood food;

    @Before
    public void setUp() throws Exception {
        paddock = new Paddock("Boneyard", SpeciesType.CARNIVORE);
        dino = new TRex();
        food = new DinoFood(FoodType.VEGETARIAN);
        food.setFoodType(FoodType.VEGETARIAN);
        paddock.getDinosaurs().add(dino);
        dino.getBelly().add(food);
    }


    @Test
    public void dinoIsInPaddock() {
        assertEquals(1, paddock.getDinosaurs().size());

    }

    @Test
    public void dinoCanEscape() {
        assertEquals(1, paddock.countDinosaurs());
        paddock.setPaddockSecure(false);
        paddock.breakout();
        assertEquals(0, paddock.countDinosaurs());
    }
}

