package devops.hw1.core;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * Created by burchtm on 3/21/2016.
 */
public class CardTests {

    @Test
    public void testName(){
        Card testCard = new Card("Carnivore");
        assertTrue("Carnivore".equals(testCard.getName()));
    }
}
