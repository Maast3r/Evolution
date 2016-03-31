package com.Evolution;


import com.Evolution.exceptions.DeckEmptyException;
import com.Evolution.exceptions.IllegalCardDirectionException;
import com.Evolution.interfaces.ICard;
import com.Evolution.logic.Deck;
import com.Evolution.testClasses.TestCard;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Tests the Deck class
 * Created by burchtm on 3/21/2016.
 */
public class DeckTests {

    @Test
    public void testSize0(){
        Deck<ICard> testDeck = new Deck<>();
        assertTrue(0 == testDeck.getSize());
    }

    @Test
    public void testSizeOver0() throws IllegalCardDirectionException {
        Deck<ICard> testDeck = new Deck<>();
        testDeck.add(new TestCard());
        assertTrue(1 == testDeck.getSize());
    }

    @Test
    public void testDraw() throws IllegalCardDirectionException, DeckEmptyException {
        Deck<ICard> testDeck = new Deck<>();
        ICard testCard = new TestCard();
        testDeck.add(testCard);
        assertEquals(testCard, testDeck.draw());
    }

    @Test
    public void testDiscard() throws IllegalCardDirectionException{
        Deck<ICard> testDeck = new Deck<>();
        ICard testCard = new TestCard();
        assertFalse(testDeck.contains(testCard));
        testDeck.discard(testCard);
        assertTrue(testDeck.contains(testCard));
    }

    @Test
    public void testDrawMultiple() throws IllegalCardDirectionException, DeckEmptyException {
        Deck<ICard> testDeck = new Deck<>();
        ICard testCard = new TestCard();
        ICard testCard2 = new TestCard();
        testDeck.add(testCard);
        testDeck.add(testCard2);
        assertEquals(testCard2, testDeck.draw());
        assertEquals(testCard, testDeck.draw());
    }

    @Test
    public void testShuffle() throws IllegalCardDirectionException{
        Deck<ICard> testDeck = new Deck<>();
        for(int i = 0; i < 128; i++){
            ICard testCard = new TestCard();
            testDeck.add(testCard);
        }
        float countPasses = 0;
        for(int j = 0; j < 1000; j++) {
            ICard original = testDeck.get(32);
            testDeck.shuffle();
            if(!original.equals(testDeck.get(32))){
                countPasses++;
            }
        }
        assertTrue((countPasses/1000) > .97);
    }

    @Test(expected = DeckEmptyException.class)
    public void testDeckEmpty() throws DeckEmptyException {
        Deck<ICard> d = new Deck<>();
        d.draw();
    }
}
