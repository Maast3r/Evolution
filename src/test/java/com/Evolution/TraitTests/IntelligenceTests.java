package com.Evolution.TraitTests;


import com.Evolution.interfaces.ATrait;
import com.Evolution.logic.Game;
import com.Evolution.traits.Intelligence;
import org.easymock.EasyMock;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class IntelligenceTests {
    @Test
    public void testGetGame() {
        Game g = EasyMock.niceMock(Game.class);
        ATrait t = new Intelligence(g);
        assertEquals(g, t.getGame());
    }


//    @Test
//    public void testDropCardForFood() throws IllegalCardRemovalException,
//            InvalidPlayerSelectException, NullGameObjectException,
//            IllegalCardDiscardException, IllegalSpeciesIndexException,
//            SpeciesFullException, WateringHoleEmptyException,
//            IllegalCardFoodException, IllegalCardDirectionException {
//        Game g = EasyMock.niceMock(Game.class);
//        ICard c = EasyMock.niceMock(Card.class);
//        ATrait t = new Foraging(g);
//        g.discardFromPlayer(0, c);
//        g.feedPlayerSpecies(0, 0);
//        EasyMock.replay(g);
//        t.executeTrait(new int[]{0, 0}, new int[]{0, 0});
//        EasyMock.verify(g);
//    }
}
