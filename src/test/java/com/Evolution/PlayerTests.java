package com.Evolution;

import com.Evolution.exceptions.IllegalCardRemovalException;
import com.Evolution.exceptions.InvalidPlayerSpeciesRemovalException;
import com.Evolution.exceptions.NullGameObjectException;
import com.Evolution.interfaces.ICard;
import com.Evolution.logic.Player;
import com.Evolution.logic.Species;
import com.Evolution.testClasses.TestCard;
import com.Evolution.testClasses.TestSpecies;
import org.easymock.EasyMock;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.Assert.*;

/**
 * Tests for the Player implementation
 * Created by goistjt on 3/22/2016.
 */
public class PlayerTests {

    @Test
    public void testPlayerInit() throws NullGameObjectException {
        TestSpecies s = new TestSpecies();
        Player p = new Player(s);
        assertNotNull(p);
    }

    @Test
    public void testPlayerInitWSpecies() throws NullGameObjectException {
        TestSpecies s = new TestSpecies();
        Player p = new Player(s);
        assertEquals(1, p.getSpecies().size());
    }

    @Test
    public void testPlayerAddSpeciesRight() throws NullGameObjectException {
        TestSpecies s = new TestSpecies();
        Player p = new Player(s);
        TestSpecies s2 = new TestSpecies();
        p.addSpeciesRight(s2);
        assertEquals(s2, p.getSpecies().get(1));
    }

    @Test
    public void testPlayerAddSpeciesLeft() throws NullGameObjectException {
        TestSpecies s = new TestSpecies();
        Player p = new Player(s);
        p.addSpeciesRight(new TestSpecies());
        TestSpecies s2 = new TestSpecies();
        p.addSpeciesLeft(s2);
        assertEquals(s2, p.getSpecies().get(0));
    }

    @Test
    public void testPlayerAddMultiSpecies() throws NullGameObjectException {
        TestSpecies s = new TestSpecies();
        Player p = new Player(s);
        p.addSpeciesRight(new TestSpecies());
        p.addSpeciesRight(new TestSpecies());
        assertTrue(p.getSpecies().size() == 3);
    }

    @Test
    public void testPlayerRemSpecies() throws InvalidPlayerSpeciesRemovalException, NullGameObjectException {
        TestSpecies s = new TestSpecies();
        Player p = new Player(s);
        p.addSpeciesRight(new TestSpecies());
        p.removeSpecies(0);
        assertTrue(p.getSpecies().size() == 1);
    }

    @Test
    public void testPlayerRemMultiSpecies() throws InvalidPlayerSpeciesRemovalException, NullGameObjectException {
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
    public void testInvalidRemovalIndexNone() throws InvalidPlayerSpeciesRemovalException, NullGameObjectException {
        Player p = new Player(new TestSpecies());
        p.removeSpecies(0);
        p.removeSpecies(0);
    }

    @Test(expected = InvalidPlayerSpeciesRemovalException.class)
    public void testInvalidRemovalIndexNegative() throws InvalidPlayerSpeciesRemovalException, NullGameObjectException {
        Player p = new Player(new TestSpecies());
        p.removeSpecies(-1);
    }

    @Test(expected = InvalidPlayerSpeciesRemovalException.class)
    public void testInvalidRemovalIndexNegative2() throws InvalidPlayerSpeciesRemovalException, NullGameObjectException {
        Player p = new Player(new TestSpecies());
        p.removeSpecies(-45);
    }

    @Test(expected = InvalidPlayerSpeciesRemovalException.class)
    public void testInvalidRemovalIndexHigh() throws InvalidPlayerSpeciesRemovalException, NullGameObjectException {
        Player p = new Player(new TestSpecies());
        p.removeSpecies(2);
    }

    @Test(expected = InvalidPlayerSpeciesRemovalException.class)
    public void testInvalidRemovalIndexHigh2() throws InvalidPlayerSpeciesRemovalException, NullGameObjectException {
        Player p = new Player(new TestSpecies());
        p.removeSpecies(2990);
    }

    @Test(expected = InvalidPlayerSpeciesRemovalException.class)
    public void testInvalidRemovalIndexWithOne() throws InvalidPlayerSpeciesRemovalException, NullGameObjectException {
        Player p = new Player(new TestSpecies());
        p.removeSpecies(1);
    }

    @Test(expected = InvalidPlayerSpeciesRemovalException.class)
    public void testInvalidRemovalIndexWithOne2() throws InvalidPlayerSpeciesRemovalException, NullGameObjectException {
        Player p = new Player(new TestSpecies());
        p.removeSpecies(0);
        p.removeSpecies(0);
    }

    @Test
    public void testGetNoCards() throws NullGameObjectException {
        Player p = new Player(new TestSpecies());
        assertTrue(p.getCards().size() == 0);
    }

    @Test
    public void testGetAllPlayerCards() throws NullGameObjectException {
        Player p = new Player(new TestSpecies());
        ArrayList<ICard> expected = new ArrayList<>();
        ICard card1 = new TestCard();
        ICard card2 = new TestCard();
        ICard card3 = new TestCard();
        expected.add(card1);
        expected.add(card2);
        expected.add(card3);
        p.addCardToHand(card1);
        p.addCardToHand(card2);
        p.addCardToHand(card3);
        assertTrue(Arrays.equals(p.getCards().toArray(), expected.toArray()));
    }

    @Test
    public void testDiscard() throws IllegalCardRemovalException, NullGameObjectException {
        Player p = new Player(new TestSpecies());
        ICard card = new TestCard();
        p.addCardToHand(card);
        assertTrue(p.getCards().contains(card));
        p.removeCardFromHand(card);
        assertTrue(!p.getCards().contains(card));
    }

    @Test(expected = IllegalCardRemovalException.class)
    public void testInvalidDiscard() throws IllegalCardRemovalException, NullGameObjectException {
        Player p = new Player(new TestSpecies());
        ICard card = new TestCard();
        assertTrue(!p.getCards().contains(card));
        p.removeCardFromHand(card);
    }

    @Test(expected = NullGameObjectException.class)
    public void testAddNullCardToHand() throws NullGameObjectException {
        Player p = new Player(EasyMock.niceMock(Species.class));
        p.addCardToHand(null);
    }

    @Test(expected = NullGameObjectException.class)
    public void testRemoveNullCardToHand() throws NullGameObjectException, IllegalCardRemovalException {
        Player p = new Player(EasyMock.niceMock(Species.class));
        p.removeCardFromHand(null);
    }

    @Test(expected = NullGameObjectException.class)
    public void testInitNull() throws NullGameObjectException {
        Player p = new Player(null);
    }

    @Test(expected = NullGameObjectException.class)
    public void testAddRightNullSpecies() throws NullGameObjectException {
        Player p = new Player(EasyMock.niceMock(Species.class));
        p.addSpeciesRight(null);
    }

    @Test(expected = NullGameObjectException.class)
    public void testAddLeftNullSpecies() throws NullGameObjectException {
        Player p = new Player(EasyMock.niceMock(Species.class));
        p.addSpeciesLeft(null);
    }

    @Test
    public void testMoveFood() throws NullGameObjectException {
        Species s = EasyMock.niceMock(Species.class);
        EasyMock.expect(s.getEatenFood()).andReturn(0);
        s.resetEatenFood();
        EasyMock.replay(s);
        Player p = new Player(s);
        p.moveFoodToFoodBag();
        assertEquals(0, p.getFoodBag());
        EasyMock.verify(s);
    }

    @Test
    public void testMoveFoodMultiple() throws NullGameObjectException {
        Species s = EasyMock.niceMock(Species.class);
        EasyMock.expect(s.getEatenFood()).andReturn(1);
        s.resetEatenFood();
        EasyMock.replay(s);
        Player p = new Player(s);
        for (int i = 0; i < 2; i++) {
            s = EasyMock.niceMock(Species.class);
            EasyMock.expect(s.getEatenFood()).andReturn(1);
            s.resetEatenFood();
            EasyMock.replay(s);
            p.addSpeciesLeft(s);
        }
        p.moveFoodToFoodBag();
        assertEquals(3, p.getFoodBag());
        EasyMock.verify();
    }

    @Test
    public void testAllSpeciesFull() throws NullGameObjectException {
        Species s = EasyMock.niceMock(Species.class);
        Player p = new Player(s);
        EasyMock.expect(s.isFull()).andReturn(true);
        EasyMock.replay();
        assertTrue(p.allSpeciesFull());
        EasyMock.verify();
    }
}
