package com.Evolution.TraitTests;


import com.Evolution.exceptions.*;
import com.Evolution.abstracts.ATrait;
import com.Evolution.interfaces.IPlayer;
import com.Evolution.interfaces.ISpecies;
import com.Evolution.logic.Game;
import com.Evolution.logic.Player;
import com.Evolution.logic.Species;
import com.Evolution.traits.Foraging;
import org.easymock.EasyMock;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.Assert.assertEquals;

public class ForagingTests {

    @Test
    public void testGetGame() {
        Game g = EasyMock.niceMock(Game.class);
        ATrait t = new Foraging(g);
        assertEquals(g, t.getGame());
    }

    @Test
    public void testCallGameFeed() throws IllegalSpeciesIndexException,
            InvalidPlayerSelectException,
            SpeciesFullException, WateringHoleEmptyException,
            IllegalCardFoodException, IllegalCardDirectionException,
            NullGameObjectException, IllegalCardRemovalException,
            IllegalCardDiscardException, SpeciesPopulationException, FoodBankEmptyException {
        Game g = EasyMock.niceMock(Game.class);
        IPlayer p = EasyMock.niceMock(Player.class);
        ISpecies s = EasyMock.niceMock(Species.class);
        EasyMock.expect(g.getPlayerObjects()).andReturn(
                new ArrayList<>(Arrays.asList(p)));
        EasyMock.expect(p.getSpecies()).andReturn(
                new ArrayList<>(Arrays.asList(s)));
        EasyMock.expect(s.getTraits()).andReturn(new ArrayList<>());
        ATrait t = new Foraging(g);
        g.feedPlayerSpeciesFromBank(0, 0);
        EasyMock.replay(g, p, s);
        t.executeTrait(new int[]{0, 0}, new int[]{0, 0});
        EasyMock.verify(g, p, s);
    }

}
