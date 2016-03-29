package com.Evolution;

import com.Evolution.exceptions.IllegalCardDirectionException;
import com.Evolution.interfaces.ICard;
import com.Evolution.interfaces.IDeck;
import com.Evolution.logic.DeckFactory;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.*;

public class DeckFactoryDeckTests {

    @Test
    public void testGenerateDrawPile() throws IOException, IllegalCardDirectionException {
        DeckFactory df = new DeckFactory();
        IDeck<ICard> drawPile = df.generateDrawPile("src/main/resources/cardFiles/cardTestMultiple.txt");
        assertNotNull(drawPile.draw());
        assertNotNull(drawPile.draw());
        // TODO: Add exception handling to prevent drawing from empty deck
    }
}
