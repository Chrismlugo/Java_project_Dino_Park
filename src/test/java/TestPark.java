import models.Park;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertNotEquals;

public class TestPark {
    Park park;

    @Before
    public void setUp() throws Exception {
        park = new Park("Dino Park");
    }

    @Test
    public void canAddVisitors() {
        park.addVisitors();
        assertNotEquals(0, park.countVisitors());

    }
}
