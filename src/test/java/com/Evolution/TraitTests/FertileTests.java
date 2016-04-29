package com.Evolution.TraitTests;


import com.Evolution.exceptions.*;
import com.Evolution.abstracts.ATrait;
import com.Evolution.logic.Game;
import com.Evolution.traits.Fertile;
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

    @Test
    public void testIncreasePop() throws SpeciesPopulationException,
            SpeciesFullException, InvalidPlayerSelectException,
            IllegalCardRemovalException, WateringHoleEmptyException,
            IllegalSpeciesIndexException, IllegalCardDirectionException,
            IllegalCardFoodException, NullGameObjectException,
            IllegalCardDiscardException {
        Game g = EasyMock.niceMock(Game.class);
        ATrait t = new Fertile(g);
        g.increasePopulation(0, 0);
        EasyMock.replay(g);
        t.executeTrait(new int[]{0, 0}, new int[]{0, 0});
        EasyMock.verify(g);
    }
}
