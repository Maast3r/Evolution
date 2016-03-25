package com.Evolution;

import com.Evolution.exceptions.IllegalCardDirectionException;
import com.Evolution.logic.ICard;
import com.Evolution.logic.TestCard;
import com.Evolution.logic.Deck;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

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
        testDeck.add(new TestCard("Carnivore", "Makes a species a carnivore", "./carnivore.jpg", 3, 0));
        assertTrue(1 == testDeck.getSize());
    }

    @Test
    public void testDraw() throws IllegalCardDirectionException{
        Deck<ICard> testDeck = new Deck<>();
        ICard testCard = new TestCard("Carnivore", "Makes a species a carnivore", "./carnivore.jpg", 3, 0);
        testDeck.add(testCard);
        assertEquals(testCard, testDeck.draw());
    }

    @Test
    public void testDrawMultiple() throws IllegalCardDirectionException{
        Deck<ICard> testDeck = new Deck<>();
        ICard testCard = new TestCard("Carnivore", "Makes a species a carnivore", "./carnivore.jpg", 3, 0);
        ICard testCard2 = new TestCard("Angry", "Makes a species angry", "./angry.jpg", 10, 2);
        testDeck.add(testCard);
        testDeck.add(testCard2);
        assertEquals(testCard2, testDeck.draw());
        assertEquals(testCard, testDeck.draw());
    }

    @Test
    public void testShuffle() throws IllegalCardDirectionException{
        Deck<ICard> testDeck = new Deck<>();
        for(int i = 0; i < 128; i++){
            ICard testCard = new TestCard(String.valueOf(i),String.valueOf(i), String.valueOf(i), 3, 0);
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
}
