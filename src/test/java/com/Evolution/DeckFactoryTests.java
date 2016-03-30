package com.Evolution;

import com.Evolution.exceptions.IllegalCardDirectionException;
import com.Evolution.exceptions.WrongFileException;
import com.Evolution.interfaces.ICard;
import com.Evolution.interfaces.IDeck;
import com.Evolution.logic.Deck;
import com.Evolution.logic.DeckFactory;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import static org.junit.Assert.*;

public class DeckFactoryTests {

    @Test
    public void testReadLineToCard() throws IllegalCardDirectionException, IOException, WrongFileException {
        DeckFactory df = new DeckFactory();
        ICard c = df.readLineToCard("Carnivore;Makes a species a carnivore;./carnivore.jpg;3;0");
        assertTrue(c.getName().equals("Carnivore"));
        assertTrue(c.getDesc().equals("Makes a species a carnivore"));
        assertTrue(c.getImgPath().equals("./carnivore.jpg"));
        assertTrue(c.getFood() == 3);
        assertTrue(c.getDirection() == 0);
    }

    @Test
    public void testReadLineToCard2() throws IllegalCardDirectionException, IOException, WrongFileException {
        DeckFactory df = new DeckFactory();
        ICard c = df.readLineToCard("Random;Does random stuff;./random.jpg;2;1");
        assertTrue(c.getName().equals("Random"));
        assertTrue(c.getDesc().equals("Does random stuff"));
        assertTrue(c.getImgPath().equals("./random.jpg"));
        assertTrue(c.getFood() == 2);
        assertTrue(c.getDirection() == 1);
    }

    @Test
    public void testReadFile1() throws IllegalCardDirectionException, IOException, WrongFileException {
        DeckFactory df = new DeckFactory();
        InputStream input = new ByteArrayInputStream("Random;Does random stuff;./random.jpg;2;1".getBytes());
        ArrayList<ICard> c = df.readFile(input);
        assertTrue(c.get(0).getName().equals("Random"));
        assertTrue(c.get(0).getDesc().equals("Does random stuff"));
        assertTrue(c.get(0).getImgPath().equals("./random.jpg"));
        assertTrue(c.get(0).getFood() == 2);
        assertTrue(c.get(0).getDirection() == 1);
    }

    @Test
    public void testReadFile2() throws IllegalCardDirectionException, IOException, WrongFileException {
        DeckFactory df = new DeckFactory();
        InputStream input = new ByteArrayInputStream("asdf;asdf random stuff;./asdf.jpg;2;1".getBytes());
        ArrayList<ICard> c = df.readFile(input);
        assertTrue(c.get(0).getName().equals("asdf"));
        assertTrue(c.get(0).getDesc().equals("asdf random stuff"));
        assertTrue(c.get(0).getImgPath().equals("./asdf.jpg"));
        assertTrue(c.get(0).getFood() == 2);
        assertTrue(c.get(0).getDirection() == 1);
    }

    @Test
    public void testReadFile3() throws IllegalCardDirectionException, IOException, WrongFileException {
        DeckFactory df = new DeckFactory();
        InputStream input = new ByteArrayInputStream("asdf2;asdf2 random stuff;./asdf2.jpg;2;1".getBytes());
        ArrayList<ICard> c = df.readFile(input);
        assertTrue(c.get(0).getName().equals("asdf2"));
        assertTrue(c.get(0).getDesc().equals("asdf2 random stuff"));
        assertTrue(c.get(0).getImgPath().equals("./asdf2.jpg"));
        assertTrue(c.get(0).getFood() == 2);
        assertTrue(c.get(0).getDirection() == 1);
    }

    @Test
    public void testReadFileMultipleLines() throws IOException, IllegalCardDirectionException, WrongFileException {
        DeckFactory df = new DeckFactory();
        InputStream input = new ByteArrayInputStream(("asdf2;asdf2 random stuff;./asdf2.jpg;2;1\n" +
                "Random;Does random stuff;./random.jpg;6;2").getBytes());
        ArrayList<ICard> c = df.readFile(input);
        assertTrue(c.get(0).getName().equals("asdf2"));
        assertTrue(c.get(0).getDesc().equals("asdf2 random stuff"));
        assertTrue(c.get(0).getImgPath().equals("./asdf2.jpg"));
        assertTrue(c.get(0).getFood() == 2);
        assertTrue(c.get(0).getDirection() == 1);

        assertTrue(c.get(1).getName().equals("Random"));
        assertTrue(c.get(1).getDesc().equals("Does random stuff"));
        assertTrue(c.get(1).getImgPath().equals("./random.jpg"));
        assertTrue(c.get(1).getFood() == 6);
        assertTrue(c.get(1).getDirection() == 2);
    }

    @Test(expected = WrongFileException.class)
    public void testIllegalStringFormat1() throws IOException, IllegalCardDirectionException, WrongFileException {
        DeckFactory df = new DeckFactory();
        df.readLineToCard("asdf");
    }
}
