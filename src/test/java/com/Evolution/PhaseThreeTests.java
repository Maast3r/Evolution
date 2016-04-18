package com.Evolution;

import com.Evolution.logic.Game;
import com.Evolution.logic.PhaseThree;
import org.easymock.EasyMock;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Logic for the third phase of the game
 */
public class PhaseThreeTests {

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
