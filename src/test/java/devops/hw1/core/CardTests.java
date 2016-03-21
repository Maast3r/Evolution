package devops.hw1.core;

import org.junit.Test;

import static org.junit.Assert.*;
import static org.junit.Assert.assertTrue;

/**
 * Unit tests for the Card class
 * Created by burchtm on 3/21/2016.
 */
public class CardTests {

    @Test
    public void testName(){
        Card testCard = new Card("Carnivore", "Makes a species a carnivore", "./carnivore.jpg", 3);
        assertTrue("Carnivore".equals(testCard.getName()));
    }

    @Test
    public void testDesc(){
        Card testCard = new Card("Carnivore", "Makes a species a carnivore", "./carnivore.jpg", 3);
        assertTrue("Makes a species a carnivore".equals(testCard.getDesc()));
    }

    @Test
    public void testImgPath(){
        Card testCard = new Card("Carnivore", "Makes a species a carnivore", "./carnivore.jpg", 3);
        assertTrue("./carnivore.jpg".equals(testCard.getImgPath()));
    }

    @Test
    public void testFood(){
        Card testCard = new Card("Carnivore", "Makes a species a carnivore", "./carnivore.jpg", 3);
        assertEquals(testCard.getFood(), 3);
    }
}
