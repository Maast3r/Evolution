package com.Evolution.TraitTests;


import com.Evolution.exceptions.IllegalSpeciesIndexException;
import com.Evolution.exceptions.InvalidPlayerSelectException;
import com.Evolution.exceptions.SpeciesFullException;
import com.Evolution.exceptions.WateringHoleEmptyException;
import com.Evolution.interfaces.ATrait;
import com.Evolution.logic.Game;
import com.Evolution.traits.Cooperation;
import com.Evolution.traits.Foraging;
import org.easymock.EasyMock;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CooperationTests {

    @Test
    public void testGetGame() {
        Game g = EasyMock.niceMock(Game.class);
        ATrait t = new Cooperation(g);
        assertEquals(g, t.getGame());
    }

    @Test
    public void testCallGameFeedRight() throws IllegalSpeciesIndexException, InvalidPlayerSelectException,
            SpeciesFullException, WateringHoleEmptyException {
        Game g = EasyMock.niceMock(Game.class);
        ATrait t = new Cooperation(g);
        g.feedPlayerSpecies(0, 1);
        EasyMock.replay(g);
        t.executeTrait(new int[]{0, 0}, new int[]{1, 0});
        EasyMock.verify(g);
    }
}
