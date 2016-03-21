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
        Deck<Card> testDeck = new Deck<Card>();
        assertTrue(0 == testDeck.getSize());
    }
}
