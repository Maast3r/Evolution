package devops.hw1.core;

import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

/**
 * Created by goistjt on 3/23/2016.
 */
public class GameTests {

    @Test
    public void testCreateNewGame1() throws IllegalNumberOfPlayers {
        Game g = new Game(3);
        assertEquals(3, g.getNumPlayers());
    }
    @Test
    public void testCreateNewGame2() throws IllegalNumberOfPlayers {
        Game g = new Game(4);
        assertEquals(4, g.getNumPlayers());
    }
    @Test
    public void testCreateNewGame3() throws IllegalNumberOfPlayers {
        Game g = new Game(5);
        assertEquals(5, g.getNumPlayers());
    }

    @Test(expected = IllegalNumberOfPlayers.class)
    public void testValidNumberOfPlayersSub3() throws IllegalNumberOfPlayers {
        Game g = new Game(0);
    }

    @Test(expected = IllegalNumberOfPlayers.class)
    public void testValidNumberOfPlayersGre6() throws IllegalNumberOfPlayers {
        Game g = new Game(7);
    }

    @Test
    public void getPlayers1() throws IllegalNumberOfPlayers {
        Game g = new Game(4);
        assertEquals(4, g.getPlayerObjects().size());
    }

    @Test
    public void getPlayers2() throws IllegalNumberOfPlayers {
        Game g = new Game(5);
        assertEquals(5, g.getPlayerObjects().size());
    }

    @Test
    public void getPlayers3() throws IllegalNumberOfPlayers {
        Game g = new Game(6);
        assertEquals(6, g.getPlayerObjects().size());
    }

    @Test
    public void getDrawPile1() throws IllegalNumberOfPlayers {
        Game g = new Game(4);
        assertEquals(50, g.getDrawPile().size());
    }

    @Test
    public void testGetCurrentRound() throws IllegalNumberOfPlayers {
        Game g = new Game(4);
        assertEquals(1, g.getRound());
    }

    @Test
    public void testIncreaseRound() throws IllegalNumberOfPlayers {
        Game g = new Game(6);
        g.increaseRound();
        assertEquals(2, g.getRound());
    }

    @Test
    public void testIncreaseMultiRound() throws IllegalNumberOfPlayers {
        Game g = new Game(5);
        for (int i = 0; i < 4; i++) {
            g.increaseRound();
            assertEquals(i + 2, g.getRound());
        }
    }

}
