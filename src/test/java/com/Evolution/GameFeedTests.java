package com.Evolution;


import com.Evolution.exceptions.*;
import com.Evolution.interfaces.ICard;
import com.Evolution.interfaces.IDeck;
import com.Evolution.interfaces.IPlayer;
import com.Evolution.interfaces.IWateringHole;
import com.Evolution.logic.*;
import org.easymock.EasyMock;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

import static junit.framework.TestCase.assertEquals;

@RunWith(Parameterized.class)
public class GameFeedTests {
    private IWateringHole wateringHole;
    private IDeck<ICard> drawPile;
    private IDeck<ICard> discardPile;
    private int numPlayers;
    private int playerIndex;

    public GameFeedTests(int numPlayers, int playerIndex) {
        this.numPlayers = numPlayers;
        this.playerIndex = playerIndex;
    }


    @Parameterized.Parameters
    public static Collection playersToCheck() {
        return Arrays.asList(new Object[][]{
                {3, 0}, {3, 1}, {3, 2},
                {4, 0}, {4, 1}, {4, 2}, {4, 3},
                {5, 0}, {5, 1}, {5, 2}, {5, 3}, {5, 4}
        });
    }

    private ArrayList<IPlayer> generateNumPlayers(int num) {
        ArrayList<IPlayer> players = new ArrayList<>();
        for (int i = 0; i < num; i++) {
            players.add(EasyMock.niceMock(Player.class));
        }
        return players;
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

    @Before
    public void initObjects() {
        wateringHole = EasyMock.niceMock(WateringHole.class);
        drawPile = EasyMock.niceMock(Deck.class);
        discardPile = EasyMock.niceMock(Deck.class);
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
            IllegalCardDirectionException, IllegalNumberOfPlayers,
            InvalidPlayerSelectException, IllegalSpeciesIndexException,
            SpeciesFullException {
        Game g = new Game(generateNumRealPlayers(4), this.wateringHole, this.drawPile,
                this.discardPile);
        int expected = g.getPlayerObjects().get(0).getSpecies().get(0).getEatenFood() + 1;
        g.feedPlayerSpecies(0, 0);
        assertEquals(expected, g.getPlayerObjects().get(0).getSpecies()
            .get(0).getEatenFood());
    }

    @Test
    public void testFeedPlayer2() throws NullGameObjectException, IllegalCardFoodException,
            IllegalCardDirectionException, IllegalNumberOfPlayers,
            InvalidPlayerSelectException, IllegalSpeciesIndexException,
            SpeciesFullException {
        Game g = new Game(generateNumRealPlayers(5), this.wateringHole, this.drawPile,
                this.discardPile);
        int expected = g.getPlayerObjects().get(4).getSpecies().get(0).getEatenFood() + 1;
        g.feedPlayerSpecies(4, 0);
        assertEquals(expected, g.getPlayerObjects().get(4).getSpecies()
                .get(0).getEatenFood());
    }
}
