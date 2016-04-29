package com.Evolution.TraitTests;


import com.Evolution.abtracts.ATrait;
import com.Evolution.abtracts.CTrait;
import com.Evolution.exceptions.*;
import com.Evolution.interfaces.ICard;
import com.Evolution.interfaces.IPlayer;
import com.Evolution.interfaces.ISpecies;
import com.Evolution.logic.Card;
import com.Evolution.logic.Game;
import com.Evolution.logic.Player;
import com.Evolution.logic.Species;
import com.Evolution.traits.Intelligence;
import org.easymock.EasyMock;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.Assert.assertEquals;

public class IntelligenceTests {
    @Test
    public void testGetGame() {
        Game g = EasyMock.niceMock(Game.class);
        CTrait t = new Intelligence(g);
        assertEquals(g, t.getGame());
    }


    @Test
    public void testDropCardForFood() throws IllegalCardRemovalException,
            InvalidPlayerSelectException, NullGameObjectException,
            IllegalCardDiscardException, IllegalSpeciesIndexException,
            SpeciesFullException, WateringHoleEmptyException, SpeciesPopulationException,
            IllegalCardFoodException, IllegalCardDirectionException {
        Game g = EasyMock.niceMock(Game.class);
        ICard c = EasyMock.niceMock(Card.class);
        g.discardFromPlayer(0, c);
        g.feedPlayerSpeciesFromBank(0, 0);
        g.feedPlayerSpeciesFromBank(0, 0);
        EasyMock.replay(g, c);
        CTrait t = new Intelligence(g);
        t.executeTrait(new int[]{0, 0}, new int[]{0, 0}, c);
        EasyMock.verify(g, c);
    }
}
