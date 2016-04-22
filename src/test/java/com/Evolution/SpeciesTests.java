package com.Evolution;

import com.Evolution.exceptions.*;
import com.Evolution.interfaces.ICard;
import com.Evolution.logic.Card;
import com.Evolution.logic.Species;
import com.Evolution.testClasses.TestCard;
import org.easymock.EasyMock;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.*;

/**
 * Tests for the Species implementation
 * Created by goistjt on 3/21/2016.
 */
@RunWith(Parameterized.class)
public class SpeciesTests {

    private int popIncrease;
    private int popDecrease;
    private int bodyIncrease;

    public SpeciesTests(int popIncrease, int popDecrease, int bodyIncrease) {
        this.popIncrease = popIncrease;
        this.popDecrease = popDecrease;
        this.bodyIncrease = bodyIncrease;
    }

    @Parameterized.Parameters
    public static Collection playersToCheck() {
        return Arrays.asList(new Object[][]{
                {1, 1, 1}, {2, 2, 2}, {1, 2, 3},
                {2, 1, 4}, {6, 1, 1}, {1, 1, 6},
                {0, 2, 2}, {2, 4, 3}
        });
    }

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
        try {
            for (int i = 0; i < this.popIncrease; i++) {
                s.increasePopulation();
            }
            assertEquals(this.popIncrease + 1, s.getPopulation());
        }  catch (SpeciesPopulationException e) {
            if(!(this.popIncrease > 5)){
                fail();
            }
        }
    }


    @Test
    public void testDecreasePopulation() throws SpeciesPopulationException {
        Species s = new Species();
        try {
            for (int i = 0; i < this.popIncrease; i++) {
                s.increasePopulation();
            }
            for (int i = 0; i < this.popDecrease; i++) {
                s.decreasePopulation();
            }
            assertEquals(1 + this.popIncrease - this.popDecrease, s.getPopulation());
        } catch (SpeciesPopulationException e){
            if(!(this.popIncrease > 5) && !(this.popIncrease - this.popDecrease + 1 < 0)){
                fail();
            }
        }
    }

    @Test
    public void testIncreaseBodySize() throws SpeciesBodySizeException {
        Species s = new Species();
        try {
            for (int i = 0; i < this.bodyIncrease; i++) {
                s.increaseBodySize();
            }
            assertEquals(1 + this.bodyIncrease, s.getBodySize());
        }catch(SpeciesBodySizeException e){
            if(!(this.bodyIncrease > 5)){
                fail();
            }
        }
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

    @Test
    public void testAddFirstTrait() throws SpeciesNumberTraitsException, SpeciesDuplicateTraitException, NullGameObjectException {
        Species s = new Species();
        ICard c = new TestCard();
        s.addTrait(c);
        assertTrue(s.getTraits().contains(c));
    }

    @Test
    public void testAddThreeTraits() throws SpeciesNumberTraitsException, SpeciesDuplicateTraitException, IllegalCardFoodException, IllegalCardDirectionException, NullGameObjectException {
        Species s = new Species();
        for (int i = 0; i < 3; i++) {
            ICard c = new Card(Integer.toString(i), "bar", "foobar", 1, 1);
            s.addTrait(c);
            assertTrue(s.getTraits().contains(c));
        }
        assertTrue(s.getTraits().size() == 3);
    }

    @Test(expected = SpeciesNumberTraitsException.class)
    public void testAddFourTraits() throws SpeciesNumberTraitsException, SpeciesDuplicateTraitException, IllegalCardFoodException, IllegalCardDirectionException, NullGameObjectException {
        Species s = new Species();
        for (int i = 0; i < 4; i++) {
            ICard c = new Card(Integer.toString(i), "bar", "foobar", 1, 1);
            s.addTrait(c);
        }
    }

    @Test(expected = SpeciesDuplicateTraitException.class)
    public void testAddDuplicateTraits() throws SpeciesDuplicateTraitException, IllegalCardFoodException, IllegalCardDirectionException, SpeciesNumberTraitsException, NullGameObjectException {
        Species s = new Species();
        ICard c = new Card("foo", "bar", "foobar", 1, 1);
        ICard c2 = new Card("foo", "bar", "foobar", 1, 1);
        s.addTrait(c);
        s.addTrait(c2);
    }

    @Test
    public void testRemoveSuccessful() throws SpeciesNumberTraitsException, SpeciesDuplicateTraitException,
            SpeciesTraitNotFoundException, NullGameObjectException {
        Species s = new Species();
        ICard c = EasyMock.niceMock(Card.class);
        EasyMock.expect(c.getName()).andReturn("FOO");
        EasyMock.expect(c.getName()).andReturn("FOO");
        EasyMock.expect(c.getName()).andReturn("FOO");
        EasyMock.expect(c.getName()).andReturn("FOO");
        EasyMock.replay(c);
        s.addTrait(c);
        assertTrue(s.getTraits().size() == 1);
        assertTrue(s.getTraits().contains(c));
        s.removeTrait(c);
        assertTrue(s.getTraits().size() == 0);
        assertTrue(!s.getTraits().contains(c));
        EasyMock.verify(c);
    }

    @Test(expected = SpeciesTraitNotFoundException.class)
    public void testRemoveFails() throws SpeciesNumberTraitsException, SpeciesDuplicateTraitException,
            SpeciesTraitNotFoundException {
        Species s = new Species();
        ICard c = EasyMock.niceMock(Card.class);
        s.removeTrait(c);
    }

    @Test(expected = NullGameObjectException.class)
    public void testAddNullTrait() throws SpeciesNumberTraitsException, SpeciesDuplicateTraitException, NullGameObjectException {
        Species s = new Species();
        s.addTrait(null);
    }
}
