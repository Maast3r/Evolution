package com.Evolution;

import com.Evolution.interfaces.IPlayer;
import com.Evolution.logic.*;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

/**
 * Created by maas on 3/28/2016.
 */
public class PhaseOneTests {
    @Test
    public void testInit(){
        ArrayList<IPlayer> players = new ArrayList<IPlayer>();
        players.add(new Player(new Species()));
        Deck<Card> drawPile = new Deck();
        Deck<Card> discardPile = new Deck();

        PhaseOne p = new PhaseOne(players, drawPile, discardPile);
        assertEquals(1, p.getPlayers().size());
        assertEquals(0, p.getDrawPile().size());
        assertEquals(0, p.getDiscardPile().size());
    }
}
