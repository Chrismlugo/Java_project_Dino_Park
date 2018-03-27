import models.Enums.SpeciesType;
import models.dinosaurs.Diplodocus;
import models.paddocks.Paddock;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TestDiplodocus {
    Diplodocus diplodocus;
    Paddock paddock;

    @Before
    public void setUp() throws Exception {
        paddock = new Paddock("herb's green", SpeciesType.HERBIVORE);
        diplodocus = new Diplodocus("Dave",paddock);
    }

    @Test
    public void hasName() {
        assertEquals("Dave", diplodocus.getName());
    }

    @Test
    public void hasPaddock() {
        assertEquals(paddock, diplodocus.getPaddock());
        paddock.getDinosaurs().add(diplodocus);
        assertEquals(1, paddock.getDinosaurs().size());
    }

    @Test
    public void hasSpeciesType() {
        assertEquals(SpeciesType.HERBIVORE, diplodocus.getSpecies());
    }

    @Test
    public void hasInitiallyEmptyBelly() {
        assertEquals(0, diplodocus.getBelly());
    }

    @Test
    public void canFeedFromPaddock() {

    }
}
