import models.DinoFood.DinoFood;
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
    private DinoFood food;

    @Before
    public void setup(){

        herbypaddock = new Paddock("Leafy Green", SpeciesType.HERBIVORE);
        carnipaddock = new Paddock("Meat City", SpeciesType.CARNIVORE);
        diplodocus = new Diplodocus("Brian", herbypaddock);
        raptor = new Raptor("Will Smith", carnipaddock);
        triceratops = new Triceratops("Trunko", herbypaddock);
        food = new DinoFood();
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
        assertEquals(0, triceratops.getBelly().size());
    }

    @Test
    public void dinosCanEat(){
        raptor.feed(food);
        assertEquals(1, raptor.getBelly().size());
    }

    @Test
    public void dinosHaveStomachSizes(){
        assertEquals(StomachSize.LARGE, triceratops.getStomachSize());
    }

    @Test
    public void dinosHaveStomachCapacity(){
        assertEquals(5, diplodocus.getStomachCapacity());
        assertEquals(2, raptor.getStomachCapacity());
    }

    @Test
    public void dinosCannotExceedStomachCapacity(){
        raptor.feed(food);
        raptor.feed(food);
        assertEquals(2, raptor.getBelly().size());
        raptor.feed(food);
        assertEquals(2, raptor.getBelly().size());
    }
}
