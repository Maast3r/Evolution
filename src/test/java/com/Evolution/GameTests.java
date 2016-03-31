package com.Evolution;

import com.Evolution.exceptions.*;
import com.Evolution.interfaces.*;
import com.Evolution.logic.*;
import com.Evolution.testClasses.*;
import com.sun.xml.internal.bind.v2.model.core.ID;
import com.sun.xml.internal.bind.v2.runtime.reflect.Lister;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.easymock.EasyMock;

import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class GameTests {
    private IWateringHole wateringHole;
    private IDeck<ICard> drawPile;
    private IDeck<ICard> discardPile;

    private ArrayList<IPlayer> generateNumPlayers(int num) {
        ArrayList<IPlayer> players = new ArrayList<>();
        for (int i = 0; i < num; i++) {
            players.add(new TestPlayer(new TestSpecies()));
        }
        return players;
    }

    @Before
    public void initObjects() {
        wateringHole = new TestWateringHole();
        drawPile = EasyMock.niceMock(Deck.class);
        discardPile = EasyMock.niceMock(Deck.class);
    }

    @Test
    public void testCreateNewGame1() throws IllegalNumberOfPlayers,
            IllegalCardDirectionException {
        Game g = new Game(generateNumPlayers(3), wateringHole, this.drawPile, this.discardPile);
        assertEquals(3, g.getPlayerObjects().size());
    }

    @Test
    public void testCreateNewGame2() throws IllegalNumberOfPlayers,
            IllegalCardDirectionException {
        Game g = new Game(generateNumPlayers(4), wateringHole, this.drawPile, this.discardPile);
        assertEquals(4, g.getPlayerObjects().size());
    }

    @Test
    public void testCreateNewGame3() throws IllegalNumberOfPlayers,
            IllegalCardDirectionException {
        Game g = new Game(generateNumPlayers(5), wateringHole, this.drawPile, this.discardPile);
        assertEquals(5, g.getPlayerObjects().size());
    }

    @Test(expected = IllegalNumberOfPlayers.class)
    public void testValidNumberOfPlayersSub3() throws IllegalNumberOfPlayers,
            IllegalCardDirectionException {
        Game g = new Game(generateNumPlayers(0), wateringHole, this.drawPile, this.discardPile);
    }

    @Test(expected = IllegalNumberOfPlayers.class)
    public void testValidNumberOfPlayersGre6() throws IllegalNumberOfPlayers,
            IllegalCardDirectionException {
        Game g = new Game(generateNumPlayers(7), wateringHole, this.drawPile, this.discardPile);
    }

    @Test
    public void getPlayers1() throws IllegalNumberOfPlayers,
            IllegalCardDirectionException {
        IWateringHole wateringHole = new TestWateringHole();
        Game g = new Game(generateNumPlayers(4), wateringHole, this.drawPile, this.discardPile);
        assertEquals(4, g.getPlayerObjects().size());
    }

    @Test
    public void getPlayers2() throws IllegalNumberOfPlayers,
            IllegalCardDirectionException {
        IWateringHole wateringHole = new TestWateringHole();
        Game g = new Game(generateNumPlayers(5), wateringHole, this.drawPile, this.discardPile);
        assertEquals(5, g.getPlayerObjects().size());
    }

    @Test
    public void getPlayers3() throws IllegalNumberOfPlayers,
            IllegalCardDirectionException {
        Game g = new Game(generateNumPlayers(3), wateringHole, this.drawPile, this.discardPile);
        assertEquals(3, g.getPlayerObjects().size());
    }

    @Test
    public void getDrawPile() throws IllegalNumberOfPlayers,
            IllegalCardDirectionException {
        Game g = new Game(generateNumPlayers(4), wateringHole, this.drawPile, this.discardPile);
        assertTrue(g.getDrawPile() == this.drawPile);
        assertEquals(0, g.getDrawPile().getSize());
    }

    @Test
    public void getDiscardPile1() throws IllegalNumberOfPlayers,
            IllegalCardDirectionException {
        Game g = new Game(generateNumPlayers(5), wateringHole, this.drawPile, this.discardPile);
        assertTrue(this.discardPile == g.getDiscardPile());
        assertEquals(0, g.getDiscardPile().getSize());
    }

    @Test
    public void getDiscardPile2() throws IllegalNumberOfPlayers,
            IllegalCardDirectionException {
        IDeck<ICard> discardPile = new Deck<>();
        Game g = new Game(generateNumPlayers(5), wateringHole, this.drawPile, discardPile);
        g.getDiscardPile().discard(new TestCard());
        assertEquals(1, g.getDiscardPile().getSize());
    }

    @Test
    public void testGetCurrentRound() throws IllegalNumberOfPlayers,
            IllegalCardDirectionException {
        Game g = new Game(generateNumPlayers(4), wateringHole, this.drawPile, this.discardPile);
        assertEquals(1, g.getRound());
    }

    @Test
    public void testIncreaseRound() throws IllegalNumberOfPlayers,
            IllegalCardDirectionException {
        Game g = new Game(generateNumPlayers(5), wateringHole, this.drawPile, this.discardPile);
        g.increaseRound();
        assertEquals(2, g.getRound());
    }

    @Test
    public void testIncreaseMultiRound() throws IllegalNumberOfPlayers,
            IllegalCardDirectionException {
        Game g = new Game(generateNumPlayers(5), wateringHole, this.drawPile, this.discardPile);
        for (int i = 0; i < 4; i++) {
            g.increaseRound();
            assertEquals(i + 2, g.getRound());
        }
    }

    @Test
    public void testStartPhase() throws IllegalNumberOfPlayers,
            IllegalCardDirectionException, DeckEmptyException, InvalidPlayerSelectException {
        IPhases fakePhaseOne = EasyMock.niceMock(PhaseOne.class);
        fakePhaseOne.execute();
        EasyMock.replay(fakePhaseOne);
        Game g = new Game(generateNumPlayers(4), wateringHole, this.drawPile, this.discardPile);
        g.setPhase(fakePhaseOne);
        g.startGame();
        EasyMock.verify(fakePhaseOne);
    }

    @Test
    public void testGetTurn1() throws IllegalNumberOfPlayers, IllegalCardDirectionException {
        Game g = new Game(generateNumPlayers(5), wateringHole, this.drawPile, this.discardPile);
        assertTrue(g.getTurn() == 1);
    }

    @Test
    public void testGetTurn3() throws IllegalNumberOfPlayers, IllegalCardDirectionException {
        Game g = new Game(generateNumPlayers(5), wateringHole, this.drawPile, this.discardPile);
        g.nextTurn();
        g.nextTurn();
        assertTrue(g.getTurn() == 3);
    }

    @Test
    public void testGetTurn6() throws IllegalNumberOfPlayers, IllegalCardDirectionException {
        Game g = new Game(generateNumPlayers(5), wateringHole, this.drawPile, this.discardPile);
        for (int j = 0; j < 5; j++) {
            g.nextTurn();
        }
        assertTrue(g.getTurn() == 1);
    }

    @Test
    public void testGetWateringHole() throws IllegalNumberOfPlayers, IllegalCardDirectionException {
        Game g = new Game(generateNumPlayers(4), wateringHole, this.drawPile, this.discardPile);
        assertTrue(g.getWateringHole() == wateringHole);
    }

    @Test
    public void testGetFoodBankCount() throws IllegalNumberOfPlayers, IllegalCardDirectionException {
        Game g = new Game(generateNumPlayers(4), this.wateringHole, this.drawPile, this.discardPile);
        assertEquals(240, g.getFoodBankCount());
    }

    @Test
    public void testDecrementFoodBank() throws IllegalNumberOfPlayers, IllegalCardDirectionException, FoodBankEmptyException {
        Game g = new Game(generateNumPlayers(4), this.wateringHole, this.drawPile, this.discardPile);
        for (int i = 1; i < 50; i++) {
            g.decrementFoodBank();
            assertEquals(240 - i, g.getFoodBankCount());
        }
    }

    @Test
    public void testDecrementNFoodBank() throws IllegalNumberOfPlayers, IllegalCardDirectionException, FoodBankEmptyException {
        Game g = new Game(generateNumPlayers(4), this.wateringHole, this.drawPile, this.discardPile);
        for (int i = 1; i < 10; i++) {
            g.decrementFoodBank(5);
            assertEquals(240 - (i * 5), g.getFoodBankCount());
        }
    }

    @Test
    public void testMoveFoodFromBankToHole() throws IllegalNumberOfPlayers, IllegalCardDirectionException, FoodBankEmptyException {
        IWateringHole wateringHole = new WateringHole();
        Game g = new Game(generateNumPlayers(4), wateringHole, this.drawPile, this.discardPile);
        g.moveFoodFromBankToHole(1);
        assertEquals(239, g.getFoodBankCount());
        assertEquals(1, g.getWateringHole().getFoodCount());
    }

    @Test
    public void testMoveNFoodFromBankToHole() throws IllegalNumberOfPlayers, IllegalCardDirectionException, FoodBankEmptyException {
        IWateringHole wateringHole = new WateringHole();
        Game g = new Game(generateNumPlayers(4), wateringHole, this.drawPile, this.discardPile);
        for (int i = 1; i < 50; i++) {
            g.moveFoodFromBankToHole(1);
            assertEquals(240 - i, g.getFoodBankCount());
            assertEquals(i, g.getWateringHole().getFoodCount());
        }
    }

    @Test
    public void testMoveNFoodFromBankToHole2() throws IllegalNumberOfPlayers, IllegalCardDirectionException, FoodBankEmptyException {
        IWateringHole wateringHole = new WateringHole();
        Game g = new Game(generateNumPlayers(4), wateringHole, this.drawPile, this.discardPile);
        for (int i = 1; i < 10; i++) {
            g.moveFoodFromBankToHole(5);
            assertEquals(240 - (i * 5), g.getFoodBankCount());
            assertEquals(i * 5, g.getWateringHole().getFoodCount());
        }
    }

    @Test(expected = FoodBankEmptyException.class)
    public void testFoodBankOneEmpty() throws IllegalNumberOfPlayers, IllegalCardDirectionException, FoodBankEmptyException {
        IWateringHole wateringHole = new WateringHole();
        Game g = new Game(generateNumPlayers(4), wateringHole, this.drawPile, this.discardPile);
        for(int i=0; i<241; i++){
            g.decrementFoodBank();
        }
    }

    @Test(expected = FoodBankEmptyException.class)
    public void testFoodBankIEmpty1() throws IllegalNumberOfPlayers, IllegalCardDirectionException, FoodBankEmptyException {
        IWateringHole wateringHole = new WateringHole();
        Game g = new Game(generateNumPlayers(4), wateringHole, this.drawPile, this.discardPile);
        g.decrementFoodBank(241);
    }

    @Test(expected = FoodBankEmptyException.class)
    public void testFoodBankIEmpty2() throws IllegalNumberOfPlayers, IllegalCardDirectionException, FoodBankEmptyException {
        IWateringHole wateringHole = new WateringHole();
        Game g = new Game(generateNumPlayers(4), wateringHole, this.drawPile, this.discardPile);
        g.decrementFoodBank(-241);
    }
    @Test
    public void testDealToPlayerValid() throws IllegalNumberOfPlayers, IllegalCardDirectionException, DeckEmptyException, InvalidPlayerSelectException {
        Game g = new Game(generateNumPlayers(4), this.wateringHole, this.drawPile, this.discardPile);
        IPlayer fakePlayer = EasyMock.niceMock(Player.class);
        ICard fakeCard = EasyMock.niceMock(Card.class);

        try {
            Field playerList = g.getClass().getDeclaredField("players");
            playerList.setAccessible(true);
            ArrayList<IPlayer> fakePlayerList = new ArrayList<>();
            fakePlayerList.add(fakePlayer);
            playerList.set(g, fakePlayerList);
        } catch (Exception e) {
            Assert.fail();
        }

        EasyMock.expect(this.drawPile.draw()).andReturn(fakeCard);
        fakePlayer.addCardToHand(fakeCard);

        EasyMock.replay(this.drawPile);
        EasyMock.replay(fakePlayer);
        EasyMock.replay(fakeCard);

        g.dealToPlayer(0);

        EasyMock.verify(this.drawPile);
        EasyMock.verify(fakePlayer);
        EasyMock.verify(fakeCard);
    }

    @Test
    public void testDealToPlayerResults() throws IllegalNumberOfPlayers, IllegalCardDirectionException, DeckEmptyException, InvalidPlayerSelectException {
        Deck<ICard> drawPile = new Deck<>();
        ICard card = new TestCard();
        drawPile.discard(card);
        drawPile.contains(card);
        Player player = new Player(new TestSpecies());
        ArrayList<IPlayer> playerList = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            playerList.add(player);
        }
        Game g = new Game(playerList, this.wateringHole, drawPile, this.discardPile);
        g.dealToPlayer(0);
        assertTrue(player.getCards().get(0).equals(card));
        assertTrue(!drawPile.contains(card));

    }

    @Test
    public void testRemovePlayerValid() throws IllegalNumberOfPlayers, IllegalCardDirectionException, NoSuchFieldException, IllegalAccessException {
        Game g = new Game(generateNumPlayers(4), this.wateringHole, this.drawPile, this.discardPile);
        IPlayer fakePlayer = EasyMock.niceMock(Player.class);
        ICard fakeCard = EasyMock.niceMock(Card.class);

        Field playerList = g.getClass().getDeclaredField("players");
        playerList.setAccessible(true);
        ArrayList<IPlayer> fakePlayerList = new ArrayList<>();
        fakePlayerList.add(fakePlayer);
        playerList.set(g, fakePlayerList);
        EasyMock.expect(fakePlayer.removeCardFromHand(fakeCard)).andReturn(true);
        this.discardPile.discard(fakeCard);

        EasyMock.replay(this.discardPile);
        EasyMock.replay(fakePlayer);
        EasyMock.replay(fakeCard);

        g.discardFromPlayer(0, fakeCard);

        EasyMock.verify(this.discardPile);
        EasyMock.verify(fakePlayer);
        EasyMock.verify(fakeCard);
    }

    @Test
    public void testRemoveFromPlayerResults() throws IllegalNumberOfPlayers, IllegalCardDirectionException {
        Deck<ICard> discardPile = new Deck<>();
        ICard card = new TestCard();
        assertTrue(!discardPile.contains(card));
        Player player = new Player(new TestSpecies());
        ArrayList<IPlayer> playerList = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            playerList.add(player);
        }
        Game g = new Game(playerList, this.wateringHole, this.drawPile, discardPile);
        player.addCardToHand(card);
        assertTrue(player.getCards().get(0).equals(card));
        assertTrue(g.discardFromPlayer(0, card));
        assertTrue(discardPile.contains(card));

    }

    @Test(expected = InvalidPlayerSelectException.class)
    public void testInvalidPlayerSelect1() throws IllegalNumberOfPlayers, IllegalCardDirectionException, DeckEmptyException, InvalidPlayerSelectException {
        Deck<ICard> discardPile = new Deck<>();
        ICard card = new TestCard();
        assertTrue(!discardPile.contains(card));
        Player player = new Player(new TestSpecies());
        ArrayList<IPlayer> playerList = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            playerList.add(player);
        }
        Game g = new Game(playerList, this.wateringHole, this.drawPile, discardPile);
        g.dealToPlayer(4);
    }

    @Test(expected = InvalidPlayerSelectException.class)
    public void testInvalidPlayerSelect2() throws IllegalNumberOfPlayers, IllegalCardDirectionException,
            DeckEmptyException, InvalidPlayerSelectException {
        Deck<ICard> discardPile = new Deck<>();
        ICard card = new TestCard();
        assertTrue(!discardPile.contains(card));
        Player player = new Player(new TestSpecies());
        ArrayList<IPlayer> playerList = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            playerList.add(player);
        }
        Game g = new Game(playerList, this.wateringHole, this.drawPile, discardPile);
        g.dealToPlayer(-1);
    }

    @Test
    public void testGetPhase() throws IllegalNumberOfPlayers, IllegalCardDirectionException {
        Deck<ICard> discardPile = new Deck<>();
        ICard card = new TestCard();
        assertTrue(!discardPile.contains(card));
        Player player = new Player(new TestSpecies());
        ArrayList<IPlayer> playerList = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            playerList.add(player);
        }
        Game g = new Game(playerList, this.wateringHole, this.drawPile, discardPile);
        assertEquals(PhaseOne.class, g.getPhase().getClass());
    }

    @Test
    public void testSetPhase() throws IllegalNumberOfPlayers, IllegalCardDirectionException {
        Deck<ICard> discardPile = new Deck<>();
        ICard card = new TestCard();
        assertTrue(!discardPile.contains(card));
        Player player = new Player(new TestSpecies());
        ArrayList<IPlayer> playerList = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            playerList.add(player);
        }
        Game g = new Game(playerList, this.wateringHole, this.drawPile, discardPile);
        g.setPhase(new PhaseTwo(g));
        assertEquals(PhaseTwo.class, g.getPhase().getClass());
    }

}
