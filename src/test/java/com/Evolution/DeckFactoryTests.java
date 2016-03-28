package com.Evolution;

import com.Evolution.exceptions.IllegalCardDirectionException;
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
    public void testReadLineToCard() throws IllegalCardDirectionException {
        DeckFactory df = new DeckFactory();
        ICard c = df.readLineToCard(
                new ByteArrayInputStream("Carnivore;Makes a species a carnivore;./carnivore.jpg;3;0".getBytes()));
        assertTrue(c.getName().equals("Carnivore"));
        assertTrue(c.getDesc().equals("Makes a species a carnivore"));
        assertTrue(c.getImgPath().equals("./carnivore.jpg"));
        assertTrue(c.getFood() == 3);
        assertTrue(c.getDirection() == 0);
    }

    @Test
    public void testReadLineToCard2() throws IllegalCardDirectionException {
        DeckFactory df = new DeckFactory();
        ICard c = df.readLineToCard(
                new ByteArrayInputStream("Random;Does random stuff;./random.jpg;2;1".getBytes()));
        assertTrue(c.getName().equals("Random"));
        assertTrue(c.getDesc().equals("Does random stuff"));
        assertTrue(c.getImgPath().equals("./random.jpg"));
        assertTrue(c.getFood() == 2);
        assertTrue(c.getDirection() == 1);
    }

}
