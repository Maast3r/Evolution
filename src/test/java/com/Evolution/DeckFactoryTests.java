package com.Evolution;

import com.Evolution.exceptions.IllegalCardDirectionException;
import com.Evolution.interfaces.ICard;
import com.Evolution.logic.Deck;
import com.Evolution.logic.DeckFactory;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import static org.junit.Assert.*;

public class DeckFactoryTests {

    @Test
    public void testReadLineToCard() throws IllegalCardDirectionException, IOException {
        DeckFactory df = new DeckFactory();
        ICard c = df.readLineToCard("Carnivore;Makes a species a carnivore;./carnivore.jpg;3;0");
        assertTrue(c.getName().equals("Carnivore"));
        assertTrue(c.getDesc().equals("Makes a species a carnivore"));
        assertTrue(c.getImgPath().equals("./carnivore.jpg"));
        assertTrue(c.getFood() == 3);
        assertTrue(c.getDirection() == 0);
    }

    @Test
    public void testReadLineToCard2() throws IllegalCardDirectionException, IOException {
        DeckFactory df = new DeckFactory();
        ICard c = df.readLineToCard("Random;Does random stuff;./random.jpg;2;1");
        assertTrue(c.getName().equals("Random"));
        assertTrue(c.getDesc().equals("Does random stuff"));
        assertTrue(c.getImgPath().equals("./random.jpg"));
        assertTrue(c.getFood() == 2);
        assertTrue(c.getDirection() == 1);
    }

    @Test
    public void testReadFile1() throws IllegalCardDirectionException, IOException {
        DeckFactory df = new DeckFactory();
        ArrayList<ICard> c = df.readFile("src/main/resources/cardFiles/cardTest.txt");
        assertTrue(c.get(0).getName().equals("Random"));
        assertTrue(c.get(0).getDesc().equals("Does random stuff"));
        assertTrue(c.get(0).getImgPath().equals("./random.jpg"));
        assertTrue(c.get(0).getFood() == 2);
        assertTrue(c.get(0).getDirection() == 1);
    }

    @Test
    public void testReadFile2() throws IllegalCardDirectionException, IOException {
        DeckFactory df = new DeckFactory();
        ArrayList<ICard> c = df.readFile("src/main/resources/cardFiles/cardTest2.txt");
        assertTrue(c.get(0).getName().equals("asdf"));
        assertTrue(c.get(0).getDesc().equals("asdf random stuff"));
        assertTrue(c.get(0).getImgPath().equals("./asdf.jpg"));
        assertTrue(c.get(0).getFood() == 2);
        assertTrue(c.get(0).getDirection() == 1);
    }

    @Test
    public void testReadFile3() throws IllegalCardDirectionException, IOException {
        DeckFactory df = new DeckFactory();
        ArrayList<ICard> c = df.readFile("src/main/resources/cardFiles/cardTest3.txt");
        assertTrue(c.get(0).getName().equals("asdf2"));
        assertTrue(c.get(0).getDesc().equals("asdf2 random stuff"));
        assertTrue(c.get(0).getImgPath().equals("./asdf2.jpg"));
        assertTrue(c.get(0).getFood() == 2);
        assertTrue(c.get(0).getDirection() == 1);
    }

    @Test
    public void testReadFileMultipleLines() throws IOException, IllegalCardDirectionException {
        DeckFactory df = new DeckFactory();
        ArrayList<ICard> c = df.readFile("src/main/resources/cardFiles/cardTestMultiple.txt");
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

}
