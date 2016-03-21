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
    public void testIncreasePopulation() {
        Species s = new Species();
        s.increasePopulation();
        assertEquals(2, s.getPopulation());
    }

    @Test
    public void testIncreasePopulationMulti() {
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
    public void testMultiIncreaseDecreasePopulation() {
        Species s = new Species();
        s.increasePopulation();
        s.increasePopulation();
        s.decreasePopulation();
        assertEquals(2, s.getPopulation());
    }

    @Test
    public void testIncreaseBodySize() {
        Species s = new Species();
        s.increaseBodySize();
        assertEquals(2, s.getBodySize());
    }

    @Test
    public void testIncreaseBodySizeMulti() {
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
    public void testMultiIncreaseDecreaseBodySize() {
        Species s = new Species();
        s.increaseBodySize();
        s.increaseBodySize();
        s.decreaseBodySize();
        assertEquals(2, s.getBodySize());
    }
}
