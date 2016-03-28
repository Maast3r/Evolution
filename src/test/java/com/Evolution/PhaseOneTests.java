package com.Evolution;

import com.Evolution.exceptions.IllegalCardDirectionException;
import com.Evolution.interfaces.IPlayer;
import com.Evolution.logic.*;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Random;

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

    @Test
    public void testInitRandom() throws IllegalCardDirectionException {
        ArrayList<IPlayer> players = new ArrayList<IPlayer>();
        Random rn = new Random();
        int random = rn.nextInt(1000 - 0 + 1) + 0;
        Deck<Card> drawPile = new Deck();
        Deck<Card> discardPile = new Deck();
        for(int i=0; i<random; i++){
            players.add(new Player(new Species()));
            drawPile.add(new Card("Carnivore", "Makes a species a carnivore",
                    "./carnivore.jpg", 3, 0));
            discardPile.add(new Card("Carnivore", "Makes a species a carnivore",
                    "./carnivore.jpg", 3, 0));
        }


        PhaseOne p = new PhaseOne(players, drawPile, discardPile);
        assertEquals(random, p.getPlayers().size());
        assertEquals(random, p.getDrawPile().size());
        assertEquals(random, p.getDiscardPile().size());
    }
}
