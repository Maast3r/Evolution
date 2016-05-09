package com.Evolution.TraitTests;


import com.Evolution.abstracts.CTrait;
import com.Evolution.exceptions.*;
import com.Evolution.interfaces.*;
import com.Evolution.logic.Game;
import com.Evolution.logic.Player;
import com.Evolution.logic.Species;
import com.Evolution.traits.FatTissue;
import com.Evolution.traits.Intelligence;
import org.easymock.EasyMock;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class FatTissueTest {

    private ArrayList<IPlayer> generateNumRealPlayers(int num) {
        ArrayList<IPlayer> players = new ArrayList<>();
        for (int i = 0; i < num; i++) {
            try {
                players.add(new Player(new Species()));
            } catch (NullGameObjectException e) {
                e.printStackTrace();
            }
        }
        return players;
    }

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
            IllegalCardDiscardException, FoodBankEmptyException {
        Game g = EasyMock.niceMock(Game.class);
        IPlayer p = EasyMock.niceMock(Player.class);
        ISpecies s = EasyMock.niceMock(Species.class);
        EasyMock.expect(g.getPlayerObjects()).andReturn(
                new ArrayList<>(Arrays.asList(p)));
        EasyMock.expect(p.getSpecies()).andReturn(
                new ArrayList<>(Arrays.asList(s)));
        s.eatTemp();
        EasyMock.replay(g, p, s);
        CTrait t = new FatTissue(g);
        t.executeTrait(new int[]{0, 0}, new int[]{0, 0}, null);
        EasyMock.verify(g, p, s);
    }

    @Test
    public void testFeedTempRealSpecies() throws SpeciesFullException, InvalidPlayerSelectException, IllegalCardRemovalException, WateringHoleEmptyException, SpeciesPopulationException, IllegalSpeciesIndexException, IllegalCardDirectionException, IllegalCardFoodException, NullGameObjectException, IllegalCardDiscardException, FoodBankEmptyException {
        Game g = EasyMock.niceMock(Game.class);
        IPlayer p = EasyMock.niceMock(Player.class);
        ISpecies s = new Species();
        EasyMock.expect(g.getPlayerObjects()).andReturn(new ArrayList<>(Arrays.asList(p)));
        EasyMock.expect(p.getSpecies()).andReturn(new ArrayList<>(Arrays.asList(s)));
        EasyMock.replay(g, p);
        CTrait t = new FatTissue(g);
        t.executeTrait(new int[]{0, 0}, new int[]{0, 0}, null);
        assertTrue(s.getTempFood() == 1);
        EasyMock.verify(g, p);
    }

    @Test
    public void testFeedTempRealPlayer() throws SpeciesFullException, InvalidPlayerSelectException, IllegalCardRemovalException, WateringHoleEmptyException, SpeciesPopulationException, IllegalSpeciesIndexException, IllegalCardDirectionException, IllegalCardFoodException, NullGameObjectException, IllegalCardDiscardException, FoodBankEmptyException {
        Game g = EasyMock.niceMock(Game.class);
        ISpecies s = new Species();
        IPlayer p = new Player(s);
        EasyMock.expect(g.getPlayerObjects()).andReturn(new ArrayList<>(Arrays.asList(p)));
        EasyMock.replay(g);
        CTrait t = new FatTissue(g);
        t.executeTrait(new int[]{0, 0}, new int[]{0, 0}, null);
        assertTrue(s.getTempFood() == 1);
        EasyMock.verify(g);
    }

    @Test
    public void testFeedTempRealGame() throws SpeciesFullException, InvalidPlayerSelectException, IllegalCardRemovalException, WateringHoleEmptyException, SpeciesPopulationException, IllegalSpeciesIndexException, IllegalCardDirectionException, IllegalCardFoodException, NullGameObjectException, IllegalCardDiscardException, FoodBankEmptyException, IllegalNumberOfPlayers {
        Game g = new Game(generateNumRealPlayers(3), EasyMock.niceMock(IWateringHole.class), EasyMock.niceMock(IDeck.class), EasyMock.niceMock(IDeck.class));
        ISpecies s = g.getPlayerObjects().get(0).getSpecies().get(0);
        g.fatTissueEat(0, 0);
        assertTrue(s.getTempFood() == 1);
    }
}
