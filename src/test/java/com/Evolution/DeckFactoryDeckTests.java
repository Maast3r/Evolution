package com.Evolution;

import com.Evolution.exceptions.IllegalCardDirectionException;
import com.Evolution.exceptions.WrongFileException;
import com.Evolution.interfaces.ICard;
import com.Evolution.interfaces.IDeck;
import com.Evolution.logic.DeckFactory;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import static org.junit.Assert.*;

public class DeckFactoryDeckTests {

    @Test
    public void testGenerateDrawPile() throws IOException, IllegalCardDirectionException, WrongFileException {
        DeckFactory df = new DeckFactory();
        InputStream input = new ByteArrayInputStream(("asdf2;asdf2 random stuff;./asdf2.jpg;2;1\n" +
                "Random;Does random stuff;./random.jpg;6;2").getBytes());
        IDeck<ICard> drawPile = df.generateDrawPile(input);
        assertNotNull(drawPile.draw());
        assertNotNull(drawPile.draw());
        // TODO: Add exception handling to prevent drawing from empty deck
    }

    @Test
    public void testGenerateDiscardPile() {
        DeckFactory df = new DeckFactory();
        IDeck<ICard> discardPile = df.generateDiscardPile();
        assertTrue(discardPile.getSize() == 0);
    }
}
