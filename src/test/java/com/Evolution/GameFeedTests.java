package com.Evolution;


import com.Evolution.exceptions.*;
import com.Evolution.interfaces.*;
import com.Evolution.logic.*;
import org.easymock.EasyMock;
import org.junit.Assert;
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
    private ArrayList<ISpecies> s;

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
        generateMockSpecies(num);
        for (int i = 0; i < num; i++) {
            Player p = EasyMock.createMockBuilder(Player.class)
                    .withConstructor(ISpecies.class)
                    .withArgs(s.get(i))
                    .createMock();
            players.add(p);
        }
        return players;
    }

    private void generateMockSpecies(int num) {
        s = new ArrayList<>();
        for (int i = 0; i < num; i++) {
            s.add(EasyMock.niceMock(Species.class));
        }
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
        wateringHole = new WateringHole();
        drawPile = EasyMock.niceMock(Deck.class);
        discardPile = EasyMock.niceMock(Deck.class);
    }

    private ArrayList<IPlayer> addCardsToPlayers(ArrayList<IPlayer> p, int i)
            throws NullGameObjectException, IllegalCardFoodException,
            IllegalCardDirectionException {
        for (int j = 0; j < i; j++) {
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
            SpeciesFullException, WateringHoleEmptyException {
        Game g = new Game(generateNumRealPlayers(this.numPlayers), this.wateringHole, this.drawPile,
                this.discardPile);
        int expected = g.getPlayerObjects().get(this.playerIndex).getSpecies().get(0).getEatenFood() + 1;
        g.getWateringHole().addFood();
        int expected2 = g.getWateringHole().getFoodCount() - 1;
        g.feedPlayerSpecies(this.playerIndex, 0);
        assertEquals(expected, g.getPlayerObjects().get(this.playerIndex).getSpecies()
                .get(0).getEatenFood());
        assertEquals(expected2, g.getWateringHole().getFoodCount());
    }

    @Test(expected = InvalidPlayerSelectException.class)
    public void testFeedPlayer3() throws NullGameObjectException, IllegalCardFoodException,
            IllegalCardDirectionException, IllegalNumberOfPlayers,
            InvalidPlayerSelectException, IllegalSpeciesIndexException,
            SpeciesFullException, WateringHoleEmptyException {
        Game g = new Game(generateNumRealPlayers(this.numPlayers), this.wateringHole, this.drawPile,
                this.discardPile);
        int expected = g.getPlayerObjects().get(this.playerIndex).getSpecies().get(0).getEatenFood() + 1;
        g.getWateringHole().addFood();
        int expected2 = g.getWateringHole().getFoodCount() - 1;
        g.feedPlayerSpecies(-1, 0);
    }

    @Test(expected = InvalidPlayerSelectException.class)
    public void testFeedPlayer4() throws NullGameObjectException, IllegalCardFoodException,
            IllegalCardDirectionException, IllegalNumberOfPlayers,
            InvalidPlayerSelectException, IllegalSpeciesIndexException,
            SpeciesFullException, WateringHoleEmptyException {
        ArrayList<IPlayer> p = generateNumRealPlayers(this.numPlayers);
        Game g = new Game(p, this.wateringHole, this.drawPile,
                this.discardPile);
        g.feedPlayerSpecies(this.numPlayers, 0);
    }

    @Test(expected = IllegalSpeciesIndexException.class)
    public void testFeedPlayer5() throws NullGameObjectException, IllegalCardFoodException,
            IllegalCardDirectionException, IllegalNumberOfPlayers,
            InvalidPlayerSelectException, IllegalSpeciesIndexException,
            SpeciesFullException, WateringHoleEmptyException {
        ArrayList<IPlayer> p = generateNumRealPlayers(this.numPlayers);
        Game g = new Game(p, this.wateringHole, this.drawPile,
                this.discardPile);
        g.feedPlayerSpecies(this.playerIndex, 1);
    }

    @Test(expected = IllegalSpeciesIndexException.class)
    public void testFeedPlayer6() throws NullGameObjectException, IllegalCardFoodException,
            IllegalCardDirectionException, IllegalNumberOfPlayers,
            InvalidPlayerSelectException, IllegalSpeciesIndexException,
            SpeciesFullException, WateringHoleEmptyException {
        ArrayList<IPlayer> p = generateNumRealPlayers(this.numPlayers);
        Game g = new Game(p, this.wateringHole, this.drawPile,
                this.discardPile);
        g.feedPlayerSpecies(this.playerIndex, -1);
    }

    @Test(expected = WateringHoleEmptyException.class)
    public void testFeedPlayer7() throws NullGameObjectException, IllegalCardFoodException,
            IllegalCardDirectionException, IllegalNumberOfPlayers,
            InvalidPlayerSelectException, IllegalSpeciesIndexException,
            SpeciesFullException, WateringHoleEmptyException {
        ArrayList<IPlayer> p = generateNumRealPlayers(this.numPlayers);
        Game g = new Game(p, this.wateringHole, this.drawPile,
                this.discardPile);
        g.feedPlayerSpecies(this.playerIndex, 0);
    }

    @Test
    public void testFeedPlayerUnit() throws IllegalNumberOfPlayers,
            InvalidPlayerSelectException, IllegalSpeciesIndexException,
            SpeciesFullException, WateringHoleEmptyException, NullGameObjectException {

        ArrayList<IPlayer> players = generateNumPlayers(this.numPlayers);
        ISpecies fakeSpecies = EasyMock.niceMock(Species.class);
        Game g = new Game(players, this.wateringHole, this.drawPile, this.discardPile);
        EasyMock.replay(players.get(this.playerIndex));
        fakeSpecies.eat();
        EasyMock.replay();
        g.getWateringHole().addFood();
        g.feedPlayerSpecies(this.playerIndex, 0);
        EasyMock.verify(players.get(this.playerIndex));
        EasyMock.verify();
    }

    @Test
    public void testGetSpeciesFood() throws IllegalNumberOfPlayers, SpeciesFullException, InvalidPlayerSelectException,
            IllegalSpeciesIndexException, WateringHoleEmptyException, NullGameObjectException {
        ArrayList<IPlayer> players = generateNumRealPlayers(this.numPlayers);
        Game g = new Game(players, this.wateringHole, this.drawPile, this.discardPile);
        int food = g.getSpeciesFood(this.playerIndex, 0);
        assertEquals(0, food);
    }

    @Test(expected = InvalidPlayerSelectException.class)
    public void testGetSpeciesFoodInvalidPlayer() throws IllegalNumberOfPlayers, SpeciesFullException, InvalidPlayerSelectException,
            IllegalSpeciesIndexException, WateringHoleEmptyException, NullGameObjectException {
        ArrayList<IPlayer> players = generateNumRealPlayers(this.numPlayers);
        Game g = new Game(players, this.wateringHole, this.drawPile, this.discardPile);
        g.getSpeciesFood(-1, 0);
    }

    @Test(expected = InvalidPlayerSelectException.class)
    public void testGetSpeciesFoodInvalidPlayer2() throws IllegalNumberOfPlayers, SpeciesFullException,
            InvalidPlayerSelectException, IllegalSpeciesIndexException, WateringHoleEmptyException, NullGameObjectException {
        ArrayList<IPlayer> players = generateNumRealPlayers(this.numPlayers);
        Game g = new Game(players, this.wateringHole, this.drawPile, this.discardPile);
        g.getSpeciesFood(this.numPlayers, 0);
    }

    @Test(expected = IllegalSpeciesIndexException.class)
    public void testGetSpeciesFoodInvalidSpecies() throws IllegalNumberOfPlayers, SpeciesFullException,
            InvalidPlayerSelectException, IllegalSpeciesIndexException, WateringHoleEmptyException, NullGameObjectException {
        ArrayList<IPlayer> players = generateNumRealPlayers(this.numPlayers);
        Game g = new Game(players, this.wateringHole, this.drawPile, this.discardPile);
        g.getSpeciesFood(this.playerIndex, 1);
    }
}
