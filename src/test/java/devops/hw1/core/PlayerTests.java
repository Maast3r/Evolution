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
    public void testPlayerAddSpeciesRight() {
        Player p = new Player();
        p.addSpeciesRight(new TestSpecies());
        assertTrue(p.getSpecies().size() == 1);
    }

    @Test
    public void testPlayerAddSpeciesLeft() {
        Player p = new Player();
        p.addSpeciesRight(new TestSpecies());
        ISpecies s = new TestSpecies();
        p.addSpeciesLeft(s);
        assertEquals(s, p.getSpecies().get(0));
    }

    @Test
    public void testPlayerAddMultiSpecies() {
        Player p = new Player();
        p.addSpeciesRight(new TestSpecies());
        p.addSpeciesRight(new TestSpecies());
        p.addSpeciesRight(new TestSpecies());
        assertTrue(p.getSpecies().size() == 3);
    }

    @Test
    public void testPlayerRemSpecies() throws InvalidPlayerSpeciesRemovalException {
        Player p = new Player();
        p.addSpeciesRight(new TestSpecies());
        p.removeSpecies(0);
        assertTrue(p.getSpecies().size() == 0);
    }

    @Test
    public void testPlayerRemMultiSpecies() throws InvalidPlayerSpeciesRemovalException {
        Player p = new Player();
        for (int i = 0; i < 3; i++) {
            p.addSpeciesRight(new TestSpecies());
        }
        TestSpecies ts = (TestSpecies) p.getSpecies().get(0);
        p.removeSpecies(2);
        p.removeSpecies(1);
        assertTrue(p.getSpecies().get(0).equals(ts));
    }

    @Test(expected = InvalidPlayerSpeciesRemovalException.class)
    public void testInvalidRemovalIndex() throws InvalidPlayerSpeciesRemovalException {
        Player p = new Player();
        p.removeSpecies(0);
    }
}
