package com.Evolution.TraitTests;


import com.Evolution.exceptions.IllegalSpeciesIndexException;
import com.Evolution.exceptions.InvalidPlayerSelectException;
import com.Evolution.exceptions.SpeciesFullException;
import com.Evolution.exceptions.WateringHoleEmptyException;
import com.Evolution.interfaces.ATrait;
import com.Evolution.logic.Game;
import com.Evolution.traits.Foraging;
import com.Evolution.traits.LongNeck;
import org.easymock.EasyMock;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ForagingTests {

    @Test
    public void testGetGame() {
        Game g = EasyMock.niceMock(Game.class);
        ATrait t = new Foraging(g);
        assertEquals(g, t.getGame());
    }

    @Test
    public void testCallGameFeed() throws IllegalSpeciesIndexException, InvalidPlayerSelectException,
            SpeciesFullException, WateringHoleEmptyException {
        Game g = EasyMock.niceMock(Game.class);
        ATrait t = new Foraging(g);
        g.feedPlayerSpecies(0, 0);
        EasyMock.replay(g);
        t.executeTrait(new int[]{0, 0}, new int[]{0, 0});
        EasyMock.verify(g);
    }

    @Test(expceted = InvalidTraitPlayerIndicesException.class)
    public void testCallGamemFeed2() throws IllegalSpeciesIndexException,
            InvalidPlayerSelectException, SpeciesFullException, WateringHoleEmptyException {
        Game g = EasyMock.niceMock(Game.class);
        ATrait t = new Foraging(g);
        g.feedPlayerSpecies(0, 0);
        EasyMock.replay(g);
        t.executeTrait(new int[]{-1, 0}, new int[]{-1, 0});
        EasyMock.verify(g);
    }
}
