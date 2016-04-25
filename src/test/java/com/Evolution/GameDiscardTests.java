package com.Evolution;

import com.Evolution.exceptions.*;
import com.Evolution.interfaces.ICard;
import com.Evolution.interfaces.IDeck;
import com.Evolution.interfaces.IPlayer;
import com.Evolution.interfaces.IWateringHole;
import com.Evolution.logic.*;
import com.Evolution.testClasses.TestCard;
import org.easymock.EasyMock;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

public class GameDiscardTests {
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
            throws NullGameObjectException {
        for(int j=0; j < i; j++){
            ICard card = EasyMock.niceMock(Card.class);
            p.get(j).addCardToHand(card);
        }
        return p;
    }

    @Test
    public void testDiscardToIncreasePopulationIntegration() throws NullGameObjectException,
            IllegalNumberOfPlayers, InvalidPlayerSelectException,
            IllegalSpeciesIndexException, SpeciesPopulationException,
            IllegalCardRemovalException, IllegalCardDiscardException,
            DeckEmptyException, IllegalCardDirectionException {
        ArrayList<IPlayer> playerList = generateNumRealPlayers(3);
        playerList = addCardsToPlayers(playerList, 3);
        Game g = new Game(playerList, this.wateringHole, this.drawPile, this.discardPile);
        int expected = g.getDiscardPile().getSize() + 1;
        g.increasePopulation(0, 0, playerList.get(0).getCards()
                .get(0));
        assertEquals(expected, g.getDiscardPile().getSize());
    }

    @Test
    public void testDiscardToIncreasePopulationIntegration2() throws NullGameObjectException,
            IllegalNumberOfPlayers, InvalidPlayerSelectException,
            IllegalSpeciesIndexException, SpeciesPopulationException,
            IllegalCardRemovalException, IllegalCardDiscardException,
            DeckEmptyException, IllegalCardDirectionException {
        ArrayList<IPlayer> playerList = generateNumRealPlayers(5);
        playerList = addCardsToPlayers(playerList, 5);
        Game g = new Game(playerList, this.wateringHole, this.drawPile, this.discardPile);
        int expected = g.getDiscardPile().getSize() + 1;
        g.increasePopulation(0, 0, playerList.get(0).getCards()
                .get(0));
        assertEquals(expected, g.getDiscardPile().getSize());
    }
}
