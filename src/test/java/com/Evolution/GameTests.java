package com.Evolution;

import com.Evolution.exceptions.*;
import com.Evolution.interfaces.*;
import com.Evolution.logic.*;
import com.Evolution.testClasses.TestCard;
import com.Evolution.testClasses.TestPlayer;
import com.Evolution.testClasses.TestSpecies;
import com.Evolution.testClasses.TestWateringHole;
import org.easymock.EasyMock;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

import static org.easymock.EasyMock.expectLastCall;
import static org.easymock.EasyMock.getEasyMockProperty;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(Parameterized.class)
public class GameTests {
    private IWateringHole wateringHole;
    private IDeck<ICard> drawPile;
    private IDeck<ICard> discardPile;
    private int numPlayers;
    private int playerIndex;

    public GameTests(int numPlayers, int playerIndex) {
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

    @Test
    public void testCreateNewGame() throws IllegalNumberOfPlayers,
            IllegalCardDirectionException, NullGameObjectException {
        Game g = new Game(generateNumPlayers(this.numPlayers), wateringHole, this.drawPile, this.discardPile);
        assertEquals(this.numPlayers, g.getPlayerObjects().size());
    }

    @Test(expected = NullGameObjectException.class)
    public void testGameNullPlayers() throws IllegalNumberOfPlayers, NullGameObjectException {
        new Game(null, this.wateringHole, this.drawPile, this.discardPile);
    }

    @Test(expected = NullGameObjectException.class)
    public void testGameNullWH() throws IllegalNumberOfPlayers, NullGameObjectException {
        new Game(generateNumPlayers(3), null, this.drawPile, this.discardPile);
    }

    @Test(expected = NullGameObjectException.class)
    public void testGameNullDrawDeck() throws IllegalNumberOfPlayers, NullGameObjectException {
        new Game(generateNumPlayers(3), this.wateringHole, null, this.discardPile);
    }

    @Test(expected = NullGameObjectException.class)
    public void testGameDiscardDeck() throws IllegalNumberOfPlayers, NullGameObjectException {
        new Game(generateNumPlayers(3), this.wateringHole, this.drawPile, null);
    }

    @Test(expected = IllegalNumberOfPlayers.class)
    public void testValidNumberOfPlayersSub3() throws IllegalNumberOfPlayers,
            IllegalCardDirectionException, NullGameObjectException {
        new Game(generateNumPlayers(0), wateringHole, this.drawPile, this.discardPile);
    }

    @Test(expected = IllegalNumberOfPlayers.class)
    public void testValidNumberOfPlayersGre6() throws IllegalNumberOfPlayers,
            IllegalCardDirectionException, NullGameObjectException {
        new Game(generateNumPlayers(7), wateringHole, this.drawPile, this.discardPile);
    }

    @Test
    public void getPlayers() throws IllegalNumberOfPlayers,
            IllegalCardDirectionException, NullGameObjectException {
        Game g = new Game(generateNumPlayers(this.numPlayers), wateringHole, this.drawPile, this.discardPile);
        assertEquals(this.numPlayers, g.getPlayerObjects().size());
    }

    @Test
    public void getDrawPile() throws IllegalNumberOfPlayers,
            IllegalCardDirectionException, NullGameObjectException {
        Game g = new Game(generateNumPlayers(this.numPlayers), wateringHole, this.drawPile, this.discardPile);
        assertTrue(g.getDrawPile() == this.drawPile);
        assertEquals(0, g.getDrawPile().getSize());
    }

    @Test
    public void getDiscardPile1() throws IllegalNumberOfPlayers,
            IllegalCardDirectionException, NullGameObjectException {
        Game g = new Game(generateNumPlayers(this.numPlayers), wateringHole, this.drawPile, this.discardPile);
        assertTrue(this.discardPile == g.getDiscardPile());
        assertEquals(0, g.getDiscardPile().getSize());
    }

    @Test
    public void getDiscardPile2() throws IllegalNumberOfPlayers,
            IllegalCardDirectionException, NullGameObjectException {
        IDeck<ICard> discardPile = new Deck<>();
        Game g = new Game(generateNumPlayers(this.numPlayers), wateringHole, this.drawPile, discardPile);
        g.getDiscardPile().discard(new TestCard());
        assertEquals(1, g.getDiscardPile().getSize());
    }

    @Test
    public void testGetCurrentRound() throws IllegalNumberOfPlayers,
            IllegalCardDirectionException, NullGameObjectException {
        Game g = new Game(generateNumPlayers(this.numPlayers), wateringHole, this.drawPile, this.discardPile);
        assertEquals(1, g.getRound());
    }

    @Test
    public void testIncreaseRound() throws IllegalNumberOfPlayers,
            IllegalCardDirectionException, NullGameObjectException {
        Game g = new Game(generateNumPlayers(this.numPlayers), wateringHole, this.drawPile, this.discardPile);
        g.increaseRound();
        assertEquals(2, g.getRound());
    }

    @Test
    public void testIncreaseMultiRound() throws IllegalNumberOfPlayers,
            IllegalCardDirectionException, NullGameObjectException {
        Game g = new Game(generateNumPlayers(this.numPlayers), wateringHole, this.drawPile, this.discardPile);
        for (int i = 0; i < 4; i++) {
            g.increaseRound();
            assertEquals(i + 2, g.getRound());
        }
    }

    @Test
    public void testStartPhase() throws IllegalNumberOfPlayers,
            IllegalCardDirectionException, DeckEmptyException, InvalidPlayerSelectException, NullGameObjectException {
        IPhases fakePhaseOne = EasyMock.niceMock(PhaseOne.class);
        fakePhaseOne.execute();
        EasyMock.replay(fakePhaseOne);
        Game g = new Game(generateNumPlayers(4), wateringHole, this.drawPile, this.discardPile);
        g.setPhase(fakePhaseOne);
        g.startGame();
        EasyMock.verify(fakePhaseOne);
    }

    @Test
    public void testGetTurn1() throws IllegalNumberOfPlayers, IllegalCardDirectionException, NullGameObjectException {
        Game g = new Game(generateNumPlayers(this.numPlayers), wateringHole, this.drawPile, this.discardPile);
        assertTrue(g.getTurn() == 1);
    }

    @Test
    public void testGetTurn3() throws IllegalNumberOfPlayers, IllegalCardDirectionException, NullGameObjectException {
        Game g = new Game(generateNumPlayers(this.numPlayers), wateringHole, this.drawPile, this.discardPile);
        g.nextTurn();
        g.nextTurn();
        assertTrue(g.getTurn() == 3);
    }

    @Test
    public void testGetTurn6() throws IllegalNumberOfPlayers, IllegalCardDirectionException, NullGameObjectException {
        Game g = new Game(generateNumPlayers(this.numPlayers), wateringHole, this.drawPile, this.discardPile);
        for (int j = 0; j < this.numPlayers; j++) {
            g.nextTurn();
        }
        assertTrue(g.getTurn() == 1);
    }

    @Test
    public void testGetWateringHole() throws IllegalNumberOfPlayers, IllegalCardDirectionException, NullGameObjectException {
        Game g = new Game(generateNumPlayers(4), wateringHole, this.drawPile, this.discardPile);
        assertTrue(g.getWateringHole() == wateringHole);
    }

    @Test
    public void testGetFoodBankCount() throws IllegalNumberOfPlayers, IllegalCardDirectionException, NullGameObjectException {
        Game g = new Game(generateNumPlayers(4), this.wateringHole, this.drawPile, this.discardPile);
        assertEquals(240, g.getFoodBankCount());
    }

    @Test
    public void testDecrementFoodBank() throws IllegalNumberOfPlayers, IllegalCardDirectionException, FoodBankEmptyException, NullGameObjectException {
        Game g = new Game(generateNumPlayers(4), this.wateringHole, this.drawPile, this.discardPile);
        for (int i = 1; i < 50; i++) {
            g.decrementFoodBank();
            assertEquals(240 - i, g.getFoodBankCount());
        }
    }

    @Test
    public void testDecrementFoodBank0() throws IllegalNumberOfPlayers, FoodBankEmptyException, NullGameObjectException {
        Game g = new Game(generateNumPlayers(4), this.wateringHole, this.drawPile, this.discardPile);
        g.decrementFoodBank(0);
        assertEquals(240, g.getFoodBankCount());
    }

    @Test
    public void testDecrementFoodBank240() throws IllegalNumberOfPlayers, FoodBankEmptyException, NullGameObjectException {
        Game g = new Game(generateNumPlayers(4), this.wateringHole, this.drawPile, this.discardPile);
        g.decrementFoodBank(240);
        assertEquals(0, g.getFoodBankCount());
    }

    @Test
    public void testDecrementNFoodBank() throws IllegalNumberOfPlayers, IllegalCardDirectionException, FoodBankEmptyException, NullGameObjectException {
        Game g = new Game(generateNumPlayers(4), this.wateringHole, this.drawPile, this.discardPile);
        for (int i = 1; i < 10; i++) {
            g.decrementFoodBank(5);
            assertEquals(240 - (i * 5), g.getFoodBankCount());
        }
    }

    @Test
    public void testMoveFoodFromBankToHole() throws IllegalNumberOfPlayers, IllegalCardDirectionException, FoodBankEmptyException, NullGameObjectException {
        IWateringHole wateringHole = new WateringHole();
        Game g = new Game(generateNumPlayers(4), wateringHole, this.drawPile, this.discardPile);
        g.moveFoodFromBankToHole(1);
        assertEquals(239, g.getFoodBankCount());
        assertEquals(1, g.getWateringHole().getFoodCount());
    }

    @Test
    public void testMoveNFoodFromBankToHole() throws IllegalNumberOfPlayers, IllegalCardDirectionException, FoodBankEmptyException, NullGameObjectException {
        IWateringHole wateringHole = new WateringHole();
        Game g = new Game(generateNumPlayers(4), wateringHole, this.drawPile, this.discardPile);
        for (int i = 1; i < 50; i++) {
            g.moveFoodFromBankToHole(1);
            assertEquals(240 - i, g.getFoodBankCount());
            assertEquals(i, g.getWateringHole().getFoodCount());
        }
    }

    @Test
    public void testMoveNFoodFromBankToHole2() throws IllegalNumberOfPlayers, IllegalCardDirectionException, FoodBankEmptyException, NullGameObjectException {
        IWateringHole wateringHole = new WateringHole();
        Game g = new Game(generateNumPlayers(4), wateringHole, this.drawPile, this.discardPile);
        for (int i = 1; i < 10; i++) {
            g.moveFoodFromBankToHole(5);
            assertEquals(240 - (i * 5), g.getFoodBankCount());
            assertEquals(i * 5, g.getWateringHole().getFoodCount());
        }
    }

    @Test(expected = FoodBankEmptyException.class)
    public void testFoodBankOneEmpty() throws IllegalNumberOfPlayers, IllegalCardDirectionException, FoodBankEmptyException, NullGameObjectException {
        IWateringHole wateringHole = new WateringHole();
        Game g = new Game(generateNumPlayers(4), wateringHole, this.drawPile, this.discardPile);
        for (int i = 0; i < 241; i++) {
            g.decrementFoodBank();
        }
    }

    @Test(expected = FoodBankEmptyException.class)
    public void testFoodBankIEmpty1() throws IllegalNumberOfPlayers, IllegalCardDirectionException, FoodBankEmptyException, NullGameObjectException {
        IWateringHole wateringHole = new WateringHole();
        Game g = new Game(generateNumPlayers(4), wateringHole, this.drawPile, this.discardPile);
        g.decrementFoodBank(241);
    }

    @Test(expected = FoodBankEmptyException.class)
    public void testFoodBankIEmpty2() throws IllegalNumberOfPlayers, IllegalCardDirectionException, FoodBankEmptyException, NullGameObjectException {
        IWateringHole wateringHole = new WateringHole();
        Game g = new Game(generateNumPlayers(4), wateringHole, this.drawPile, this.discardPile);
        g.decrementFoodBank(-1);
    }

    @Test
    public void testDealToPlayerValid() throws IllegalNumberOfPlayers, IllegalCardDirectionException, DeckEmptyException, InvalidPlayerSelectException, NullGameObjectException {
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
    public void testDealToPlayerResults() throws IllegalNumberOfPlayers, IllegalCardDirectionException, DeckEmptyException, InvalidPlayerSelectException, NullGameObjectException {
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
    public void testRemovePlayerValid() throws IllegalNumberOfPlayers, IllegalCardDirectionException, NoSuchFieldException, IllegalAccessException, InvalidPlayerSelectException, IllegalCardDiscardException, NullGameObjectException, IllegalCardRemovalException {
        Game g = new Game(generateNumPlayers(4), this.wateringHole, this.drawPile, this.discardPile);
        IPlayer fakePlayer = EasyMock.niceMock(Player.class);
        ICard fakeCard = EasyMock.niceMock(Card.class);

        Field playerList = g.getClass().getDeclaredField("players");
        playerList.setAccessible(true);
        ArrayList<IPlayer> fakePlayerList = new ArrayList<>();
        fakePlayerList.add(fakePlayer);
        playerList.set(g, fakePlayerList);
        EasyMock.expect(fakePlayer.getCards()).andReturn(new ArrayList<>(Arrays.asList(fakeCard)));
        this.discardPile.discard(fakeCard);

        EasyMock.replay(this.discardPile);
        EasyMock.replay(fakePlayer);
        EasyMock.replay(fakeCard);

        g.discardFromPlayer(0, fakeCard);

        EasyMock.verify(this.discardPile);
        EasyMock.verify(fakePlayer);
        EasyMock.verify(fakeCard);
    }

    @Test(expected = InvalidPlayerSelectException.class)
    public void testDiscardFromPlayerInvalidPlayer() throws IllegalNumberOfPlayers, IllegalCardDirectionException,
            DeckEmptyException, InvalidPlayerSelectException, IllegalCardDiscardException, NullGameObjectException, IllegalCardRemovalException {
        ArrayList<IPlayer> playerList = generateNumPlayers(this.numPlayers);
        Game g = new Game(playerList, this.wateringHole, this.drawPile, discardPile);
        g.discardFromPlayer(this.numPlayers, new TestCard());
    }

    @Test(expected = IllegalCardDiscardException.class)
    public void testDiscardFromPlayerInvalidCard() throws IllegalNumberOfPlayers, IllegalCardDirectionException,
            InvalidPlayerSelectException, DeckEmptyException, IllegalCardDiscardException,
            InvalidDiscardToWateringHoleException, InvalidAddToWateringHoleException, NullGameObjectException, IllegalCardRemovalException {
        Deck<ICard> drawPile = new Deck<>();
        for (int i = 0; i < this.numPlayers; i++) {
            ICard card = new TestCard();
            drawPile.discard(card);
        }
        ArrayList<IPlayer> playerList = generateNumRealPlayers(this.numPlayers);
        Game g = new Game(playerList, this.wateringHole, drawPile, this.discardPile);
        for (int i = 0; i < this.numPlayers; i++) {
            g.dealToPlayer(i);
        }
        g.discardFromPlayer(this.playerIndex, new TestCard());
    }


    @Test
    public void testRemoveFromPlayerResults() throws IllegalNumberOfPlayers, IllegalCardDirectionException, InvalidPlayerSelectException, IllegalCardDiscardException, NullGameObjectException, IllegalCardRemovalException {
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
        g.discardFromPlayer(0, card);
        assertTrue(discardPile.contains(card));

    }

    @Test(expected = InvalidPlayerSelectException.class)
    public void testInvalidPlayerSelect1() throws IllegalNumberOfPlayers, IllegalCardDirectionException, DeckEmptyException, InvalidPlayerSelectException, NullGameObjectException {
        Deck<ICard> discardPile = new Deck<>();
        ICard card = new TestCard();
        assertTrue(!discardPile.contains(card));
        Player player = new Player(new TestSpecies());
        ArrayList<IPlayer> playerList = new ArrayList<>();
        for (int i = 0; i < this.numPlayers; i++) {
            playerList.add(player);
        }
        Game g = new Game(playerList, this.wateringHole, this.drawPile, discardPile);
        g.dealToPlayer(this.numPlayers);
    }

    @Test(expected = InvalidPlayerSelectException.class)
    public void testInvalidPlayerSelect2() throws IllegalNumberOfPlayers, IllegalCardDirectionException,
            DeckEmptyException, InvalidPlayerSelectException, NullGameObjectException {
        Deck<ICard> discardPile = new Deck<>();
        ICard card = new TestCard();
        assertTrue(!discardPile.contains(card));
        ArrayList<IPlayer> playerList = generateNumRealPlayers(this.numPlayers);
        Game g = new Game(playerList, this.wateringHole, this.drawPile, discardPile);
        g.dealToPlayer(-1);
    }

    @Test
    public void testGetPhase() throws IllegalNumberOfPlayers, IllegalCardDirectionException, NullGameObjectException {
        Deck<ICard> discardPile = new Deck<>();
        ICard card = new TestCard();
        assertTrue(!discardPile.contains(card));
        ArrayList<IPlayer> playerList = generateNumRealPlayers(this.numPlayers);
        Game g = new Game(playerList, this.wateringHole, this.drawPile, discardPile);
        assertEquals(PhaseOne.class, g.getPhase().getClass());
    }

    @Test
    public void testSetPhase() throws IllegalNumberOfPlayers, IllegalCardDirectionException, NullGameObjectException {
        Deck<ICard> discardPile = new Deck<>();
        ICard card = new TestCard();
        assertTrue(!discardPile.contains(card));
        ArrayList<IPlayer> playerList = generateNumRealPlayers(this.numPlayers);
        Game g = new Game(playerList, this.wateringHole, this.drawPile, discardPile);
        g.setPhase(new PhaseTwo(g));
        assertEquals(PhaseTwo.class, g.getPhase().getClass());
    }

    @Test(expected = NullGameObjectException.class)
    public void testSetNullPhase() throws IllegalNumberOfPlayers, NullGameObjectException {
        Game g = new Game(generateNumPlayers(3), this.wateringHole, this.drawPile, this.discardPile);
        g.setPhase(null);
    }

    @Test
    public void testDiscardToWateringHole() throws IllegalNumberOfPlayers, IllegalCardDirectionException,
            InvalidPlayerSelectException, DeckEmptyException, InvalidDiscardToWateringHoleException,
            InvalidAddToWateringHoleException, IllegalCardDiscardException, IllegalCardRemovalException, NullGameObjectException {
        Deck<ICard> drawPile = new Deck<>();
        WateringHole wateringHole = new WateringHole();
        ICard card = new TestCard();
        drawPile.discard(card);
        ArrayList<IPlayer> playerList = generateNumRealPlayers(this.numPlayers);
        Game g = new Game(playerList, wateringHole, drawPile, this.discardPile);
        g.dealToPlayer(this.playerIndex);
        assertTrue(g.getPlayerObjects().get(this.playerIndex).getCards().contains(card));
        assertTrue(!g.getWateringHole().getCards().contains(card));
        g.discardToWateringHole(this.playerIndex, g.getPlayerObjects().get(this.playerIndex).getCards().get(0));
        assertTrue(!g.getPlayerObjects().get(this.playerIndex).getCards().contains(card));
        assertTrue(g.getWateringHole().getCards().contains(card));
    }

    @Test(expected = InvalidPlayerSelectException.class)
    public void testDiscardToWateringHoleInvalidPlayer() throws IllegalNumberOfPlayers, IllegalCardDirectionException,
            InvalidPlayerSelectException, IllegalCardDiscardException, InvalidDiscardToWateringHoleException,
            InvalidAddToWateringHoleException, IllegalCardRemovalException, NullGameObjectException {
        WateringHole wateringHole = new WateringHole();
        ArrayList<IPlayer> playerList = generateNumPlayers(this.numPlayers);
        ICard fakeCard = EasyMock.niceMock(Card.class);
        Game g = new Game(playerList, wateringHole, this.drawPile, this.discardPile);
        g.discardToWateringHole(this.numPlayers, fakeCard);
    }

    @Test(expected = IllegalCardDiscardException.class)
    public void testDiscardToWateringHoleInvalidCard() throws IllegalNumberOfPlayers, IllegalCardDirectionException,
            InvalidPlayerSelectException, DeckEmptyException, IllegalCardDiscardException,
            InvalidDiscardToWateringHoleException, InvalidAddToWateringHoleException, IllegalCardRemovalException, NullGameObjectException {
        Deck<ICard> drawPile = new Deck<>();
        for (int i = 0; i < this.numPlayers; i++) {
            ICard card = new TestCard();
            drawPile.discard(card);
        }
        ArrayList<IPlayer> playerList = generateNumRealPlayers(this.numPlayers);
        WateringHole wateringHole = new WateringHole();
        Game g = new Game(playerList, wateringHole, drawPile, this.discardPile);
        for (int i = 0; i < this.numPlayers; i++) {
            g.dealToPlayer(i);
        }
        g.discardToWateringHole(this.playerIndex, new TestCard());
    }

    /**
     * BVA - Can only add card to the watering hole equal to the number of players
     */
    @Test(expected = InvalidDiscardToWateringHoleException.class)
    public void testDiscardExtraToWateringHole() throws IllegalNumberOfPlayers, IllegalCardDirectionException,
            InvalidPlayerSelectException, DeckEmptyException, InvalidDiscardToWateringHoleException,
            InvalidAddToWateringHoleException, IllegalCardDiscardException, IllegalCardRemovalException, NullGameObjectException {
        Deck<ICard> drawPile = new Deck<>();
        WateringHole wateringHole = new WateringHole();
        for (int i = 0; i < this.numPlayers + 1; i++) {
            ICard card = new TestCard();
            drawPile.discard(card);
        }
        ArrayList<IPlayer> playerList = generateNumRealPlayers(this.numPlayers);
        Game g = new Game(playerList, wateringHole, drawPile, this.discardPile);
        for (int k = 0; k < this.numPlayers + 1; k++) {
            g.dealToPlayer(k % this.numPlayers);
            g.discardToWateringHole(k % this.numPlayers, g.getPlayerObjects().get(k % this.numPlayers).getCards().get(0));
        }
    }

    @Test
    public void testDiscardForLeftSpecies() throws IllegalNumberOfPlayers, IllegalCardDirectionException,
            InvalidPlayerSelectException, IllegalCardDiscardException, NullGameObjectException, IllegalCardRemovalException {
        ArrayList<IPlayer> players = generateNumPlayers(this.numPlayers);
        Game g = new Game(players, this.wateringHole, this.drawPile, this.discardPile);
        ISpecies fakeSpecies = EasyMock.niceMock(Species.class);
        ICard fakeCard = EasyMock.niceMock(Card.class);
        EasyMock.expect(players.get(this.playerIndex).getCards())
                .andReturn(new ArrayList<>(Arrays.asList(fakeCard)));
        EasyMock.expect(players.get(this.playerIndex).getCards())
                .andReturn(new ArrayList<>(Arrays.asList(fakeCard)));
        players.get(this.playerIndex).addSpeciesLeft(fakeSpecies);
        this.discardPile.discard(fakeCard);
        EasyMock.replay(players.get(this.playerIndex), this.discardPile, fakeSpecies, fakeCard);
        g.discardForLeftSpecies(this.playerIndex, fakeCard, fakeSpecies);
        EasyMock.verify(players.get(this.playerIndex), this.discardPile, fakeSpecies, fakeCard);
    }

    @Test(expected = NullGameObjectException.class)
    public void testDiscardForLeftNullCard() throws InvalidPlayerSelectException, NullGameObjectException,
            IllegalCardDiscardException, IllegalNumberOfPlayers, IllegalCardRemovalException {
        Game g = new Game(generateNumPlayers(3), this.wateringHole, this.drawPile, this.discardPile);
        g.discardForLeftSpecies(0, null, EasyMock.niceMock(Species.class));
    }

    @Test(expected = NullGameObjectException.class)
    public void testDiscardForLeftNullSpecies() throws InvalidPlayerSelectException, NullGameObjectException,
            IllegalCardDiscardException, IllegalNumberOfPlayers, IllegalCardRemovalException {
        Game g = new Game(generateNumPlayers(3), this.wateringHole, this.drawPile, this.discardPile);
        g.discardForLeftSpecies(0, EasyMock.niceMock(Card.class), null);
    }

    @Test(expected = InvalidPlayerSelectException.class)
    public void testDiscardForLeftSpeciesInvalidPlayer() throws IllegalNumberOfPlayers, IllegalCardDirectionException,
            InvalidPlayerSelectException, IllegalCardDiscardException, NullGameObjectException, IllegalCardRemovalException {
        ArrayList<IPlayer> playerList = generateNumPlayers(this.numPlayers);
        ISpecies fakeSpecies = EasyMock.niceMock(Species.class);
        ICard fakeCard = EasyMock.niceMock(Card.class);
        Game g = new Game(playerList, this.wateringHole, this.drawPile, this.discardPile);
        g.discardForLeftSpecies(this.numPlayers, fakeCard, fakeSpecies);
    }

    @Test(expected = IllegalCardDiscardException.class)
    public void testDiscardForLeftSpeciesInvalidCard() throws IllegalNumberOfPlayers, IllegalCardDirectionException,
            InvalidPlayerSelectException, DeckEmptyException, IllegalCardDiscardException, NullGameObjectException, IllegalCardRemovalException {
        Deck<ICard> drawPile = new Deck<>();
        for (int i = 0; i < this.numPlayers; i++) {
            ICard card = new TestCard();
            drawPile.discard(card);
        }
        ArrayList<IPlayer> playerList = generateNumRealPlayers(this.numPlayers);
        Game g = new Game(playerList, this.wateringHole, drawPile, this.discardPile);
        for (int i = 0; i < this.numPlayers; i++) {
            g.dealToPlayer(i);
        }
        ISpecies fakeSpecies = EasyMock.niceMock(Species.class);
        g.discardForLeftSpecies(this.playerIndex, new TestCard(), fakeSpecies);
    }

    @Test
    public void testDiscardForRightSpecies() throws IllegalNumberOfPlayers, IllegalCardDirectionException,
            InvalidPlayerSelectException, IllegalCardDiscardException, NullGameObjectException, IllegalCardRemovalException {
        ArrayList<IPlayer> players = generateNumPlayers(this.numPlayers);
        Game g = new Game(players, this.wateringHole, this.drawPile, this.discardPile);
        ISpecies fakeSpecies = EasyMock.niceMock(Species.class);
        ICard fakeCard = EasyMock.niceMock(Card.class);
        EasyMock.expect(players.get(this.playerIndex).getCards()).andReturn(new ArrayList<>(Arrays.asList(fakeCard)));
        EasyMock.expect(players.get(this.playerIndex).getCards()).andReturn(new ArrayList<>(Arrays.asList(fakeCard)));
        players.get(this.playerIndex).addSpeciesRight(fakeSpecies);
        this.discardPile.discard(fakeCard);
        EasyMock.replay(players.get(this.playerIndex), this.discardPile, fakeSpecies, fakeCard);
        g.discardForRightSpecies(this.playerIndex, fakeCard, fakeSpecies);
        EasyMock.verify(players.get(this.playerIndex), this.discardPile, fakeSpecies, fakeCard);
    }

    @Test(expected = NullGameObjectException.class)
    public void testDiscardForRightNullCard() throws InvalidPlayerSelectException, NullGameObjectException,
            IllegalCardDiscardException, IllegalNumberOfPlayers, IllegalCardRemovalException {
        Game g = new Game(generateNumPlayers(3), this.wateringHole, this.drawPile, this.discardPile);
        g.discardForRightSpecies(0, null, EasyMock.niceMock(Species.class));
    }

    @Test(expected = NullGameObjectException.class)
    public void testDiscardForRightNullSpecies() throws InvalidPlayerSelectException, NullGameObjectException,
            IllegalCardDiscardException, IllegalNumberOfPlayers, IllegalCardRemovalException {
        Game g = new Game(generateNumPlayers(3), this.wateringHole, this.drawPile, this.discardPile);
        g.discardForRightSpecies(0, EasyMock.niceMock(Card.class), null);
    }

    @Test(expected = InvalidPlayerSelectException.class)
    public void testDiscardForRightSpeciesInvalidPlayer() throws IllegalNumberOfPlayers, IllegalCardDirectionException,
            InvalidPlayerSelectException, IllegalCardDiscardException, NullGameObjectException, IllegalCardRemovalException {
        ArrayList<IPlayer> playerList = generateNumPlayers(this.numPlayers);
        ISpecies fakeSpecies = EasyMock.niceMock(Species.class);
        ICard fakeCard = EasyMock.niceMock(Card.class);
        Game g = new Game(playerList, this.wateringHole, this.drawPile, this.discardPile);
        g.discardForRightSpecies(this.numPlayers, fakeCard, fakeSpecies);
    }

    @Test(expected = IllegalCardDiscardException.class)
    public void testDiscardForRightSpeciesInvalidCard() throws IllegalNumberOfPlayers, IllegalCardDirectionException,
            InvalidPlayerSelectException, DeckEmptyException, IllegalCardDiscardException, NullGameObjectException, IllegalCardRemovalException {
        Deck<ICard> drawPile = new Deck<>();
        for (int i = 0; i < this.numPlayers; i++) {
            ICard card = new TestCard();
            drawPile.discard(card);
        }
        ArrayList<IPlayer> playerList = generateNumRealPlayers(this.numPlayers);
        Game g = new Game(playerList, this.wateringHole, drawPile, this.discardPile);
        for (int i = 0; i < this.numPlayers; i++) {
            g.dealToPlayer(i);
        }
        ISpecies fakeSpecies = EasyMock.niceMock(Species.class);
        g.discardForRightSpecies(this.playerIndex, new TestCard(), fakeSpecies);
    }

    @Test
    public void testDiscardToIncreasePopulation() throws IllegalNumberOfPlayers, IllegalCardDirectionException,
            SpeciesPopulationException, DeckEmptyException, IllegalCardDiscardException,
            InvalidPlayerSelectException, IllegalSpeciesIndexException, IllegalCardRemovalException, NullGameObjectException {
        Deck<ICard> drawPile = new Deck<>();
        for (int i = 0; i < this.numPlayers; i++) {
            ICard card = new TestCard();
            drawPile.discard(card);
        }
        ArrayList<IPlayer> playerList = generateNumRealPlayers(this.numPlayers);
        Game g = new Game(playerList, this.wateringHole, drawPile, this.discardPile);
        for (int i = 0; i < this.numPlayers; i++) {
            g.dealToPlayer(i);
        }
        g.increasePopulation(this.playerIndex, 0, g.getPlayerObjects().get(this.playerIndex).getCards().get(0));

    }

    @Test(expected = IllegalCardDiscardException.class)
    public void testDiscardToIncreasePopulation2() throws IllegalNumberOfPlayers, IllegalCardDirectionException,
            SpeciesPopulationException, DeckEmptyException, IllegalCardDiscardException,
            InvalidPlayerSelectException, IllegalSpeciesIndexException, IllegalCardRemovalException, NullGameObjectException {
        Deck<ICard> drawPile = new Deck<>();
        for (int i = 0; i < this.numPlayers; i++) {
            ICard card = new TestCard();
            drawPile.discard(card);
        }
        ArrayList<IPlayer> playerList = generateNumRealPlayers(this.numPlayers);
        Game g = new Game(playerList, this.wateringHole, drawPile, this.discardPile);
        for (int i = 0; i < this.numPlayers; i++) {
            g.dealToPlayer(i);
        }
        g.increasePopulation(this.playerIndex, 0, new TestCard());
    }

    @Test(expected = InvalidPlayerSelectException.class)
    public void testDiscardToIncreasePopulation3() throws IllegalNumberOfPlayers, IllegalCardDirectionException,
            SpeciesPopulationException, DeckEmptyException, IllegalCardDiscardException,
            InvalidPlayerSelectException, IllegalSpeciesIndexException, IllegalCardRemovalException, NullGameObjectException {
        ArrayList<IPlayer> playerList = generateNumPlayers(this.numPlayers);
        Game g = new Game(playerList, this.wateringHole, this.drawPile, this.discardPile);
        g.increasePopulation(this.numPlayers, 0, new TestCard());
    }

    @Test(expected = IllegalSpeciesIndexException.class)
    public void testDiscardToIncreasePopulation4() throws IllegalNumberOfPlayers, IllegalCardDirectionException,
            SpeciesPopulationException, DeckEmptyException, IllegalCardDiscardException,
            InvalidPlayerSelectException, IllegalSpeciesIndexException, IllegalCardRemovalException, NullGameObjectException {
        ArrayList<IPlayer> playerList = generateNumRealPlayers(this.numPlayers);
        Deck<ICard> drawPile = new Deck<>();
        for (int i = 0; i < this.numPlayers; i++) {
            ICard card = new TestCard();
            drawPile.discard(card);
        }
        Game g = new Game(playerList, this.wateringHole, drawPile, this.discardPile);
        g.dealToPlayer(this.playerIndex);
        g.increasePopulation(this.playerIndex, 1, playerList.get(this.playerIndex).getCards().get(0));
    }


    @Test
    public void testDiscardToIncreaseBodySize() throws IllegalNumberOfPlayers, IllegalCardDirectionException,
            DeckEmptyException, IllegalCardDiscardException, InvalidPlayerSelectException,
            IllegalSpeciesIndexException, SpeciesBodySizeException, NullGameObjectException, IllegalCardRemovalException {
        Deck<ICard> drawPile = new Deck<>();
        for (int i = 0; i < this.numPlayers; i++) {
            ICard card = new TestCard();
            drawPile.discard(card);
        }
        ArrayList<IPlayer> playerList = generateNumRealPlayers(this.numPlayers);
        Game g = new Game(playerList, this.wateringHole, drawPile, this.discardPile);
        for (int i = 0; i < this.numPlayers; i++) {
            g.dealToPlayer(i);
        }
        g.increaseBodySize(this.playerIndex, 0, playerList.get(this.playerIndex).getCards().get(0));
    }

    @Test(expected = InvalidPlayerSelectException.class)
    public void testDiscardToIncreaseBodySize2() throws IllegalNumberOfPlayers, IllegalCardDirectionException,
            DeckEmptyException, IllegalCardDiscardException, InvalidPlayerSelectException,
            IllegalSpeciesIndexException, SpeciesBodySizeException, NullGameObjectException, IllegalCardRemovalException {
        ArrayList<IPlayer> playerList = generateNumPlayers(this.numPlayers);
        Game g = new Game(playerList, this.wateringHole, this.drawPile, this.discardPile);
        g.increaseBodySize(this.numPlayers, 0, new TestCard());
    }

    @Test(expected = IllegalCardDiscardException.class)
    public void testDiscardToIncreaseBodySize3() throws IllegalNumberOfPlayers, IllegalCardDirectionException,
            DeckEmptyException, IllegalCardDiscardException, InvalidPlayerSelectException,
            IllegalSpeciesIndexException, SpeciesBodySizeException, NullGameObjectException, IllegalCardRemovalException {
        Deck<ICard> drawPile = new Deck<>();
        for (int i = 0; i < this.numPlayers; i++) {
            ICard card = new TestCard();
            drawPile.discard(card);
        }
        ArrayList<IPlayer> playerList = generateNumRealPlayers(this.numPlayers);
        Game g = new Game(playerList, this.wateringHole, drawPile, this.discardPile);
        for (int i = 0; i < this.numPlayers; i++) {
            g.dealToPlayer(i);
        }
        g.increaseBodySize(this.playerIndex, 0, new TestCard());
    }

    @Test(expected = IllegalSpeciesIndexException.class)
    public void testDiscardToIncreaseBodySize4() throws IllegalNumberOfPlayers, IllegalCardDirectionException,
            DeckEmptyException, IllegalCardDiscardException, InvalidPlayerSelectException,
            IllegalSpeciesIndexException, SpeciesBodySizeException, NullGameObjectException, IllegalCardRemovalException {
        ArrayList<IPlayer> playerList = generateNumRealPlayers(this.numPlayers);
        Deck<ICard> drawPile = new Deck<>();
        for (int i = 0; i < this.numPlayers; i++) {
            ICard card = new TestCard();
            drawPile.discard(card);
        }
        Game g = new Game(playerList, this.wateringHole, drawPile, this.discardPile);
        g.dealToPlayer(this.playerIndex);
        g.increaseBodySize(this.playerIndex, 1, playerList.get(this.playerIndex).getCards().get(0));
    }

    @Test
    public void testAddTrait() throws IllegalNumberOfPlayers, SpeciesNumberTraitsException, SpeciesDuplicateTraitException, InvalidPlayerSelectException, IllegalSpeciesIndexException, NullGameObjectException, IllegalCardRemovalException {
        ArrayList<IPlayer> players = generateNumPlayers(this.numPlayers);
        Game g = new Game(players, this.wateringHole, this.drawPile, this.discardPile);
        Card fakeCard = EasyMock.niceMock(Card.class);
        Species fakeSpecies = EasyMock.niceMock(Species.class);
        players.get(this.playerIndex).removeCardFromHand(fakeCard);
        EasyMock.expect(players.get(this.playerIndex).getSpecies()).andReturn(new ArrayList<>(Arrays.asList
                (fakeSpecies)));
        EasyMock.expect(players.get(this.playerIndex).getSpecies()).andReturn(new ArrayList<>(Arrays.asList
                (fakeSpecies)));
        fakeSpecies.addTrait(fakeCard);
        EasyMock.replay(players.get(this.playerIndex), this.wateringHole, this.drawPile, this.discardPile, fakeCard,
                fakeSpecies);
        g.addTraitToSpecies(this.playerIndex, 0, fakeCard);
        EasyMock.verify(players.get(this.playerIndex), this.wateringHole, this.drawPile, this.discardPile, fakeCard,
                fakeSpecies);
    }

    @Test(expected = IllegalCardRemovalException.class)
    public void testAddTraitFalse() throws IllegalNumberOfPlayers, SpeciesNumberTraitsException, SpeciesDuplicateTraitException, InvalidPlayerSelectException, IllegalSpeciesIndexException, NullGameObjectException, IllegalCardRemovalException {
        ArrayList<IPlayer> players = generateNumRealPlayers(this.numPlayers);
        Game g = new Game(players, this.wateringHole, this.drawPile, this.discardPile);
        Card fakeCard = EasyMock.niceMock(Card.class);
        g.addTraitToSpecies(0, 0, fakeCard);
    }

    @Test(expected = InvalidPlayerSelectException.class)
    public void testAddTraitLowPlayer() throws SpeciesNumberTraitsException, SpeciesDuplicateTraitException, IllegalNumberOfPlayers, InvalidPlayerSelectException, IllegalSpeciesIndexException, NullGameObjectException, IllegalCardRemovalException {
        ArrayList<IPlayer> players = generateNumPlayers(this.numPlayers);
        Game g = new Game(players, this.wateringHole, this.drawPile, this.discardPile);
        Card fakeCard = EasyMock.niceMock(Card.class);
        g.addTraitToSpecies(-1, 0, fakeCard);
    }

    @Test(expected = InvalidPlayerSelectException.class)
    public void testAddTraitHighPlayer() throws SpeciesNumberTraitsException, SpeciesDuplicateTraitException,
            IllegalNumberOfPlayers, InvalidPlayerSelectException, IllegalSpeciesIndexException, NullGameObjectException, IllegalCardRemovalException {
        ArrayList<IPlayer> players = generateNumPlayers(this.numPlayers);
        Game g = new Game(players, this.wateringHole, this.drawPile, this.discardPile);
        Card fakeCard = EasyMock.niceMock(Card.class);
        g.addTraitToSpecies(this.numPlayers, 0, fakeCard);
    }

    @Test(expected = IllegalSpeciesIndexException.class)
    public void testAddTraitLowSpecies() throws IllegalNumberOfPlayers, SpeciesNumberTraitsException,
            SpeciesDuplicateTraitException, InvalidPlayerSelectException, IllegalSpeciesIndexException, NullGameObjectException, IllegalCardRemovalException {
        ArrayList<IPlayer> players = generateNumPlayers(this.numPlayers);
        Game g = new Game(players, this.wateringHole, this.drawPile, this.discardPile);
        Card fakeCard = EasyMock.niceMock(Card.class);
        Species fakeSpecies = EasyMock.niceMock(Species.class);
        players.get(this.playerIndex).removeCardFromHand(fakeCard);
        EasyMock.expect(players.get(this.playerIndex).getSpecies()).andReturn(new ArrayList<>(Arrays.asList
                (fakeSpecies)));
        EasyMock.replay(players.get(this.playerIndex), this.wateringHole, this.drawPile, this.discardPile, fakeCard);
        g.addTraitToSpecies(this.playerIndex, -1, fakeCard);
        EasyMock.verify(players.get(this.playerIndex), this.wateringHole, this.drawPile, this.discardPile, fakeCard);
    }

    @Test(expected = IllegalSpeciesIndexException.class)
    public void testAddTraitHighSpecies() throws IllegalNumberOfPlayers, SpeciesNumberTraitsException,
            SpeciesDuplicateTraitException, InvalidPlayerSelectException, IllegalSpeciesIndexException, NullGameObjectException, IllegalCardRemovalException {
        ArrayList<IPlayer> players = generateNumPlayers(this.numPlayers);
        Game g = new Game(players, this.wateringHole, this.drawPile, this.discardPile);
        Card fakeCard = EasyMock.niceMock(Card.class);
        Species fakeSpecies = EasyMock.niceMock(Species.class);
        players.get(this.playerIndex).removeCardFromHand(fakeCard);
        EasyMock.expect(players.get(this.playerIndex).getSpecies()).andReturn(new ArrayList<>(Arrays.asList
                (fakeSpecies)));
        EasyMock.replay(players.get(this.playerIndex), this.wateringHole, this.drawPile, this.discardPile, fakeCard);
        g.addTraitToSpecies(this.playerIndex, 1, fakeCard);
        EasyMock.verify(players.get(this.playerIndex), this.wateringHole, this.drawPile, this.discardPile, fakeCard);
    }

    @Test(expected = NullGameObjectException.class)
    public void testAddTraitNullCard() throws SpeciesNumberTraitsException, SpeciesDuplicateTraitException,
            IllegalNumberOfPlayers, InvalidPlayerSelectException, IllegalSpeciesIndexException, NullGameObjectException, IllegalCardRemovalException {
        ArrayList<IPlayer> players = generateNumPlayers(this.numPlayers);
        Game g = new Game(players, this.wateringHole, this.drawPile, this.discardPile);
        g.addTraitToSpecies(this.playerIndex, 0, null);
    }

    @Test
    public void testRemoveTrait() throws IllegalNumberOfPlayers, SpeciesTraitNotFoundException, InvalidPlayerSelectException, IllegalSpeciesIndexException, NullGameObjectException {
        ArrayList<IPlayer> players = generateNumPlayers(this.numPlayers);
        ISpecies fakeSpecies = EasyMock.niceMock(Species.class);
        ICard fakeCard = EasyMock.niceMock(Card.class);
        Game g = new Game(players, this.wateringHole, this.drawPile, this.discardPile);
        EasyMock.expect(players.get(this.playerIndex).getSpecies())
                .andReturn(new ArrayList<>(Arrays.asList(fakeSpecies)));
        EasyMock.expect(players.get(this.playerIndex).getSpecies())
                .andReturn(new ArrayList<>(Arrays.asList(fakeSpecies)));
        EasyMock.expect(fakeSpecies.removeTrait(fakeCard)).andReturn(fakeCard);
        this.discardPile.discard(fakeCard);
        EasyMock.replay(players.get(this.playerIndex));
        EasyMock.replay(fakeCard);
        EasyMock.replay(fakeSpecies);
        EasyMock.replay(this.discardPile);
        g.removeTraitFromSpecies(this.playerIndex, 0, fakeCard);
        EasyMock.verify(players.get(this.playerIndex));
        EasyMock.verify(fakeCard);
        EasyMock.verify(fakeSpecies);
        EasyMock.verify(this.discardPile);
    }

    @Test(expected = InvalidPlayerSelectException.class)
    public void testRemoveTraitInvalidPlayerNegative() throws IllegalNumberOfPlayers, SpeciesTraitNotFoundException, InvalidPlayerSelectException, IllegalSpeciesIndexException, NullGameObjectException {
        ArrayList<IPlayer> players = generateNumPlayers(this.numPlayers);
        ICard fakeCard = EasyMock.niceMock(Card.class);
        Game g = new Game(players, this.wateringHole, this.drawPile, this.discardPile);
        g.removeTraitFromSpecies(-1, 0, fakeCard);
    }

    @Test(expected = InvalidPlayerSelectException.class)
    public void testRemoveTraitInvalidPlayerTooHigh() throws IllegalNumberOfPlayers, SpeciesTraitNotFoundException, InvalidPlayerSelectException, IllegalSpeciesIndexException, NullGameObjectException {
        ArrayList<IPlayer> players = generateNumPlayers(this.numPlayers);
        ICard fakeCard = EasyMock.niceMock(Card.class);
        Game g = new Game(players, this.wateringHole, this.drawPile, this.discardPile);
        g.removeTraitFromSpecies(this.numPlayers, 0, fakeCard);
    }

    @Test(expected = IllegalSpeciesIndexException.class)
    public void testRemoveTraitInvalidSpeciesIndexNegative() throws IllegalNumberOfPlayers, SpeciesTraitNotFoundException, InvalidPlayerSelectException, IllegalSpeciesIndexException, NullGameObjectException {
        ArrayList<IPlayer> players = generateNumPlayers(this.numPlayers);
        ISpecies fakeSpecies = EasyMock.niceMock(Species.class);
        ICard fakeCard = EasyMock.niceMock(Card.class);
        Game g = new Game(players, this.wateringHole, this.drawPile, this.discardPile);
        EasyMock.expect(players.get(this.playerIndex).getSpecies())
                .andReturn(new ArrayList<>(Arrays.asList(fakeSpecies)));
        EasyMock.replay(players.get(this.playerIndex));
        g.removeTraitFromSpecies(this.playerIndex, -1, fakeCard);
    }

    @Test(expected = IllegalSpeciesIndexException.class)
    public void testRemoveTraitInvalidSpeciesIndexTooHigh() throws IllegalNumberOfPlayers, SpeciesTraitNotFoundException, InvalidPlayerSelectException, IllegalSpeciesIndexException, NullGameObjectException {
        ArrayList<IPlayer> players = generateNumPlayers(this.numPlayers);
        ISpecies fakeSpecies = EasyMock.niceMock(Species.class);
        ICard fakeCard = EasyMock.niceMock(Card.class);
        Game g = new Game(players, this.wateringHole, this.drawPile, this.discardPile);
        EasyMock.expect(players.get(this.playerIndex).getSpecies())
                .andReturn(new ArrayList<>(Arrays.asList(fakeSpecies)));
        EasyMock.replay(players.get(this.playerIndex));
        g.removeTraitFromSpecies(this.playerIndex, 1, fakeCard);
    }

    @Test(expected = NullGameObjectException.class)
    public void testRemoveTraitInvalidNullTrait() throws IllegalNumberOfPlayers, SpeciesTraitNotFoundException, InvalidPlayerSelectException, IllegalSpeciesIndexException, NullGameObjectException {
        ArrayList<IPlayer> players = generateNumPlayers(this.numPlayers);
        Game g = new Game(players, this.wateringHole, this.drawPile, this.discardPile);
        g.removeTraitFromSpecies(this.playerIndex, 0, null);
    }

    @Test
    public void testEmptyGetTraits() throws IllegalNumberOfPlayers, NullGameObjectException, InvalidPlayerSelectException, IllegalSpeciesIndexException {
        Game g = new Game(generateNumRealPlayers(this.numPlayers), this.wateringHole, this.drawPile, this.discardPile);
        ArrayList<ICard> traits = g.getTraits(this.playerIndex, 0);
        assertTrue(traits.size() == 0);
    }

    @Test
    public void testGetTraits() throws IllegalNumberOfPlayers, NullGameObjectException, IllegalCardRemovalException,
            IllegalSpeciesIndexException, InvalidPlayerSelectException, SpeciesDuplicateTraitException,
            SpeciesNumberTraitsException, DeckEmptyException {
        ArrayList<IPlayer> players = generateNumRealPlayers(this.numPlayers);
        Game g = new Game(players, this.wateringHole, this.drawPile, this.discardPile);
        ICard card = EasyMock.niceMock(Card.class);
        players.get(this.playerIndex).getSpecies().get(0).addTrait(card);
        ArrayList<ICard> traits = g.getTraits(this.playerIndex, 0);
        assertTrue(traits.size() == 1);
        assertEquals(traits.get(0), card);
    }

    @Test(expected = InvalidPlayerSelectException.class)
    public void testGetTraitsInvalidPlayer() throws IllegalNumberOfPlayers, NullGameObjectException, IllegalCardRemovalException,
            IllegalSpeciesIndexException, InvalidPlayerSelectException, SpeciesDuplicateTraitException,
            SpeciesNumberTraitsException, DeckEmptyException {
        Game g = new Game(generateNumPlayers(this.numPlayers), this.wateringHole, this.drawPile, this.discardPile);
        g.getTraits(-1, 0);
    }

    @Test(expected = IllegalSpeciesIndexException.class)
    public void testGetTraitsInvalidSpecies() throws IllegalNumberOfPlayers, NullGameObjectException,
            IllegalCardRemovalException, IllegalSpeciesIndexException, InvalidPlayerSelectException,
            SpeciesDuplicateTraitException, SpeciesNumberTraitsException, DeckEmptyException {
        Game g = new Game(generateNumRealPlayers(this.numPlayers), this.wateringHole, this.drawPile, this.discardPile);
        g.getTraits(this.playerIndex, 1);
    }

    @Test
    public void testAllFull() throws IllegalNumberOfPlayers, NullGameObjectException {
        Game g = new Game(generateNumPlayers(this.numPlayers), this.wateringHole, this.drawPile, this.discardPile);
        for (IPlayer p : g.getPlayerObjects()) {
            EasyMock.expect(p.allSpeciesFull()).andReturn(true);
        }
        g.getPlayerObjects().forEach(EasyMock::replay);
        assertTrue(g.allFull());
        g.getPlayerObjects().forEach(EasyMock::verify);
    }

    @Test
    public void testIncreasePopulation() throws SpeciesPopulationException, IllegalNumberOfPlayers, NullGameObjectException, InvalidPlayerSelectException {
        Game g = new Game(generateNumPlayers(this.numPlayers), this.wateringHole, this.drawPile, this.discardPile);
        ISpecies fakeSpecies = EasyMock.niceMock(Species.class);
        EasyMock.expect(g.getPlayerObjects().get(this.playerIndex).getSpecies()).andReturn(new ArrayList<>(Arrays
                .asList(fakeSpecies)));
        fakeSpecies.increasePopulation();
        EasyMock.replay(g.getPlayerObjects().get(this.playerIndex), fakeSpecies);
        g.increasePopulation(this.playerIndex, 0);
        EasyMock.verify(g.getPlayerObjects().get(this.playerIndex), fakeSpecies);
    }

    @Test(expected = InvalidPlayerSelectException.class)
    public void testIncreasePopulationHighBound() throws SpeciesPopulationException, IllegalNumberOfPlayers,
            NullGameObjectException, InvalidPlayerSelectException {
        Game g = new Game(generateNumPlayers(this.numPlayers), this.wateringHole, this.drawPile, this.discardPile);
        g.increasePopulation(this.numPlayers, 0);
    }

    @Test(expected = InvalidPlayerSelectException.class)
    public void testIncreasePopulationLowBound() throws SpeciesPopulationException, IllegalNumberOfPlayers,
            NullGameObjectException, InvalidPlayerSelectException {
        Game g = new Game(generateNumPlayers(this.numPlayers), this.wateringHole, this.drawPile, this.discardPile);
        g.increasePopulation(-1, 0);
    }
}
