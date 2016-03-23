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
}
