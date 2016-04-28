package com.Evolution.TraitTests;


import com.Evolution.exceptions.*;
import com.Evolution.interfaces.ATrait;
import com.Evolution.logic.Game;
import com.Evolution.traits.Foraging;
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
            SpeciesFullException, WateringHoleEmptyException,
            IllegalCardFoodException, IllegalCardDirectionException,
            NullGameObjectException, IllegalCardRemovalException,
            IllegalCardDiscardException {
        Game g = EasyMock.niceMock(Game.class);
        ATrait t = new Foraging(g);
        g.feedPlayerSpecies(0, 0);
        EasyMock.replay(g);
        t.executeTrait(new int[]{0, 0}, new int[]{0, 0});
        EasyMock.verify(g);
    }

    @Test(expected = InvalidPlayerSelectException.class)
    public void testCallGameFeed2() throws IllegalSpeciesIndexException,
            InvalidPlayerSelectException, SpeciesFullException,
            WateringHoleEmptyException, IllegalCardFoodException,
            IllegalCardDirectionException, NullGameObjectException,
            IllegalCardRemovalException, IllegalCardDiscardException {
        Game g = EasyMock.niceMock(Game.class);
        ATrait t = new Foraging(g);
        g.feedPlayerSpecies(0, 0);
        EasyMock.replay(g);
        t.executeTrait(new int[]{5, 0}, new int[]{5, 0});
        EasyMock.verify(g);
    }

    @Test(expected = InvalidPlayerSelectException.class)
    public void testCallGameFeed3() throws IllegalSpeciesIndexException,
            InvalidPlayerSelectException, SpeciesFullException,
            WateringHoleEmptyException, IllegalCardFoodException,
            IllegalCardDirectionException, NullGameObjectException,
            IllegalCardRemovalException, IllegalCardDiscardException {
        Game g = EasyMock.niceMock(Game.class);
        ATrait t = new Foraging(g);
        g.feedPlayerSpecies(0, 0);
        EasyMock.replay(g);
        t.executeTrait(new int[]{-1, 0}, new int[]{-1, 0});
        EasyMock.verify(g);
    }
}
