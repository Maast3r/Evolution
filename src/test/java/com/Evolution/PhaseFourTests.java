package com.Evolution;

import com.Evolution.logic.*;
import org.easymock.EasyMock;
import org.junit.Test;

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

}
