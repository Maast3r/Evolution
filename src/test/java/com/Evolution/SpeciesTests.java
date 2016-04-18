package com.Evolution;

import com.Evolution.exceptions.SpeciesBodySizeException;
import com.Evolution.exceptions.SpeciesPopulationException;
import com.Evolution.interfaces.ICard;
import com.Evolution.logic.Species;
import com.Evolution.testClasses.TestCard;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Tests for the Species implementation
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
    public void testDecreasePopulation() throws SpeciesPopulationException {
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
    public void testIsDeadWithPopulationAndBody() {
        Species s = new Species();
        assertFalse(s.isDead());
    }

    @Test
    public void testIsDeadWOPopulation() throws SpeciesPopulationException {
        Species s = new Species();
        s.decreasePopulation();
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
        s.decreasePopulation();
    }

    @Test
    public void testAddFirstTrait() throws Exception {
        Species s = new Species();
        ICard c = new TestCard();
        s.addTrait(c);
        assertTrue(s.getTraits().contains(c));
    }

    @Test
    public void testAddThreeTraits() throws Exception {
        Species s = new Species();
        for(int i = 0; i < 3; i++) {
            ICard c = new TestCard();
            s.addTrait(c);
            assertTrue(s.getTraits().contains(c));
        }
    }

    @Test(expected = Exception.class)
    public void testAddFourTraits() throws Exception {
        Species s = new Species();
        for(int i = 0; i < 4; i++) {
            ICard c = new TestCard();
            s.addTrait(c);
        }
    }
}
