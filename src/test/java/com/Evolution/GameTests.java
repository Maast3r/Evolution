package com.Evolution;

import com.Evolution.exceptions.IllegalCardDirectionException;
import com.Evolution.exceptions.IllegalNumberOfPlayers;
import com.Evolution.interfaces.IPhases;
import com.Evolution.logic.Card;
import com.Evolution.logic.Game;
import com.Evolution.logic.PhaseOne;
import org.junit.Test;
import org.easymock.EasyMock;

import static org.junit.Assert.assertEquals;

/**
 * Created by goistjt on 3/23/2016.
 */
public class GameTests {

    @Test
    public void testCreateNewGame1() throws IllegalNumberOfPlayers,
            IllegalCardDirectionException {
        Game g = new Game(3);
        assertEquals(3, g.getNumPlayers());
    }
    @Test
    public void testCreateNewGame2() throws IllegalNumberOfPlayers,
    	    IllegalCardDirectionException {
        Game g = new Game(4);
        assertEquals(4, g.getNumPlayers());
    }
    @Test
    public void testCreateNewGame3() throws IllegalNumberOfPlayers,
    	    IllegalCardDirectionException {
        Game g = new Game(5);
        assertEquals(5, g.getNumPlayers());
    }

    @Test(expected = IllegalNumberOfPlayers.class)
    public void testValidNumberOfPlayersSub3() throws IllegalNumberOfPlayers, 
		    IllegalCardDirectionException {
        Game g = new Game(0);
    }

    @Test(expected = IllegalNumberOfPlayers.class)
    public void testValidNumberOfPlayersGre6() throws IllegalNumberOfPlayers, 
		    IllegalCardDirectionException {
        Game g = new Game(7);
    }

    @Test
    public void getPlayers1() throws IllegalNumberOfPlayers, 
		    IllegalCardDirectionException {
        Game g = new Game(4);
        assertEquals(4, g.getPlayerObjects().size());
    }

    @Test
    public void getPlayers2() throws IllegalNumberOfPlayers, 
            IllegalCardDirectionException {
        Game g = new Game(5);
        assertEquals(5, g.getPlayerObjects().size());
    }

    @Test
    public void getPlayers3() throws IllegalNumberOfPlayers, 
		    IllegalCardDirectionException {
        Game g = new Game(6);
        assertEquals(6, g.getPlayerObjects().size());
    }

    @Test
    public void getDrawPile() throws IllegalNumberOfPlayers, 
		    IllegalCardDirectionException {
        Game g = new Game(4);
        assertEquals(50, g.getDrawPile().size());
    }

    @Test
    public void getDiscardPile1() throws IllegalNumberOfPlayers, 
		    IllegalCardDirectionException {
        Game g = new Game(5);
        assertEquals(0, g.getDiscardPile().size());
    }

    @Test
    public void getDiscardPile2() throws IllegalNumberOfPlayers,
    	    IllegalCardDirectionException {
        Game g = new Game(5);
        g.getDiscardPile().add(new Card("Carnivore", "Makes a species a carnivore",
                "./carnivore.jpg", 3, 0));
        assertEquals(1, g.getDiscardPile().size());
    }

    @Test
    public void testGetCurrentRound() throws IllegalNumberOfPlayers,
    	    IllegalCardDirectionException {
        Game g = new Game(4);
        assertEquals(1, g.getRound());
    }

    @Test
    public void testIncreaseRound() throws IllegalNumberOfPlayers,
    	    IllegalCardDirectionException {
        Game g = new Game(6);
        g.increaseRound();
        assertEquals(2, g.getRound());
    }

    @Test
    public void testIncreaseMultiRound() throws IllegalNumberOfPlayers,
    	    IllegalCardDirectionException {
        Game g = new Game(5);
        for (int i = 0; i < 4; i++) {
            g.increaseRound();
            assertEquals(i + 2, g.getRound());
        }
    }

    @Test
    public void testStartGame() throws IllegalNumberOfPlayers,
            IllegalCardDirectionException {
        PhaseOne fakePhaseOne = EasyMock.niceMock(PhaseOne.class);
        fakePhaseOne.execute();
        EasyMock.replay(fakePhaseOne);
        Game g = new Game(4);
        g.startPhase(fakePhaseOne);
        EasyMock.verify(fakePhaseOne);
    }

    @Test
    public void testPhase2() throws IllegalNumberOfPlayers,
            IllegalCardDirectionException {
        IPhases fakePhaseOne = EasyMock.niceMock(PhaseTwo.class);
        fakePhaseOne.execute();
        EasyMock.replay(fakePhaseOne);
        Game g = new Game(4);
        g.startPhase(fakePhaseOne);
        EasyMock.verify(fakePhaseOne);
    }

    @Test
    public void testPhase3() throws IllegalNumberOfPlayers,
            IllegalCardDirectionException {
        IPhases fakePhaseOne = EasyMock.niceMock(PhaseThree.class);
        fakePhaseOne.execute();
        EasyMock.replay(fakePhaseOne);
        Game g = new Game(4);
        g.startPhase(fakePhaseOne);
        EasyMock.verify(fakePhaseOne);
    }


    @Test
    public void testPhase4() throws IllegalNumberOfPlayers,
            IllegalCardDirectionException {
        IPhases fakePhaseOne = EasyMock.niceMock(PhaseFour.class);
        fakePhaseOne.execute();
        EasyMock.replay(fakePhaseOne);
        Game g = new Game(4);
        g.startPhase(fakePhaseOne);
        EasyMock.verify(fakePhaseOne);
    }
}
