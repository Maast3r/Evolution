package com.Evolution.TraitTests;

import com.Evolution.exceptions.*;
import com.Evolution.interfaces.ATrait;
import com.Evolution.logic.Game;
import com.Evolution.traits.LongNeck;
import org.easymock.EasyMock;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by goistjt on 4/27/2016.
 */
public class LongNeckTests {

    @Test
    public void getGame() {
        Game g = EasyMock.niceMock(Game.class);
        ATrait t = new LongNeck(g);
        assertEquals(g, t.getGame());
    }

    @Test
    public void callGameFeed() throws IllegalSpeciesIndexException,
            InvalidPlayerSelectException, SpeciesFullException,
            WateringHoleEmptyException, IllegalCardFoodException,
            IllegalCardDirectionException, NullGameObjectException,
            IllegalCardRemovalException, IllegalCardDiscardException {
        Game g = EasyMock.niceMock(Game.class);
        ATrait t = new LongNeck(g);
        g.feedPlayerSpecies(0, 0);
        EasyMock.replay(g);
        t.executeTrait(new int[]{0, -1}, new int[]{0, -1});
        EasyMock.verify(g);
    }
}
