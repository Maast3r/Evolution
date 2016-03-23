package devops.hw1.core;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by goistjt on 3/23/2016.
 */
public class GameTests {

    @Test
    public void testCreateNewGame() {
        Game g = new Game(4);
        assertEquals(4, g.getNumPlayers());
    }

    @Test
    public void testGetCurrentRound() {
        Game g = new Game(4);
        assertEquals(1, g.getRound());
    }

    @Test
    public void testIncreaseRound() {
        Game g = new Game(6);
        g.increaseRound();
        assertEquals(2, g.getRound());
    }

    @Test
    public void testIncreaseMultiRound() {
        Game g = new Game(5);
        for (int i = 0; i < 4; i++) {
            g.increaseRound();
            assertEquals(i + 2, g.getRound());
        }
    }

}
