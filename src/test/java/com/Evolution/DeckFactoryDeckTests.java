package com.Evolution;

import com.Evolution.exceptions.DeckEmptyException;
import com.Evolution.exceptions.IllegalCardDirectionException;
import com.Evolution.exceptions.WrongFileException;
import com.Evolution.interfaces.ICard;
import com.Evolution.interfaces.IDeck;
import com.Evolution.logic.DeckFactory;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class DeckFactoryDeckTests {

    @Test
    public void testGenerateDrawPile() throws IOException, IllegalCardDirectionException, WrongFileException, DeckEmptyException {
        DeckFactory df = new DeckFactory();
        InputStream input = new ByteArrayInputStream(("asdf2;asdf2 random stuff;./asdf2.png;2;1\n" +
                "Random;Does random stuff;./random.png;6;2").getBytes());
        IDeck<ICard> drawPile = df.generateDrawPile(input);
        assertNotNull(drawPile.draw());
        assertNotNull(drawPile.draw());
    }

    @Test
    public void testGenerateDiscardPile() {
        DeckFactory df = new DeckFactory();
        IDeck<ICard> discardPile = df.generateDiscardPile();
        assertTrue(discardPile.getSize() == 0);
    }
}
