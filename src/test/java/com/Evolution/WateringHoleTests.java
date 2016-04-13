package com.Evolution;

import com.Evolution.exceptions.IllegalCardDirectionException;
import com.Evolution.exceptions.WateringHoleEmptyException;
import com.Evolution.interfaces.ICard;
import com.Evolution.logic.Card;
import com.Evolution.logic.WateringHole;
import org.easymock.EasyMock;
import org.junit.Test;

import java.lang.reflect.Field;
import java.util.Arrays;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Tests the watering hole functionality
 * Created by burchtm on 3/21/2016.
 */
public class WateringHoleTests {

    @Test
    public void testGetFoodCount0() {
        WateringHole wateringHole = new WateringHole();
        assertTrue(wateringHole.getFoodCount() == 0);
    }

    @Test
    public void testAddSingleFood() {
        WateringHole wateringHole = new WateringHole();
        wateringHole.addFood();
        assertEquals(1, wateringHole.getFoodCount());
    }

    @Test
    public void testAddSeveralSingleFood() {
        WateringHole wateringHole = new WateringHole();
        for (int i = 0; i < 5; i++) {
            wateringHole.addFood();
        }
        assertEquals(5, wateringHole.getFoodCount());
    }

    @Test
    public void testAdd5Food() {
        WateringHole wateringHole = new WateringHole();
        wateringHole.addFood(5);
        assertEquals(5, wateringHole.getFoodCount());
    }

    @Test
    public void testAddNFood() {
        WateringHole wateringHole = new WateringHole();
        wateringHole.addFood(5);
        assertEquals(5, wateringHole.getFoodCount());
        wateringHole = new WateringHole();
        wateringHole.addFood(12);
        assertEquals(12, wateringHole.getFoodCount());
        wateringHole = new WateringHole();
        wateringHole.addFood(7);
        assertEquals(7, wateringHole.getFoodCount());
    }

    @Test
    public void testRemove1Food() throws WateringHoleEmptyException {
        WateringHole wateringHole = new WateringHole();
        wateringHole.addFood(7);
        wateringHole.removeFood();
        assertEquals(6, wateringHole.getFoodCount());
    }

    @Test
    public void testRemoveSeveralSingleFood() throws WateringHoleEmptyException {
        WateringHole wateringHole = new WateringHole();
        wateringHole.addFood(7);
        for (int i = 0; i < 4; i++) {
            wateringHole.removeFood();
        }
        assertEquals(3, wateringHole.getFoodCount());
    }

    @Test
    public void testRemove5Food() throws WateringHoleEmptyException {
        WateringHole wateringHole = new WateringHole();
        wateringHole.addFood(10);
        wateringHole.removeFood(5);
        assertEquals(5, wateringHole.getFoodCount());
    }

    @Test
    public void testRemoveNFood() throws WateringHoleEmptyException {
        WateringHole wateringHole = new WateringHole();
        wateringHole.addFood(10);
        wateringHole.removeFood(5);
        assertEquals(5, wateringHole.getFoodCount());

        wateringHole = new WateringHole();
        wateringHole.addFood(10);
        wateringHole.removeFood(3);
        assertEquals(7, wateringHole.getFoodCount());

        wateringHole = new WateringHole();
        wateringHole.addFood(10);
        wateringHole.removeFood(1);
        assertEquals(9, wateringHole.getFoodCount());
    }

    @Test(expected = WateringHoleEmptyException.class)
    public void testWateringHoleEmpty() throws WateringHoleEmptyException {
        WateringHole w = new WateringHole();
        w.removeFood();
    }

    @Test(expected = WateringHoleEmptyException.class)
    public void testWateringHoleIEmpty1() throws WateringHoleEmptyException {
        WateringHole w = new WateringHole();
        w.removeFood(1);
    }

    @Test(expected = WateringHoleEmptyException.class)
    public void testWateringHoleIEmpty2() throws WateringHoleEmptyException {
        WateringHole w = new WateringHole();
        w.removeFood(-1);
    }

    @Test
    public void testAddCardToWateringHole(){
        WateringHole w = new WateringHole();
        for (int i = 0; i < 5; i++) {
            ICard card = EasyMock.niceMock(Card.class);
            w.addCard(card);
        }
        assertEquals(w.getCards().size(), 5);
    }

    /**
     * BVA - Removing all from an empty list. Should succeed
     * leaving the deck empty.
     */
    @Test
    public void testRemoveCardsFromWateringHole() {
        WateringHole w = new WateringHole();
        w.removeCards();
        assertEquals(w.getCards().size(), 0);
    }

    /**
     * BVA - Removing all from a full list.
     * Max of 5 cards in the watering hole at a time because
     * there is a max of five players w/ 1 card per player.
     * Should succeed leaving the deck empty.
     */
    @Test
    public void testRemoveCardsFromWateringHole2() throws Exception {
        WateringHole w = new WateringHole();
        for (int i = 0; i < 5; i++) {
            ICard card = EasyMock.niceMock(Card.class);
            w.addCard(card);
        }
        assertEquals(w.getCards().size(), 5);
        w.removeCards();
        assertEquals(w.getCards().size(), 0);
    }

    /**
     * BVA - Counting a total of -10 food from all cards.
     * Lowest number of food to be ever counted from the cards
     * in the watering hole.
     */
    @Test
    public void testCountCardFood1() throws NoSuchFieldException, IllegalAccessException, IllegalCardDirectionException {
        WateringHole w = new WateringHole();
        for (int i = 0; i < 5; i++) {
            ICard card = new Card("Carnivore", "Makes a species a carnivore", "./carnivore.jpg", -2, 0);
            w.addCard(card);
        }
        assertEquals(-10, w.getCardFoodCount());
    }

    /**
     * BVA - Counting a total of 35 food from all cards.
     * Highest number of food to be ever counted from the cards
     * in the watering hole.
     */
    @Test
    public void testCountCardFood2() throws NoSuchFieldException, IllegalAccessException,
            IllegalCardDirectionException {
        WateringHole w = new WateringHole();
        for (int i = 0; i < 5; i++) {
            ICard card = new Card("Carnivore", "Makes a species a carnivore", "./carnivore.jpg", 7, 0);
            w.addCard(card);
        }
        assertEquals(35, w.getCardFoodCount());
    }

    /**
     * Same as 1, but as unit test
     * BVA - Counting a total of -10 food from all cards.
     * Lowest number of food to be ever counted from the cards
     * in the watering hole.
     */
    @Test
    public void testCountCardFood3() throws NoSuchFieldException, IllegalAccessException,
            IllegalCardDirectionException {
        WateringHole w = new WateringHole();
        for (int i = 0; i < 5; i++) {
            Card card = EasyMock.createMockBuilder(Card.class)
                    .withConstructor(String.class, String.class, String.class,
                            int.class, int.class)
                    .withArgs("","","",-2, 0)
                    .createMock();
            w.addCard(card);
        }
        assertEquals(-10, w.getCardFoodCount());
    }

    /**
     * Same as 2, but as unit test
     * BVA - Counting a total of 35 food from all cards.
     * Highest number of food to be ever counted from the cards
     * in the watering hole.
     */
    @Test
    public void testCountCardFood4() throws NoSuchFieldException, IllegalAccessException,
            IllegalCardDirectionException {
        WateringHole w = new WateringHole();
        for (int i = 0; i < 5; i++) {
            Card card = EasyMock.createMockBuilder(Card.class)
                    .withConstructor(String.class, String.class, String.class,
                            int.class, int.class)
                    .withArgs("","","",7, 0)
                    .createMock();
            w.addCard(card);
        }
        assertEquals(35, w.getCardFoodCount());
    }
}
