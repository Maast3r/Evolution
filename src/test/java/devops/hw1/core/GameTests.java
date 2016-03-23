package devops.hw1.core;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by goistjt on 3/23/2016.
 */
public class GameTests {

    @Test
    public void testCreateNewGame1() {
        Game g = new Game(3);
        assertEquals(4, g.getNumPlayers());
    }
    @Test
    public void testCreateNewGame2() {
        Game g = new Game(4);
        assertEquals(4, g.getNumPlayers());
    }
    @Test
    public void testCreateNewGame3() {
        Game g = new Game(5);
        assertEquals(4, g.getNumPlayers());
    }

    @Test(expected = IllegalNumberOfPlayers.class)
    public void testValidNumberOfPlayers1(){
        Game g = new Game(0);
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
