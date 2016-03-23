package devops.hw1.core;

import org.junit.Test;

import java.util.Collections;

import static org.junit.Assert.*;
import static org.junit.Assert.assertTrue;

/**
 * Tests the watering hole functionality
 * Created by burchtm on 3/21/2016.
 */
public class WateringHoleTests {

    @Test
    public void testGetFoodCount0(){
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
    public void testRemove1Food() {
        WateringHole wateringHole = new WateringHole();
        wateringHole.addFood(7);
        wateringHole.removeFood();
        assertEquals(6, wateringHole.getFoodCount());
    }
}
