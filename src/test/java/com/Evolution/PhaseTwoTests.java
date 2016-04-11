package com.Evolution;

import com.Evolution.exceptions.DeckEmptyException;
import com.Evolution.exceptions.IllegalCardDirectionException;
import com.Evolution.exceptions.IllegalNumberOfPlayers;
import com.Evolution.exceptions.InvalidPlayerSelectException;
import com.Evolution.interfaces.ICard;
import com.Evolution.interfaces.IDeck;
import com.Evolution.interfaces.IPlayer;
import com.Evolution.interfaces.IWateringHole;
import com.Evolution.logic.*;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;


public class PhaseTwoTests {

    private ArrayList<IPlayer> generateNumPlayers(int num) {
        ArrayList<IPlayer> players = new ArrayList<>();
        for (int i = 0; i < num; i++) {
            players.add(new Player(new Species()));
        }
        return players;
    }


    @Test
    public void testNextPhase() throws IllegalCardDirectionException, IllegalNumberOfPlayers, DeckEmptyException, InvalidPlayerSelectException {
        IDeck<ICard> drawPile = new Deck<>();
        IDeck<ICard> discardPile = new Deck<>();
        IWateringHole wh = new WateringHole();
        Game g = new Game(generateNumPlayers(3), wh, drawPile, discardPile);
        PhaseTwo p = new PhaseTwo(g);
        p.execute();
        assertEquals(PhaseThree.class, g.getPhase().getClass());
    }

}
