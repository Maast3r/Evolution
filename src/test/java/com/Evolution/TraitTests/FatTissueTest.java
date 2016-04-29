package com.Evolution.TraitTests;


import com.Evolution.abstracts.CTrait;
import com.Evolution.exceptions.*;
import com.Evolution.interfaces.IPhases;
import com.Evolution.interfaces.IPlayer;
import com.Evolution.interfaces.ISpecies;
import com.Evolution.logic.Game;
import com.Evolution.logic.Player;
import com.Evolution.logic.Species;
import com.Evolution.traits.FatTissue;
import com.Evolution.traits.Intelligence;
import org.easymock.EasyMock;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class FatTissueTest {
    @Test
    public void testGetGame() {
        Game g = EasyMock.niceMock(Game.class);
        CTrait t = new FatTissue(g);
        assertEquals(g, t.getGame());
    }

    @Test
    public void testFeedTemp() throws SpeciesFullException,
            InvalidPlayerSelectException, IllegalCardRemovalException,
            WateringHoleEmptyException, SpeciesPopulationException,
            IllegalSpeciesIndexException, IllegalCardDirectionException,
            IllegalCardFoodException, NullGameObjectException,
            IllegalCardDiscardException {
        Game g = EasyMock.niceMock(Game.class);
        IPlayer p = EasyMock.niceMock(Player.class);
        ISpecies s = EasyMock.niceMock(Species.class);
        g.getPlayerObjects();
        p.getSpecies();
        s.eatTemp();
        EasyMock.replay(g, p, s);
        CTrait t = new FatTissue(g);
        t.executeTrait(new int[]{0,0}, new int[]{0,0}, null);
        EasyMock.verify(g, p, s);
    }
}
