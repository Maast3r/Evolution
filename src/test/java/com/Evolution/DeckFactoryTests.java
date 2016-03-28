package com.Evolution;

import com.Evolution.interfaces.ICard;
import com.Evolution.logic.Deck;
import com.Evolution.logic.DeckFactory;
import org.junit.Test;

import java.io.ByteArrayInputStream;

import static org.junit.Assert.*;

/**
 * Created by goistjt on 3/28/2016.
 */
public class DeckFactoryTests {

    @Test
    public void testReadLineToCard() {
        DeckFactory df = new DeckFactory();
        ICard c = df.readLineToCard(
                new ByteArrayInputStream("Carnivore;Makes a species a carnivore;./carnivore.jpg;3;0".getBytes()));
        assertTrue(c.getName().equals("Carnivore"));
        assertTrue(c.getDesc().equals("Makes a species a carnivore"));
        assertTrue(c.getImgPath().equals("./carnivore.jpg"));
        assertTrue(c.getFood() == 3);
        assertTrue(c.getDirection() == 0);

    }

}
