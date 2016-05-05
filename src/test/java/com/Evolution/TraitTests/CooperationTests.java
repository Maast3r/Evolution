package com.Evolution.TraitTests;


import com.Evolution.exceptions.*;
import com.Evolution.abstracts.ATrait;
import com.Evolution.logic.Game;
import com.Evolution.traits.Cooperation;
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
    public void testCallGameFeedRight() throws IllegalSpeciesIndexException,
            InvalidPlayerSelectException, SpeciesFullException,
            WateringHoleEmptyException, IllegalCardFoodException,
            IllegalCardDirectionException, NullGameObjectException,
            IllegalCardRemovalException, IllegalCardDiscardException,
            SpeciesPopulationException, FoodBankEmptyException {
        Game g = EasyMock.niceMock(Game.class);
        ATrait t = new Cooperation(g);
        g.feedPlayerSpeciesFromBank(0, 1);
        EasyMock.replay(g);
        t.executeTrait(new int[]{0, 0}, new int[]{0, 0});
        EasyMock.verify(g);
    }

    @Test
    public void testCallGameFeedRight2() throws IllegalSpeciesIndexException, InvalidPlayerSelectException,
            SpeciesFullException, WateringHoleEmptyException,
            IllegalCardFoodException, IllegalCardDirectionException,
            NullGameObjectException, IllegalCardRemovalException,
            IllegalCardDiscardException, SpeciesPopulationException, FoodBankEmptyException {
        Game g = EasyMock.niceMock(Game.class);
        ATrait t = new Cooperation(g);
        g.feedPlayerSpeciesFromBank(0, 2);
        EasyMock.replay(g);
        t.executeTrait(new int[]{0, 0}, new int[]{1, 0});
        EasyMock.verify(g);
    }
}
