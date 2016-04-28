package com.Evolution.TraitTests;


import com.Evolution.exceptions.*;
import com.Evolution.interfaces.ATrait;
import com.Evolution.interfaces.ICard;
import com.Evolution.logic.Card;
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
    public void testIncreasePop() throws
            IllegalCardRemovalException, NullGameObjectException,
            SpeciesPopulationException, IllegalCardDiscardException,
            InvalidPlayerSelectException, IllegalSpeciesIndexException,
            IllegalCardFoodException, WateringHoleEmptyException,
            SpeciesFullException, IllegalCardDirectionException {
        Game g = EasyMock.niceMock(Game.class);
        ATrait t = new Fertile(g);
        g.increasePopulation(0, 0);
        EasyMock.replay(g);
        t.executeTrait(new int[]{0, 0}, new int[]{0, 0});
        EasyMock.verify(g);
    }
}
