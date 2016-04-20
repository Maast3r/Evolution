package com.Evolution;

import com.Evolution.exceptions.*;
import com.Evolution.interfaces.ICard;
import com.Evolution.logic.Card;
import com.Evolution.logic.Species;
import com.Evolution.testClasses.TestCard;
import org.easymock.EasyMock;
import org.junit.Test;

import java.util.ArrayList;

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
    public void testAddFirstTrait() throws SpeciesNumberTraitsException, SpeciesDuplicateTraitException {
        Species s = new Species();
        ICard c = new TestCard();
        s.addTrait(c);
        assertTrue(s.getTraits().contains(c));
    }

    @Test
    public void testAddThreeTraits() throws SpeciesNumberTraitsException, SpeciesDuplicateTraitException, IllegalCardFoodException, IllegalCardDirectionException {
        Species s = new Species();
        for (int i = 0; i < 3; i++) {
            ICard c = new Card(Integer.toString(i), "bar", "foobar", 1, 1);
            s.addTrait(c);
            assertTrue(s.getTraits().contains(c));
        }
        assertTrue(s.getTraits().size() == 3);
    }

    @Test(expected = SpeciesNumberTraitsException.class)
    public void testAddFourTraits() throws SpeciesNumberTraitsException, SpeciesDuplicateTraitException, IllegalCardFoodException, IllegalCardDirectionException {
        Species s = new Species();
        for (int i = 0; i < 4; i++) {
            ICard c = new Card(Integer.toString(i), "bar", "foobar", 1, 1);
            s.addTrait(c);
        }
    }

    @Test(expected = SpeciesDuplicateTraitException.class)
    public void testAddDuplicateTraits() throws SpeciesDuplicateTraitException, IllegalCardFoodException, IllegalCardDirectionException, SpeciesNumberTraitsException {
        Species s = new Species();
        ICard c = new Card("foo", "bar", "foobar", 1, 1);
        ICard c2 = new Card("foo", "bar", "foobar", 1, 1);
        s.addTrait(c);
        s.addTrait(c2);
    }

    @Test
    public void testRemoveSuccessful() throws SpeciesNumberTraitsException, SpeciesDuplicateTraitException {
        Species s = new Species();
        ICard c = EasyMock.niceMock(Card.class);
        s.addTrait(c);
        assertTrue(s.getTraits().size() == 1);
        assertTrue(s.getTraits().contains(c));
        s.removeTrait(c);
        assertTrue(s.getTraits().size() == 0);
        assertTrue(!s.getTraits().contains(c));
    }

    @Test(expected = SpeciesTraitNotFoundException.class)
    public void testRemoveFails() throws SpeciesNumberTraitsException, SpeciesDuplicateTraitException {
        Species s = new Species();
        ICard c = EasyMock.niceMock(Card.class);
        s.removeTrait(c);
    }
}
