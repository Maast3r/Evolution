package com.Evolution;

import com.Evolution.exceptions.IllegalCardDirectionException;
import com.Evolution.exceptions.IllegalCardFoodException;
import com.Evolution.exceptions.NullGameObjectException;
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
    public void testName() throws IllegalCardDirectionException, IllegalCardFoodException, NullGameObjectException {
        Card testCard = new Card("Carnivore", "Makes a species a carnivore", "./carnivore.jpg", 3, 0);
        assertTrue("Carnivore".equals(testCard.getName()));
    }

    @Test
    public void testDesc() throws IllegalCardDirectionException, IllegalCardFoodException, NullGameObjectException {
        Card testCard = new Card("Carnivore", "Makes a species a carnivore", "./carnivore.jpg", 3, 0);
        assertTrue("Makes a species a carnivore".equals(testCard.getDesc()));
    }

    @Test
    public void testImgPath() throws IllegalCardDirectionException, IllegalCardFoodException, NullGameObjectException {
        Card testCard = new Card("Carnivore", "Makes a species a carnivore", "./carnivore.jpg", 3, 0);
        assertTrue("./carnivore.jpg".equals(testCard.getImgPath()));
    }

    /**
     * BVA - food value must be between -3 and 9
     * This is the test for inside of the upper boundary
     */
    @Test
    public void testFood() throws IllegalCardDirectionException, IllegalCardFoodException, NullGameObjectException {
        Card testCard = new Card("Carnivore", "Makes a species a carnivore", "./carnivore.jpg", 9, 0);
        assertEquals(testCard.getFood(), 9);
    }

    /**
     * BVA - food value must be between -3 and 9
     * This is the test for the lower boundary
     */
    @Test
    public void testFood2() throws IllegalCardDirectionException, IllegalCardFoodException, NullGameObjectException {
        Card testCard = new Card("Carnivore", "Makes a species a carnivore", "./carnivore.jpg", -3, 0);
        assertEquals(testCard.getFood(), -3);
    }

    /**
     * BVA - food value must be between -3 and 9
     * This is the test for outside of the lower boundary
     */
    @Test (expected = IllegalCardFoodException.class)
    public void testFood3() throws IllegalCardDirectionException, IllegalCardFoodException, NullGameObjectException {
        Card testCard = new Card("Carnivore", "Makes a species a carnivore", "./carnivore.jpg", -4, 0);
    }

    /**
     * BVA - food value must be between -3 and 9
     * This is the test for outside of the upper boundary
     */
    @Test (expected = IllegalCardFoodException.class)
    public void testFood4() throws IllegalCardDirectionException, IllegalCardFoodException, NullGameObjectException {
        Card testCard = new Card("Carnivore", "Makes a species a carnivore", "./carnivore.jpg", 10, 0);
    }

    @Test
    public void testDirection() throws IllegalCardDirectionException, IllegalCardFoodException, NullGameObjectException {
        Card testCard = new Card("Carnivore", "Makes a species a carnivore", "./carnivore.jpg", 3, 0);
        assertEquals(testCard.getDirection(), 0);
    }
    
    @Test(expected = IllegalCardDirectionException.class)
    public void testValidCardDirection1() throws IllegalCardDirectionException, IllegalCardFoodException, NullGameObjectException {
    	Card testCard = new Card("Carnivore", "Makes a species a carnivore", "./carnivore.jpg", 3, -1);
    }
    
    @Test(expected = IllegalCardDirectionException.class)
    public void testValidCardDirection2() throws IllegalCardDirectionException, IllegalCardFoodException, NullGameObjectException {
    	Card testCard = new Card("Carnivore", "Makes a species a carnivore", "./carnivore.jpg", 3, -1234);
    }
    
    @Test(expected = IllegalCardDirectionException.class)
    public void testValidCardDirection3() throws IllegalCardDirectionException, IllegalCardFoodException, NullGameObjectException {
    	Card testCard = new Card("Carnivore", "Makes a species a carnivore", "./carnivore.jpg", 3, 3);
    }
    
    @Test(expected = IllegalCardDirectionException.class)
    public void testValidCardDirection4() throws IllegalCardDirectionException, IllegalCardFoodException, NullGameObjectException {
    	Card testCard = new Card("Carnivore", "Makes a species a carnivore", "./carnivore.jpg", 3, 234);
    }

    @Test(expected = NullGameObjectException.class)
    public void testNullName() throws IllegalCardFoodException, IllegalCardDirectionException, NullGameObjectException {
        Card testCard = new Card(null, "Makes a species a carnivore", "./carnivore.jpg", 3, 0);
    }

    @Test(expected = NullGameObjectException.class)
    public void testNullDescription() throws NullGameObjectException, IllegalCardFoodException, IllegalCardDirectionException {
        Card testCard = new Card("Carnivore", null, "./carnivore.jpg", 3, 0);
    }
    
}
