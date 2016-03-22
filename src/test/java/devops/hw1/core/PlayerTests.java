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

    @Test
    public void testPlayerAddSpecies() {
        Player p = new Player();
        p.addSpecies();
        assertTrue(p.getSpecies().size() == 1);
    }

    @Test
    public void testPlayerAddMultiSpecies() {
        Player p = new Player();
        p.addSpecies();
        p.addSpecies();
        p.addSpecies();
        assertTrue(p.getSpecies().size() == 3);
    }
}
