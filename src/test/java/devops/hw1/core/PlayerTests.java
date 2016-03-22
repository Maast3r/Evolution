package devops.hw1.core;

import static org.junit.Assert.*;
import org.junit.Test;

/**
 * Created by goistjt on 3/22/2016.
 */
public class PlayerTests {

    @Test
    public void testPlayerInit() {
        Player p = new Player();
        assertNotNull(p);
    }

    public void testPlayerAddSpecies() {
        Player p = new Player();
        p.addSpecies();
        assertTrue(p.getSpecies().getSize() == 1);
    }
}
