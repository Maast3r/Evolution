package devops.hw1.core;

import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

/**
 * Created by goistjt on 3/23/2016.
 */
public class GameTests {

    @Test
    public void testConstructWNumPlayers() {
        ArrayList<IPlayer> players = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            players.add(new TestPlayer());
        }
        Game g = new Game(players);
        assertEquals(4, g.getPlayers().size());
    }

    @Test
    public void testGetCurrentRound() {
        ArrayList<IPlayer> players = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            players.add(new TestPlayer());
        }
        Game g = new Game(players);
        assertEquals(1, g.getRound());
    }

    @Test
    public void testIncreaseRound() {
        ArrayList<IPlayer> players = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            players.add(new TestPlayer());
        }
        Game g = new Game(players);
        g.increaseRound();
        assertEquals(2, g.getRound());
    }

    @Test
    public void testIncreaseMultiRound() {
        ArrayList<IPlayer> players = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            players.add(new TestPlayer());
        }
        Game g = new Game(players);
        for (int i = 0; i < 4; i++) {
            g.increaseRound();
            assertEquals(i + 2, g.getRound());
        }
    }

}
