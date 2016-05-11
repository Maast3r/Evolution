package com.Evolution.TraitTests;

import com.Evolution.exceptions.*;
import com.Evolution.abstracts.ATrait;
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
}
