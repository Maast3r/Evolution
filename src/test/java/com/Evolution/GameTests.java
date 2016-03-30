package com.Evolution;

import com.Evolution.exceptions.IllegalCardDirectionException;
import com.Evolution.exceptions.IllegalNumberOfPlayers;
import com.Evolution.interfaces.*;
import com.Evolution.logic.*;
import com.Evolution.testClasses.*;
import org.junit.Before;
import org.junit.Test;
import org.easymock.EasyMock;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class GameTests {
    private IWateringHole wateringHole;
    private IDeck<ICard> drawPile;
    private IDeck<ICard> discardPile;

    private ArrayList<IPlayer> generateNumPlayers(int num) {
        ArrayList<IPlayer> players = new ArrayList<>();
        for (int i = 0; i < num; i++) {
            players.add(new TestPlayer(new TestSpecies()));
        }
        return players;
    }

    @Before
    public void initObjects() {
        wateringHole = new TestWateringHole();
        drawPile = EasyMock.niceMock(Deck.class);
        discardPile = EasyMock.niceMock(Deck.class);
    }

    @Test
    public void testCreateNewGame1() throws IllegalNumberOfPlayers,
            IllegalCardDirectionException {
        Game g = new Game(generateNumPlayers(3), wateringHole, this.drawPile);
        assertEquals(3, g.getPlayerObjects().size());
    }

    @Test
    public void testCreateNewGame2() throws IllegalNumberOfPlayers,
            IllegalCardDirectionException {
        Game g = new Game(generateNumPlayers(4), wateringHole, this.drawPile);
        assertEquals(4, g.getPlayerObjects().size());
    }

    @Test
    public void testCreateNewGame3() throws IllegalNumberOfPlayers,
            IllegalCardDirectionException {
        Game g = new Game(generateNumPlayers(5), wateringHole, this.drawPile);
        assertEquals(5, g.getPlayerObjects().size());
    }

    @Test(expected = IllegalNumberOfPlayers.class)
    public void testValidNumberOfPlayersSub3() throws IllegalNumberOfPlayers,
            IllegalCardDirectionException {
        Game g = new Game(generateNumPlayers(0), wateringHole, this.drawPile);
    }

    @Test(expected = IllegalNumberOfPlayers.class)
    public void testValidNumberOfPlayersGre6() throws IllegalNumberOfPlayers,
            IllegalCardDirectionException {
        Game g = new Game(generateNumPlayers(7), wateringHole, this.drawPile);
    }

    @Test
    public void getPlayers1() throws IllegalNumberOfPlayers,
            IllegalCardDirectionException {
        IWateringHole wateringHole = new TestWateringHole();
        Game g = new Game(generateNumPlayers(4), wateringHole, this.drawPile);
        assertEquals(4, g.getPlayerObjects().size());
    }

    @Test
    public void getPlayers2() throws IllegalNumberOfPlayers,
            IllegalCardDirectionException {
        IWateringHole wateringHole = new TestWateringHole();
        Game g = new Game(generateNumPlayers(5), wateringHole, this.drawPile);
        assertEquals(5, g.getPlayerObjects().size());
    }

    @Test
    public void getPlayers3() throws IllegalNumberOfPlayers,
            IllegalCardDirectionException {
        Game g = new Game(generateNumPlayers(6), wateringHole, this.drawPile);
        assertEquals(6, g.getPlayerObjects().size());
    }

    @Test
    public void getDrawPile() throws IllegalNumberOfPlayers,
            IllegalCardDirectionException {
        Game g = new Game(generateNumPlayers(4), wateringHole, this.drawPile);
        assertTrue(g.getDrawPile() == this.drawPile);
    }

    @Test
    public void getDiscardPile1() throws IllegalNumberOfPlayers,
            IllegalCardDirectionException {
        Game g = new Game(generateNumPlayers(5), wateringHole, this.drawPile);
        assertEquals(0, g.getDiscardPile().getSize());
    }

    @Test
    public void getDiscardPile2() throws IllegalNumberOfPlayers,
            IllegalCardDirectionException {
        Game g = new Game(generateNumPlayers(5), wateringHole, this.drawPile);
        g.getDiscardPile().discard(new Card("Carnivore", "Makes a species a carnivore",
                "./carnivore.jpg", 3, 0));
        assertEquals(1, g.getDiscardPile().getSize());
    }

    @Test
    public void testGetCurrentRound() throws IllegalNumberOfPlayers,
            IllegalCardDirectionException {
        Game g = new Game(generateNumPlayers(4), wateringHole, this.drawPile);
        assertEquals(1, g.getRound());
    }

    @Test
    public void testIncreaseRound() throws IllegalNumberOfPlayers,
            IllegalCardDirectionException {
        Game g = new Game(generateNumPlayers(6), wateringHole, this.drawPile);
        g.increaseRound();
        assertEquals(2, g.getRound());
    }

    @Test
    public void testIncreaseMultiRound() throws IllegalNumberOfPlayers,
            IllegalCardDirectionException {
        Game g = new Game(generateNumPlayers(5), wateringHole, this.drawPile);
        for (int i = 0; i < 4; i++) {
            g.increaseRound();
            assertEquals(i + 2, g.getRound());
        }
    }

    @Test
    public void testStartPhase() throws IllegalNumberOfPlayers,
            IllegalCardDirectionException {
        IPhases fakePhaseOne = EasyMock.niceMock(PhaseOne.class);
        fakePhaseOne.execute();
        EasyMock.replay(fakePhaseOne);
        Game g = new Game(generateNumPlayers(4), wateringHole, this.drawPile);
        g.startGame(fakePhaseOne);
        EasyMock.verify(fakePhaseOne);
    }

    @Test
    public void testGetTurn1() throws IllegalNumberOfPlayers, IllegalCardDirectionException {
        Game g = new Game(generateNumPlayers(5), wateringHole, this.drawPile);
        assertTrue(g.getTurn() == 1);
    }

    @Test
    public void testGetTurn3() throws IllegalNumberOfPlayers, IllegalCardDirectionException {
        Game g = new Game(generateNumPlayers(5), wateringHole, this.drawPile);
        g.nextTurn();
        g.nextTurn();
        assertTrue(g.getTurn() == 3);
    }

    @Test
    public void testGetTurn6() throws IllegalNumberOfPlayers, IllegalCardDirectionException {
        Game g = new Game(generateNumPlayers(5), wateringHole, this.drawPile);
        for (int j = 0; j < 5; j++) {
            g.nextTurn();
        }
        assertTrue(g.getTurn() == 1);
    }

    @Test
    public void testGetWateringHole() throws IllegalNumberOfPlayers, IllegalCardDirectionException {
        Game g = new Game(generateNumPlayers(4), wateringHole, this.drawPile);
        assertTrue(g.getWateringHole() == wateringHole);
    }

}
