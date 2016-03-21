package devops.hw1.core;

import org.junit.Test;

import static org.junit.Assert.*;
import static org.junit.Assert.assertTrue;

/**
 * Tests the Deck class
 * Created by burchtm on 3/21/2016.
 */
public class DeckTests {

    @Test
    public void testSize0(){
        Deck<Card> testDeck = new Deck<>();
        assertTrue(0 == testDeck.getSize());
    }

    @Test
    public void testSizeOver0(){
        Deck<Card> testDeck = new Deck<>();
        testDeck.add(new Card("Carnivore", "Makes a species a carnivore", "./carnivore.jpg", 3, 0));
        assertTrue(1 == testDeck.getSize());
    }

    @Test
    public void testDraw(){
        Deck<Card> testDeck = new Deck<>();
        Card testCard = new Card("Carnivore", "Makes a species a carnivore", "./carnivore.jpg", 3, 0);
        testDeck.add(testCard);
        assertEquals(testCard, testDeck.draw());
    }

    @Test
    public void testDrawMultiple(){
        Deck<Card> testDeck = new Deck<>();
        Card testCard = new Card("Carnivore", "Makes a species a carnivore", "./carnivore.jpg", 3, 0);
        Card testCard2 = new Card("Angry", "Makes a species angry", "./angry.jpg", 10, 4);
        testDeck.add(testCard);
        testDeck.add(testCard2);
        assertEquals(testCard2, testDeck.draw());
        assertEquals(testCard, testDeck.draw());
    }
}
