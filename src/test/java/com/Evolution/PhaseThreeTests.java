package com.Evolution;

import com.Evolution.exceptions.DeckEmptyException;
import com.Evolution.exceptions.IllegalCardDirectionException;
import com.Evolution.exceptions.IllegalNumberOfPlayers;
import com.Evolution.exceptions.InvalidPlayerSelectException;
import com.Evolution.interfaces.ICard;
import com.Evolution.interfaces.IDeck;
import com.Evolution.interfaces.IWateringHole;
import com.Evolution.logic.*;
import org.easymock.EasyMock;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Logic for the third phase of the game
 */
public class PhaseThreeTests {

    public void testNextPhase() throws IllegalCardDirectionException, IllegalNumberOfPlayers, DeckEmptyException, InvalidPlayerSelectException {
        Game g = EasyMock.niceMock(Game.class);
        PhaseTwo p = new PhaseTwo(g);
        EasyMock.expect(g.getTurn()).andReturn(2);
        EasyMock.replay(g);
        g.setPhase(p);
        p.execute();
        assertEquals(PhaseFour.class, g.getPhase().getClass());
        EasyMock.verify(g);
    }

    @Test
    public void testGetPhaseName() {
        Game g = EasyMock.niceMock(Game.class);
        PhaseThree p = new PhaseThree(g);
        assertEquals(p.getName(), "Play Cards");
    }

    @Test
    public void testGetPhaseNumber() {
        Game g = EasyMock.niceMock(Game.class);
        PhaseThree p = new PhaseThree(g);
        assertEquals(p.getNumber(), 3);
    }
}
