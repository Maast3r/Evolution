package devops.hw1.core;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Unit tests for the Card class
 * Created by burchtm on 3/21/2016.
 */
public class CardTests {

    @Test
    public void testName() throws IllegalCardDirectionException{
        Card testCard = new Card("Carnivore", "Makes a species a carnivore", "./carnivore.jpg", 3, 0);
        assertTrue("Carnivore".equals(testCard.getName()));
    }

    @Test
    public void testDesc() throws IllegalCardDirectionException{
        Card testCard = new Card("Carnivore", "Makes a species a carnivore", "./carnivore.jpg", 3, 0);
        assertTrue("Makes a species a carnivore".equals(testCard.getDesc()));
    }

    @Test
    public void testImgPath() throws IllegalCardDirectionException{
        Card testCard = new Card("Carnivore", "Makes a species a carnivore", "./carnivore.jpg", 3, 0);
        assertTrue("./carnivore.jpg".equals(testCard.getImgPath()));
    }

    @Test
    public void testFood() throws IllegalCardDirectionException{
        Card testCard = new Card("Carnivore", "Makes a species a carnivore", "./carnivore.jpg", 3, 0);
        assertEquals(testCard.getFood(), 3);
    }

    @Test
    public void testDirection() throws IllegalCardDirectionException{
        Card testCard = new Card("Carnivore", "Makes a species a carnivore", "./carnivore.jpg", 3, 0);
        assertEquals(testCard.getDirection(), 0);
    }
    
    @Test(expected = IllegalCardDirectionException.class)
    public void testValidCardDirection1() throws IllegalCardDirectionException{
    	Card testCard = new Card("Carnivore", "Makes a species a carnivore", "./carnivore.jpg", 3, -1);
    }
    
}
