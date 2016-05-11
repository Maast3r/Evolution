package com.Evolution.TraitTests;

import com.Evolution.exceptions.*;
import com.Evolution.abstracts.ATrait;
import com.Evolution.interfaces.IDeck;
import com.Evolution.interfaces.IPlayer;
import com.Evolution.interfaces.ISpecies;
import com.Evolution.interfaces.IWateringHole;
import com.Evolution.logic.Card;
import com.Evolution.logic.Game;
import com.Evolution.logic.Player;
import com.Evolution.logic.Species;
import com.Evolution.traits.LongNeck;
import org.easymock.EasyMock;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by goistjt on 4/27/2016.
 */
public class LongNeckTests {
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
            IllegalCardRemovalException, IllegalCardDiscardException,
            SpeciesPopulationException, FoodBankEmptyException {
        Game g = EasyMock.niceMock(Game.class);
        Player p = EasyMock.niceMock(Player.class);
        Species s = EasyMock.niceMock(Species.class);
        Card c = EasyMock.niceMock(Card.class);

        ATrait t = new LongNeck(g);
        g.feedPlayerSpeciesFromBank(0, 0);
        EasyMock.expect(g.getPlayerObjects()).andReturn(new ArrayList<>(Arrays.asList(p))).times(2);
        EasyMock.expect(p.getSpecies()).andReturn(new ArrayList<>(Arrays.asList(s))).times(2);
        EasyMock.expect(s.getTraits()).andReturn(new ArrayList<>(Arrays.asList(c))).times(2);
        EasyMock.expect(c.getName()).andReturn("Foraging").times(2);
        EasyMock.replay(g, p, s, c);
        t.executeTrait(new int[]{0, -1}, new int[]{0, -1});
        EasyMock.verify(g, p, s, c);
    }

    @Test
    public void callWithRealCard() throws SpeciesFullException, SpeciesPopulationException, FoodBankEmptyException, InvalidPlayerSelectException, IllegalSpeciesIndexException, WateringHoleEmptyException, NullGameObjectException, IllegalCardFoodException, IllegalCardDirectionException {
        Game g = EasyMock.niceMock(Game.class);
        Player p = EasyMock.niceMock(Player.class);
        Species s = EasyMock.niceMock(Species.class);
        ATrait t = new LongNeck(g);
        g.feedPlayerSpeciesFromBank(0, 0);
        EasyMock.expect(g.getPlayerObjects()).andReturn(new ArrayList<>(Arrays.asList(p))).times(2);
        EasyMock.expect(p.getSpecies()).andReturn(new ArrayList<>(Arrays.asList(s))).times(2);
        EasyMock.expect(s.getTraits()).andReturn(new ArrayList<>(Arrays.asList(new Card("Foraging", "", "", 0, 0)))).times(2);
        EasyMock.replay(g, p, s);
        t.executeTrait(new int[]{0, -1}, new int[]{0, -1});
        EasyMock.verify(g, p, s);
    }

    @Test
    public void callWithRealSpecies() throws SpeciesFullException, SpeciesPopulationException, FoodBankEmptyException, InvalidPlayerSelectException, IllegalSpeciesIndexException, WateringHoleEmptyException, NullGameObjectException, IllegalCardFoodException, IllegalCardDirectionException, SpeciesNumberTraitsException, SpeciesDuplicateTraitException {
        Game g = EasyMock.niceMock(Game.class);
        Player p = EasyMock.niceMock(Player.class);
        Species s = new Species();
        s.addTrait(new Card("Foraging", "", "", 0, 0));
        ATrait t = new LongNeck(g);
        g.feedPlayerSpeciesFromBank(0, 0);
        EasyMock.expect(g.getPlayerObjects()).andReturn(new ArrayList<>(Arrays.asList(p))).times(2);
        EasyMock.expect(p.getSpecies()).andReturn(new ArrayList<>(Arrays.asList(s))).times(2);
        EasyMock.replay(g, p);
        t.executeTrait(new int[]{0, -1}, new int[]{0, -1});
        EasyMock.verify(g, p);
    }

    @Test
    public void callWithRealPlayer() throws SpeciesFullException, SpeciesPopulationException, FoodBankEmptyException, InvalidPlayerSelectException, IllegalSpeciesIndexException, WateringHoleEmptyException, NullGameObjectException, IllegalCardFoodException, IllegalCardDirectionException, SpeciesNumberTraitsException, SpeciesDuplicateTraitException {
        Game g = EasyMock.niceMock(Game.class);
        Species s = new Species();
        Player p = new Player(s);
        s.addTrait(new Card("Foraging", "", "", 0, 0));
        ATrait t = new LongNeck(g);
        g.feedPlayerSpeciesFromBank(0, 0);
        EasyMock.expect(g.getPlayerObjects()).andReturn(new ArrayList<>(Arrays.asList(p))).times(2);
        EasyMock.replay(g);
        t.executeTrait(new int[]{0, -1}, new int[]{0, -1});
        EasyMock.verify(g);
    }

    @Test
    public void callWithRealGame() throws SpeciesFullException, SpeciesPopulationException, FoodBankEmptyException, InvalidPlayerSelectException, IllegalSpeciesIndexException, WateringHoleEmptyException, NullGameObjectException, IllegalCardFoodException, IllegalCardDirectionException, SpeciesNumberTraitsException, SpeciesDuplicateTraitException, IllegalNumberOfPlayers {
        Game g = new Game(generateNumRealPlayers(3), EasyMock.niceMock(IWateringHole.class), EasyMock.niceMock(IDeck.class), EasyMock.niceMock(IDeck.class));
        IPlayer p = g.getPlayerObjects().get(0);
        ISpecies s = p.getSpecies().get(0);
        ATrait t = new LongNeck(g);
        t.executeTrait(new int[]{0, -1}, new int[]{0, -1});
        assertTrue(s.getEatenFood() == 1);
    }

    @Test
    public void callWithRealGameWithForaging() throws SpeciesFullException, SpeciesPopulationException, FoodBankEmptyException, InvalidPlayerSelectException, IllegalSpeciesIndexException, WateringHoleEmptyException, NullGameObjectException, IllegalCardFoodException, IllegalCardDirectionException, SpeciesNumberTraitsException, SpeciesDuplicateTraitException, IllegalNumberOfPlayers {
        Game g = new Game(generateNumRealPlayers(3), EasyMock.niceMock(IWateringHole.class), EasyMock.niceMock(IDeck.class), EasyMock.niceMock(IDeck.class));
        IPlayer p = g.getPlayerObjects().get(0);
        ISpecies s = p.getSpecies().get(0);
        s.increasePopulation();
        s.addTrait(new Card("Foraging", "", "", 0, 0));
        ATrait t = new LongNeck(g);
        t.executeTrait(new int[]{0, -1}, new int[]{0, -1});
        assertTrue(s.getEatenFood() == 2);
    }
}
