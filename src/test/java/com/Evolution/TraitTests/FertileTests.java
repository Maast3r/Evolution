package com.Evolution.TraitTests;


import com.Evolution.interfaces.ATrait;
import com.Evolution.logic.Game;
import org.easymock.EasyMock;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class FertileTests {
    @Test
    public void testGetGame() {
        Game g = EasyMock.niceMock(Game.class);
        ATrait t = new Fertile(g);
        assertEquals(g, t.getGame());
    }
}
