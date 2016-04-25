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

    @Test
    public void testDiscardToIncreasePopulationIntegration() throws NullGameObjectException,
            IllegalNumberOfPlayers, InvalidPlayerSelectException,
            IllegalSpeciesIndexException, SpeciesPopulationException,
            IllegalCardRemovalException, IllegalCardDiscardException,
            DeckEmptyException, IllegalCardDirectionException {
        ArrayList<IPlayer> playerList = generateNumRealPlayers(3);
        for(int i = 0; i < 3; i ++) {
            ICard card = EasyMock.niceMock(Card.class);
            playerList.get(i).addCardToHand(card);
        }
        Game g = new Game(playerList, this.wateringHole, this.drawPile, this.discardPile);
        int expected = g.getDiscardPile().getSize() + 1;
        g.increasePopulation(0, 0, playerList.get(0).getCards()
                .get(0));
        assertEquals(expected, g.getDiscardPile().getSize());
    }
}
