package com.Evolution;

import com.Evolution.exceptions.*;
import com.Evolution.interfaces.*;
import com.Evolution.logic.*;
import org.easymock.EasyMock;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

/**
 * Logic for the fourth phase of the game
 */
public class PhaseFourTests {

    @Test
    public void testGetPhaseName() {
        Game g = EasyMock.niceMock(Game.class);
        PhaseFour p = new PhaseFour(g);
        assertEquals(p.getName(), "Feeding");
    }

    @Test
    public void testGetPhaseNumber() {
        Game g = EasyMock.niceMock(Game.class);
        PhaseFour p = new PhaseFour(g);
        assertEquals(p.getNumber(), 4);
    }

    @Test
    public void testNextPhase() throws IllegalCardDirectionException, IllegalNumberOfPlayers, DeckEmptyException, InvalidPlayerSelectException, NullGameObjectException {
        ArrayList<IPlayer> players = generateMockedPlayers(3);
        Game g = new Game(players, EasyMock.niceMock(WateringHole.class), EasyMock.niceMock(Deck.class),
                EasyMock.niceMock(Deck.class));
        PhaseFour p = new PhaseFour(g);
        g.setPhase(p);
        for (IPlayer player :
                players) {
            EasyMock.expect(player.allSpeciesFull()).andReturn(true);
        }

        players.forEach(EasyMock::replay);
        p.execute();
        assertEquals(PhaseOne.class, g.getPhase().getClass());
        players.forEach(EasyMock::verify);
    }

    @Test
    public void testNextTurn() throws IllegalNumberOfPlayers, NullGameObjectException, InvalidPlayerSelectException, IllegalCardDirectionException, DeckEmptyException {
        ArrayList<IPlayer> players = generateMockedPlayers(3);
        Game g = new Game(players, EasyMock.niceMock(WateringHole.class), EasyMock.niceMock(Deck.class),
                EasyMock.niceMock(Deck.class));
        PhaseFour p = new PhaseFour(g);
        g.setPhase(p);
        int initTurn = g.getTurn();
        EasyMock.expect(players.get(0).allSpeciesFull()).andReturn(false);
        players.forEach(EasyMock::replay);

        p.execute();
        assertEquals(initTurn + 1, g.getTurn());
        players.forEach(EasyMock::verify);
    }

    @Test
    public void testNextRound() throws IllegalCardDirectionException, IllegalNumberOfPlayers, DeckEmptyException,
            InvalidPlayerSelectException, NullGameObjectException {
        ArrayList<IPlayer> players = generateMockedPlayers(3);
        Game g = new Game(players, EasyMock.niceMock(WateringHole.class), EasyMock.niceMock(Deck.class),
                EasyMock.niceMock(Deck.class));
        PhaseFour p = new PhaseFour(g);
        g.setPhase(p);
        for (IPlayer player :
                players) {
            EasyMock.expect(player.allSpeciesFull()).andReturn(true);
        }

        int curRound = g.getRound();
        players.forEach(EasyMock::replay);
        p.execute();
        assertEquals(curRound + 1, g.getRound());
        players.forEach(EasyMock::verify);
    }

    private ArrayList<IPlayer> generateMockedPlayers(int numPlayers) {
        ArrayList<IPlayer> players = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            players.add(EasyMock.niceMock(Player.class));
        }
        return players;
    }
}
