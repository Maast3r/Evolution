package devops.hw1.core;

import static org.junit.Assert.*;
import org.junit.Test;

/**
 * Created by goistjt on 3/21/2016.
 */
public class SpeciesTests {

    @Test
    public void testBodySize() {
        Species s = new Species();
        assertEquals(1, s.getBodySize());
    }

    @Test
    public void testPopulation() {
        Species s = new Species();
        assertEquals(1, s.getPopulation());
    }

    @Test
    public void testIncreasePopulation() throws SpeciesPopulationException {
        Species s = new Species();
        s.increasePopulation();
        assertEquals(2, s.getPopulation());
    }

    @Test
    public void testIncreasePopulationMulti() throws SpeciesPopulationException {
        Species s = new Species();
        s.increasePopulation();
        s.increasePopulation();
        assertEquals(3, s.getPopulation());
    }

    @Test
    public void testDecreasePopulation() {
        Species s = new Species();
        s.decreasePopulation();
        assertEquals(0, s.getPopulation());
    }

    @Test
    public void testMultiIncreaseDecreasePopulation() throws SpeciesPopulationException {
        Species s = new Species();
        s.increasePopulation();
        s.increasePopulation();
        s.decreasePopulation();
        assertEquals(2, s.getPopulation());
    }

    @Test
    public void testIncreaseBodySize() throws SpeciesBodySizeException {
        Species s = new Species();
        s.increaseBodySize();
        assertEquals(2, s.getBodySize());
    }

    @Test
    public void testIncreaseBodySizeMulti() throws SpeciesBodySizeException {
        Species s = new Species();
        s.increaseBodySize();
        s.increaseBodySize();
        assertEquals(3, s.getBodySize());
    }

    @Test
    public void testDecreaseBodySize() {
        Species s = new Species();
        s.decreaseBodySize();
        assertEquals(0, s.getBodySize());
    }

    @Test
    public void testMultiIncreaseDecreaseBodySize() throws SpeciesBodySizeException {
        Species s = new Species();
        s.increaseBodySize();
        s.increaseBodySize();
        s.decreaseBodySize();
        assertEquals(2, s.getBodySize());
    }

    @Test
    public void testIsDeadWithPopulationAndBody() {
        Species s = new Species();
        assertFalse(s.isDead());
    }

    @Test
    public void testIsDeadWOPopulation() {
        Species s = new Species();
        s.decreasePopulation();
        assertTrue(s.isDead());
    }

    @Test
    public void testIsDeadWOBody() {
        Species s = new Species();
        s.decreaseBodySize();
        assertTrue(s.isDead());
    }

    @Test(expected = SpeciesPopulationException.class)
    public void testTooBigPop() throws SpeciesPopulationException {
        Species s = new Species();
        s.increasePopulation();
        s.increasePopulation();
        s.increasePopulation();
        s.increasePopulation();
        s.increasePopulation();
        s.increasePopulation();
        s.increasePopulation();
    }

    @Test(expected = SpeciesBodySizeException.class)
    public void testTooBigSize() throws SpeciesBodySizeException {
        Species s = new Species();
        s.increaseBodySize();
        s.increaseBodySize();
        s.increaseBodySize();
        s.increaseBodySize();
        s.increaseBodySize();
        s.increaseBodySize();
        s.increaseBodySize();
    }

    @Test(expected = SpeciesPopulationException.class)
    public void testTooSmallPop() throws SpeciesPopulationException {
        Species s = new Species();
        s.decreasePopulation();
    }
}
