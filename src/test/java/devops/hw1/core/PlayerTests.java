package devops.hw1.core;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * Created by goistjt on 3/22/2016.
 */
public class PlayerTests {

    @Test
    public void testPlayerInit() {
        TestSpecies s = new TestSpecies();
        Player p = new Player(s);
        assertNotNull(p);
    }

    @Test
    public void testPlayerInitWSpecies() {
        TestSpecies s = new TestSpecies();
        Player p = new Player(s);
        assertEquals(1, p.getSpecies().size());
    }

    @Test
    public void testPlayerAddSpeciesRight() {
        TestSpecies s = new TestSpecies();
        Player p = new Player(s);
        TestSpecies s2 = new TestSpecies();
        p.addSpeciesRight(s2);
        assertEquals(s2, p.getSpecies().get(1));
    }

    @Test
    public void testPlayerAddSpeciesLeft() {
        TestSpecies s = new TestSpecies();
        Player p = new Player(s);
        p.addSpeciesRight(new TestSpecies());
        TestSpecies s2 = new TestSpecies();
        p.addSpeciesLeft(s2);
        assertEquals(s2, p.getSpecies().get(0));
    }

    @Test
    public void testPlayerAddMultiSpecies() {
        TestSpecies s = new TestSpecies();
        Player p = new Player(s);
        p.addSpeciesRight(new TestSpecies());
        p.addSpeciesRight(new TestSpecies());
        assertTrue(p.getSpecies().size() == 3);
    }

    @Test
    public void testPlayerRemSpecies() throws InvalidPlayerSpeciesRemovalException {
        TestSpecies s = new TestSpecies();
        Player p = new Player(s);
        p.addSpeciesRight(new TestSpecies());
        p.removeSpecies(0);
        assertTrue(p.getSpecies().size() == 1);
    }

    @Test
    public void testPlayerRemMultiSpecies() throws InvalidPlayerSpeciesRemovalException {
        TestSpecies s = new TestSpecies();
        Player p = new Player(s);
        for (int i = 0; i < 3; i++) {
            p.addSpeciesRight(new TestSpecies());
        }
        TestSpecies ts = (TestSpecies) p.getSpecies().get(0);
        p.removeSpecies(2);
        p.removeSpecies(1);
        assertTrue(p.getSpecies().get(0).equals(ts));
    }

    @Test(expected = InvalidPlayerSpeciesRemovalException.class)
    public void testInvalidRemovalIndexNone() throws InvalidPlayerSpeciesRemovalException {
        Player p = new Player(new TestSpecies());
        p.removeSpecies(0);
        p.removeSpecies(0);
    }

    @Test(expected = InvalidPlayerSpeciesRemovalException.class)
    public void testInvalidRemovalIndexNegative() throws InvalidPlayerSpeciesRemovalException {
        Player p = new Player(new TestSpecies());
        p.removeSpecies(-1);
    }

    @Test(expected = InvalidPlayerSpeciesRemovalException.class)
    public void testInvalidRemovalIndexNegative2() throws InvalidPlayerSpeciesRemovalException {
        Player p = new Player(new TestSpecies());
        p.removeSpecies(-45);
    }

    @Test(expected = InvalidPlayerSpeciesRemovalException.class)
    public void testInvalidRemovalIndexHigh() throws InvalidPlayerSpeciesRemovalException {
        Player p = new Player(new TestSpecies());
        p.removeSpecies(2);
    }

    @Test(expected = InvalidPlayerSpeciesRemovalException.class)
    public void testInvalidRemovalIndexHigh2() throws InvalidPlayerSpeciesRemovalException {
        Player p = new Player(new TestSpecies());
        p.removeSpecies(2990);
    }

    @Test(expected = InvalidPlayerSpeciesRemovalException.class)
    public void testInvalidRemovalIndexWithOne() throws InvalidPlayerSpeciesRemovalException {
        Player p = new Player(new TestSpecies());
        p.removeSpecies(1);
    }

    @Test(expected = InvalidPlayerSpeciesRemovalException.class)
    public void testInvalidRemovalIndexWithOne2() throws InvalidPlayerSpeciesRemovalException {
        Player p = new Player(new TestSpecies());
        p.removeSpecies(0);
        p.removeSpecies(0);
    }
}
