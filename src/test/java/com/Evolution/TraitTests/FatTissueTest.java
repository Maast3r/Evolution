package com.Evolution.TraitTests;


import com.Evolution.abstracts.CTrait;
import com.Evolution.logic.Game;
import com.Evolution.traits.Intelligence;
import org.easymock.EasyMock;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class FatTissueTest {
    @Test
    public void testGetGame() {
        Game g = EasyMock.niceMock(Game.class);
        CTrait t = new FatTissue(g);
        assertEquals(g, t.getGame());
    }

}
