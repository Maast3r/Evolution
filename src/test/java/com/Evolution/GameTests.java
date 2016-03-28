package com.Evolution;

import com.Evolution.exceptions.IllegalCardDirectionException;
import com.Evolution.exceptions.IllegalNumberOfPlayers;
import com.Evolution.interfaces.IPhases;
import com.Evolution.interfaces.IPlayer;
import com.Evolution.logic.Card;
import com.Evolution.logic.Game;
import com.Evolution.logic.PhaseOne;
import com.Evolution.testClasses.TestPlayer;
import com.Evolution.testClasses.TestSpecies;
import org.junit.Test;
import org.easymock.EasyMock;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

/**
 * Created by goistjt on 3/23/2016.
 */
public class GameTests {

    @Test
    public void testCreateNewGame1() throws IllegalNumberOfPlayers,
            IllegalCardDirectionException {
        ArrayList<IPlayer> players = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            players.add(new TestPlayer(new TestSpecies()));
        }
        Game g = new Game(players);
        assertEquals(3, g.getPlayerObjects().size());
    }
    @Test
    public void testCreateNewGame2() throws IllegalNumberOfPlayers,
    	    IllegalCardDirectionException {
        ArrayList<IPlayer> players = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            players.add(new TestPlayer(new TestSpecies()));
        }
        Game g = new Game(players);
        assertEquals(4, g.getPlayerObjects().size());
    }
    @Test
    public void testCreateNewGame3() throws IllegalNumberOfPlayers,
    	    IllegalCardDirectionException {
        ArrayList<IPlayer> players = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            players.add(new TestPlayer(new TestSpecies()));
        }
        Game g = new Game(players);
        assertEquals(5, g.getPlayerObjects().size());
    }

    @Test(expected = IllegalNumberOfPlayers.class)
    public void testValidNumberOfPlayersSub3() throws IllegalNumberOfPlayers, 
		    IllegalCardDirectionException {
        ArrayList<IPlayer> players = new ArrayList<>();
        Game g = new Game(players);
    }

    @Test(expected = IllegalNumberOfPlayers.class)
    public void testValidNumberOfPlayersGre6() throws IllegalNumberOfPlayers, 
		    IllegalCardDirectionException {
        ArrayList<IPlayer> players = new ArrayList<>();
        for (int i = 0; i < 7; i++) {
            players.add(new TestPlayer(new TestSpecies()));
        }
        Game g = new Game(players);
    }

    @Test
    public void getPlayers1() throws IllegalNumberOfPlayers, 
		    IllegalCardDirectionException {
        ArrayList<IPlayer> players = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            players.add(new TestPlayer(new TestSpecies()));
        }
        Game g = new Game(players);
        assertEquals(4, g.getPlayerObjects().size());
    }

    @Test
    public void getPlayers2() throws IllegalNumberOfPlayers, 
            IllegalCardDirectionException {
        ArrayList<IPlayer> players = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            players.add(new TestPlayer(new TestSpecies()));
        }
        Game g = new Game(players);
        assertEquals(5, g.getPlayerObjects().size());
    }

    @Test
    public void getPlayers3() throws IllegalNumberOfPlayers, 
		    IllegalCardDirectionException {
        ArrayList<IPlayer> players = new ArrayList<>();
        for (int i = 0; i < 6; i++) {
            players.add(new TestPlayer(new TestSpecies()));
        }
        Game g = new Game(players);
        assertEquals(6, g.getPlayerObjects().size());
    }

    @Test
    public void getDrawPile() throws IllegalNumberOfPlayers, 
		    IllegalCardDirectionException {
        ArrayList<IPlayer> players = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            players.add(new TestPlayer(new TestSpecies()));
        }
        Game g = new Game(players);
        assertEquals(50, g.getDrawPile().size());
    }

    @Test
    public void getDiscardPile1() throws IllegalNumberOfPlayers, 
		    IllegalCardDirectionException {
        ArrayList<IPlayer> players = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            players.add(new TestPlayer(new TestSpecies()));
        }
        Game g = new Game(players);
        assertEquals(0, g.getDiscardPile().size());
    }

    @Test
    public void getDiscardPile2() throws IllegalNumberOfPlayers,
    	    IllegalCardDirectionException {
        ArrayList<IPlayer> players = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            players.add(new TestPlayer(new TestSpecies()));
        }
        Game g = new Game(players);
        g.getDiscardPile().add(new Card("Carnivore", "Makes a species a carnivore",
                "./carnivore.jpg", 3, 0));
        assertEquals(1, g.getDiscardPile().size());
    }

    @Test
    public void testGetCurrentRound() throws IllegalNumberOfPlayers,
    	    IllegalCardDirectionException {
        ArrayList<IPlayer> players = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            players.add(new TestPlayer(new TestSpecies()));
        }
        Game g = new Game(players);
        assertEquals(1, g.getRound());
    }

    @Test
    public void testIncreaseRound() throws IllegalNumberOfPlayers,
    	    IllegalCardDirectionException {
        ArrayList<IPlayer> players = new ArrayList<>();
        for (int i = 0; i < 6; i++) {
            players.add(new TestPlayer(new TestSpecies()));
        }
        Game g = new Game(players);
        g.increaseRound();
        assertEquals(2, g.getRound());
    }

    @Test
    public void testIncreaseMultiRound() throws IllegalNumberOfPlayers,
    	    IllegalCardDirectionException {
        ArrayList<IPlayer> players = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            players.add(new TestPlayer(new TestSpecies()));
        }
        Game g = new Game(players);
        for (int i = 0; i < 4; i++) {
            g.increaseRound();
            assertEquals(i + 2, g.getRound());
        }
    }

    @Test
    public void testStartPhase() throws IllegalNumberOfPlayers,
            IllegalCardDirectionException {
        ArrayList<IPlayer> players = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            players.add(new TestPlayer(new TestSpecies()));
        }
        IPhases fakePhaseOne = EasyMock.niceMock(PhaseOne.class);
        fakePhaseOne.execute();
        EasyMock.replay(fakePhaseOne);
        Game g = new Game(players);
        g.startGame(fakePhaseOne);
        EasyMock.verify(fakePhaseOne);
    }

}
