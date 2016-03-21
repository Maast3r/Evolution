package devops.hw1.core;

import org.junit.Test;

import static org.junit.Assert.assertTrue;

/**
 * Created by burchtm on 3/21/2016.
 */
public class CardTests {

    @Test
    public void testName(){
        Card testCard = new Card("Carnivore", "Makes a species a carnivore");
        assertTrue("Carnivore".equals(testCard.getName()));
    }
    
    @Test
    public void testDesc(){
        Card testCard = new Card("Carnivore", "Makes a species a carnivore");
        assertTrue("Makes a species a carnivore".equals(testCard.getDesc()));
    }
}
