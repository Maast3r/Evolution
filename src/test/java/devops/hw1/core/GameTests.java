package devops.hw1.core;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by goistjt on 3/23/2016.
 */
public class GameTests {

    @Test
    public void testGetCurrentRound() {
        Game g = new Game();
        assertEquals(1, g.getRound());
    }

    @Test
    public void testIncreaseRound() {
        Game g = new Game();
        g.increaseRound();
        assertEquals(2, g.getRound());
    }
}
