package com.Evolution;

import com.Evolution.exceptions.IllegalCardDirectionException;
import com.Evolution.logic.Card;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Unit tests for the Card class
 * Created by burchtm on 3/21/2016.
 */
public class CardTests {

    @Test
    public void testName() throws IllegalCardDirectionException {
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

    /**
     * BVA - food value must be between -3 and 9
     * This is the test for the lower boundary
     */
    @Test
    public void testFood2() throws IllegalCardDirectionException {
        Card testCard = new Card("Carnivore", "Makes a species a carnivore", "./carnivore.jpg", -3, 0);
        assertEquals(testCard.getFood(), -3);
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
    
    @Test(expected = IllegalCardDirectionException.class)
    public void testValidCardDirection2() throws IllegalCardDirectionException{
    	Card testCard = new Card("Carnivore", "Makes a species a carnivore", "./carnivore.jpg", 3, -1234);
    }
    
    @Test(expected = IllegalCardDirectionException.class)
    public void testValidCardDirection3() throws IllegalCardDirectionException{
    	Card testCard = new Card("Carnivore", "Makes a species a carnivore", "./carnivore.jpg", 3, 3);
    }
    
    @Test(expected = IllegalCardDirectionException.class)
    public void testValidCardDirection4() throws IllegalCardDirectionException{
    	Card testCard = new Card("Carnivore", "Makes a species a carnivore", "./carnivore.jpg", 3, 234);
    }
    
}
