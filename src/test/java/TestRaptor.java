import models.Enums.SpeciesType;
import models.dinosaurs.Raptor;
import models.paddocks.Paddock;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

public class TestRaptor {
    Paddock paddock;
    Raptor raptor;



    @Before
    public void before() {
        paddock = new Paddock("Raptor Paddock", SpeciesType.CARNIVORE, 10);
        raptor = new Raptor("Blue", SpeciesType.CARNIVORE, paddock);
    }

    @Test
    public void raptorHasPaddock() {
        assertEquals(paddock, raptor.getPaddock());
    }

    @Test
    public void paddockHasRaptor() {
        paddock.getDinosaurs().add(raptor);
        assertEquals(1, paddock.getDinosaurs().size());
    }

    @Test
    public void raptorCanEat() {
        raptor.eat(paddock);
        assertEquals(1, raptor.getBelly());
        assertEquals(9, paddock.getFood_stock());
    }

}
