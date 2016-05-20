package com.Evolution.TraitTests;


import com.Evolution.exceptions.*;
import com.Evolution.abstracts.ATrait;
import com.Evolution.interfaces.IPlayer;
import com.Evolution.interfaces.ISpecies;
import com.Evolution.logic.*;
import com.Evolution.traits.Foraging;
import org.easymock.EasyMock;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

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
    public void testEatWithForaging1Population() throws IllegalNumberOfPlayers, NullGameObjectException, IllegalCardFoodException, IllegalCardDirectionException, SpeciesNumberTraitsException, SpeciesDuplicateTraitException, SpeciesFullException, SpeciesPopulationException, FoodBankEmptyException, InvalidPlayerSelectException, IllegalSpeciesIndexException, WateringHoleEmptyException {
        Game g = new Game(generateNumRealPlayers(3), new WateringHole(), EasyMock.niceMock(Deck.class), EasyMock.niceMock(Deck.class));
        g.getPlayerObjects().get(0).getSpecies().get(0).addTrait(new Card("Foraging", "", "", 0, 0));
        g.getWateringHole().addFood();
        try {
            g.feedPlayerSpecies(0, 0);
        }catch (SpeciesFullException e){
            assertTrue(g.getPlayerObjects().get(0).getSpecies().get(0).getEatenFood() == 1);
            return;
        }
        fail("Should throw a SpeciesFullException as the foraging will try to eat a second time");
    }

}
