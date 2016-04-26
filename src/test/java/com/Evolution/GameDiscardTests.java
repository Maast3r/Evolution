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
    public void testDiscardToIncreasePopulationIntegration() throws NullGameObjectException,
            IllegalNumberOfPlayers, InvalidPlayerSelectException,
            IllegalSpeciesIndexException, SpeciesPopulationException,
            IllegalCardRemovalException, IllegalCardDiscardException,
            DeckEmptyException, IllegalCardDirectionException, IllegalCardFoodException {

        ArrayList<IPlayer> playerList = generateNumRealPlayers(3);
        playerList = addCardsToPlayers(playerList, 3);
        Game g = new Game(playerList, this.wateringHole, this.drawPile, this.discardPile);
        int expected = g.getDiscardPile().getSize() + 1;
        int expected2 = playerList.get(0).getSpecies().get(0).getPopulation() + 1;
        int expected3 = playerList.get(0).getCards().size() - 1;
        g.increasePopulation(0, 0, playerList.get(0).getCards()
                .get(0));
        assertEquals(expected, g.getDiscardPile().getSize());
        assertEquals(expected2,
                g.getPlayerObjects().get(0).getSpecies().get(0)
                        .getPopulation());
        assertEquals(expected3, g.getPlayerObjects().get(0).getCards().size());
    }

    @Test
    public void testDiscardToIncreasePopulationIntegration2() throws NullGameObjectException,
            IllegalNumberOfPlayers, InvalidPlayerSelectException,
            IllegalSpeciesIndexException, SpeciesPopulationException,
            IllegalCardRemovalException, IllegalCardDiscardException,
            DeckEmptyException, IllegalCardDirectionException, IllegalCardFoodException {

        ArrayList<IPlayer> playerList = generateNumRealPlayers(5);
        playerList = addCardsToPlayers(playerList, 5);
        Game g = new Game(playerList, this.wateringHole, this.drawPile, this.discardPile);
        int expected = g.getDiscardPile().getSize() + 1;
        int expected2 = playerList.get(4).getSpecies().get(0).getPopulation() + 1;
        int expected3 = playerList.get(4).getCards().size() - 1;
        g.increasePopulation(4, 0, playerList.get(4).getCards()
                .get(0));
        assertEquals(expected, g.getDiscardPile().getSize());
        assertEquals(expected2,
                g.getPlayerObjects().get(4).getSpecies().get(0)
                        .getPopulation());
        assertEquals(expected3, g.getPlayerObjects().get(4).getCards().size());
    }

    @Test
    public void testDiscardToIncreaseBodyIntegration() throws NullGameObjectException,
            IllegalNumberOfPlayers, InvalidPlayerSelectException,
            IllegalSpeciesIndexException, SpeciesPopulationException,
            IllegalCardRemovalException, IllegalCardDiscardException,
            DeckEmptyException, IllegalCardDirectionException, IllegalCardFoodException,
            SpeciesBodySizeException {

        ArrayList<IPlayer> playerList = generateNumRealPlayers(3);
        playerList = addCardsToPlayers(playerList, 3);
        Game g = new Game(playerList, this.wateringHole, this.drawPile, this.discardPile);
        int expected = g.getDiscardPile().getSize() + 1;
        int expected2 = playerList.get(0).getSpecies().get(0).getBodySize() + 1;
        int expected3 = playerList.get(0).getCards().size() - 1;
        g.increaseBodySize(0, 0, playerList.get(0).getCards()
                .get(0));
        assertEquals(expected, g.getDiscardPile().getSize());
        assertEquals(expected2,
                g.getPlayerObjects().get(0).getSpecies().get(0)
                    .getBodySize());
        assertEquals(expected3, g.getPlayerObjects().get(0).getCards().size());
    }

    @Test
    public void testDiscardToIncreaseBodyIntegration2() throws NullGameObjectException,
            IllegalNumberOfPlayers, InvalidPlayerSelectException,
            IllegalSpeciesIndexException, SpeciesPopulationException,
            IllegalCardRemovalException, IllegalCardDiscardException,
            DeckEmptyException, IllegalCardDirectionException, IllegalCardFoodException,
            SpeciesBodySizeException {

        ArrayList<IPlayer> playerList = generateNumRealPlayers(5);
        playerList = addCardsToPlayers(playerList, 5);
        Game g = new Game(playerList, this.wateringHole, this.drawPile, this.discardPile);
        int expected = g.getDiscardPile().getSize() + 1;
        int expected2 = playerList.get(4).getSpecies().get(0).getBodySize() + 1;
        int expected3 = playerList.get(4).getCards().size() - 1;
        g.increaseBodySize(4, 0, playerList.get(4).getCards()
                .get(0));
        assertEquals(expected, g.getDiscardPile().getSize());
        assertEquals(expected2,
                g.getPlayerObjects().get(4).getSpecies().get(0)
                        .getBodySize());
        assertEquals(expected3, g.getPlayerObjects().get(4).getCards().size());
    }


    @Test
    public void testDiscardForLeftSpecies() throws NullGameObjectException,
            IllegalNumberOfPlayers, InvalidPlayerSelectException,
            IllegalSpeciesIndexException, SpeciesPopulationException,
            IllegalCardRemovalException, IllegalCardDiscardException,
            DeckEmptyException, IllegalCardDirectionException, IllegalCardFoodException,
            SpeciesBodySizeException {

        ArrayList<IPlayer> playerList = generateNumRealPlayers(3);
        playerList = addCardsToPlayers(playerList, 3);
        Game g = new Game(playerList, this.wateringHole, this.drawPile, this.discardPile);
        int expected = g.getDiscardPile().getSize() + 1;
        int expected2 = playerList.get(0).getSpecies().size() + 1;
        int expected3 = playerList.get(0).getCards().size() - 1;
        g.discardForLeftSpecies(0, playerList.get(0).getCards().get(0),
                new Species());
        assertEquals(expected, g.getDiscardPile().getSize());
        assertEquals(expected2,
                g.getPlayerObjects().get(0).getSpecies().size());
        assertEquals(expected3, g.getPlayerObjects().get(0).getCards().size());
    }

    @Test
    public void testDiscardForLeftSpecies2() throws NullGameObjectException,
            IllegalNumberOfPlayers, InvalidPlayerSelectException,
            IllegalSpeciesIndexException, SpeciesPopulationException,
            IllegalCardRemovalException, IllegalCardDiscardException,
            DeckEmptyException, IllegalCardDirectionException, IllegalCardFoodException,
            SpeciesBodySizeException {

        ArrayList<IPlayer> playerList = generateNumRealPlayers(5);
        playerList = addCardsToPlayers(playerList, 5);
        Game g = new Game(playerList, this.wateringHole, this.drawPile, this.discardPile);
        int expected = g.getDiscardPile().getSize() + 1;
        int expected2 = playerList.get(4).getSpecies().size() + 1;
        int expected3 = playerList.get(4).getSpecies().size() - 1;
        g.discardForLeftSpecies(4, playerList.get(4).getCards().get(0),
                new Species());
        assertEquals(expected, g.getDiscardPile().getSize());
        assertEquals(expected2,
                g.getPlayerObjects().get(4).getSpecies().size());
        assertEquals(expected3, g.getPlayerObjects().get(4).getCards().size());
    }
}
