import models.Enums.FoodType;
import models.Enums.SpeciesType;
import models.Enums.StomachSize;
import models.dinosaurs.Diplodocus;
import models.dinosaurs.Raptor;
import models.dinosaurs.Triceratops;
import models.paddocks.Paddock;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNull;

public class TestDino {

    private Diplodocus diplodocus;
    private Raptor raptor;
    private Triceratops triceratops;
    private Paddock herbypaddock;
    private Paddock carnipaddock;

    @Before
    public void setup(){

        herbypaddock = new Paddock("Leafy Green", SpeciesType.HERBIVORE);
        carnipaddock = new Paddock("Meat City", SpeciesType.CARNIVORE);
        diplodocus = new Diplodocus("Brian", herbypaddock);
        raptor = new Raptor("Will Smith", carnipaddock);
        triceratops = new Triceratops("Trunko", herbypaddock);
    }

    @Test
    public void dinosHaveNames(){
        assertEquals("Brian", diplodocus.getName());
        assertEquals("Will Smith", raptor.getName());
        assertEquals("Trunko", triceratops.getName());
    }

    @Test
    public void dinosHaveSpeciesTypes(){
        assertEquals(SpeciesType.HERBIVORE, triceratops.getSpecies());
        assertEquals(SpeciesType.HERBIVORE, diplodocus.getSpecies());
        assertEquals(SpeciesType.CARNIVORE, raptor.getSpecies());
    }

    @Test
    public void dinoBelliesStartEmpty(){
        assertEquals(0, triceratops.getBelly());
    }

    @Test
    public void dinosCanEat(){
        raptor.feed();
        assertEquals(1, raptor.getBelly());
    }

    @Test
    public void dinosHaveStomachSizes(){
        assertEquals(StomachSize.LARGE, triceratops.getStomachSize());
    }

    @Test
    public void dinosHaveStomachCapacity(){
        assertEquals(5, diplodocus.stomachCapacity());
        assertEquals(2, raptor.stomachCapacity());
    }

    @Test
    public void dinosCannotExceedStomachCapacity(){
        raptor.feed();
        raptor.feed();
        assertEquals(2, raptor.getBelly());
        raptor.feed();
        assertEquals(2, raptor.getBelly());
    }
}
