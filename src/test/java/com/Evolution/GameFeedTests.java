package com.Evolution;


import com.Evolution.exceptions.IllegalCardDirectionException;
import com.Evolution.exceptions.IllegalCardFoodException;
import com.Evolution.exceptions.IllegalNumberOfPlayers;
import com.Evolution.exceptions.NullGameObjectException;
import com.Evolution.interfaces.ICard;
import com.Evolution.interfaces.IDeck;
import com.Evolution.interfaces.IPlayer;
import com.Evolution.interfaces.IWateringHole;
import com.Evolution.logic.*;
import org.junit.Test;

import java.util.ArrayList;

import static junit.framework.TestCase.assertEquals;

public class GameFeedTests {
    private IWateringHole wateringHole = new WateringHole();
    private IDeck<ICard> drawPile = new Deck<>();
    private IDeck<ICard> discardPile = new Deck<>();

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

    private ArrayList<IPlayer> addCardsToPlayers(ArrayList<IPlayer> p, int i)
            throws NullGameObjectException, IllegalCardFoodException,
            IllegalCardDirectionException {
        for(int j=0; j < i; j++){
            ICard card = new Card("Carnivore", "Makes a species a carnivore",
                    "./carnivore.jpg", 3, 0);
            p.get(j).addCardToHand(card);
        }
        return p;
    }

    @Test
    public void testFeedPlayer() throws NullGameObjectException, IllegalCardFoodException,
            IllegalCardDirectionException, IllegalNumberOfPlayers {
        ArrayList<IPlayer> playerList = generateNumRealPlayers(3);
        playerList = addCardsToPlayers(playerList, 3);
        Game g = new Game(playerList, this.wateringHole, this.drawPile, this.discardPile);
        int expected = g.getPlayerObjects().get(0).getSpecies().get(0).getEatenFood() + 1;
        g.feedPlayerSpecies(0, 0);
        assertEquals(expected, g.getPlayerObjects().get(0).getSpecies()
            .get(0).getEatenFood());
    }
}
