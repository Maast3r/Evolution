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

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(Parameterized.class)
public class GameTests {
    private IWateringHole wateringHole;
    private IDeck<ICard> drawPile;
    private IDeck<ICard> discardPile;
    private int numPlayers;
    private int playerIndex;
    private int overHighBound;

    public GameTests(int numPlayers, int playerIndex, int overHighBound) {
        this.numPlayers = numPlayers;
        this.playerIndex = playerIndex;
        this.overHighBound = overHighBound;
    }

    @Parameterized.Parameters
    public static Collection playersToCheck() {
        return Arrays.asList(new Object[][]{
                {3, 0, 4}, {3, 1, 4}, {3, 2, 4},
                {4, 0, 5}, {4, 1, 5}, {4, 2, 5}, {4, 3, 5},
                {5, 0, 6}, {5, 1, 6}, {5, 2, 6}, {5, 3, 6}, {5, 4, 6}
        });
    }

    private ArrayList<IPlayer> generateNumPlayers(int num) {
        ArrayList<IPlayer> players = new ArrayList<>();
        for (int i = 0; i < num; i++) {
            players.add(EasyMock.niceMock(Player.class));
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
            IllegalCardDirectionException {
        Game g = new Game(generateNumPlayers(this.numPlayers), wateringHole, this.drawPile, this.discardPile);
        assertEquals(this.numPlayers, g.getPlayerObjects().size());
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
    public void getPlayers() throws IllegalNumberOfPlayers,
            IllegalCardDirectionException {
        Game g = new Game(generateNumPlayers(this.numPlayers), wateringHole, this.drawPile, this.discardPile);
        assertEquals(this.numPlayers, g.getPlayerObjects().size());
    }

    @Test
    public void getDrawPile() throws IllegalNumberOfPlayers,
            IllegalCardDirectionException {
        Game g = new Game(generateNumPlayers(this.numPlayers), wateringHole, this.drawPile, this.discardPile);
        assertTrue(g.getDrawPile() == this.drawPile);
        assertEquals(0, g.getDrawPile().getSize());
    }

    @Test
    public void getDiscardPile1() throws IllegalNumberOfPlayers,
            IllegalCardDirectionException {
        Game g = new Game(generateNumPlayers(this.numPlayers), wateringHole, this.drawPile, this.discardPile);
        assertTrue(this.discardPile == g.getDiscardPile());
        assertEquals(0, g.getDiscardPile().getSize());
    }

    @Test
    public void getDiscardPile2() throws IllegalNumberOfPlayers,
            IllegalCardDirectionException {
        IDeck<ICard> discardPile = new Deck<>();
        Game g = new Game(generateNumPlayers(this.numPlayers), wateringHole, this.drawPile, discardPile);
        g.getDiscardPile().discard(new TestCard());
        assertEquals(1, g.getDiscardPile().getSize());
    }

    @Test
    public void testGetCurrentRound() throws IllegalNumberOfPlayers,
            IllegalCardDirectionException {
        Game g = new Game(generateNumPlayers(this.numPlayers), wateringHole, this.drawPile, this.discardPile);
        assertEquals(1, g.getRound());
    }

    @Test
    public void testIncreaseRound() throws IllegalNumberOfPlayers,
            IllegalCardDirectionException {
        Game g = new Game(generateNumPlayers(this.numPlayers), wateringHole, this.drawPile, this.discardPile);
        g.increaseRound();
        assertEquals(2, g.getRound());
    }

    @Test
    public void testIncreaseMultiRound() throws IllegalNumberOfPlayers,
            IllegalCardDirectionException {
        Game g = new Game(generateNumPlayers(this.numPlayers), wateringHole, this.drawPile, this.discardPile);
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
        Game g = new Game(generateNumPlayers(this.numPlayers), wateringHole, this.drawPile, this.discardPile);
        assertTrue(g.getTurn() == 1);
    }

    @Test
    public void testGetTurn3() throws IllegalNumberOfPlayers, IllegalCardDirectionException {
        Game g = new Game(generateNumPlayers(this.numPlayers), wateringHole, this.drawPile, this.discardPile);
        g.nextTurn();
        g.nextTurn();
        assertTrue(g.getTurn() == 3);
    }

    @Test
    public void testGetTurn6() throws IllegalNumberOfPlayers, IllegalCardDirectionException {
        Game g = new Game(generateNumPlayers(this.numPlayers), wateringHole, this.drawPile, this.discardPile);
        for (int j = 0; j < this.numPlayers; j++) {
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
        for (int i = 0; i < 241; i++) {
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
        for (int i = 0; i < this.numPlayers; i++) {
            playerList.add(player);
        }
        Game g = new Game(playerList, this.wateringHole, this.drawPile, discardPile);
        g.dealToPlayer(this.overHighBound);
    }

    @Test(expected = InvalidPlayerSelectException.class)
    public void testInvalidPlayerSelect2() throws IllegalNumberOfPlayers, IllegalCardDirectionException,
            DeckEmptyException, InvalidPlayerSelectException {
        Deck<ICard> discardPile = new Deck<>();
        ICard card = new TestCard();
        assertTrue(!discardPile.contains(card));
        Player player = new Player(new TestSpecies());
        ArrayList<IPlayer> playerList = new ArrayList<>();
        for (int i = 0; i < this.numPlayers; i++) {
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
        for (int i = 0; i < this.numPlayers; i++) {
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
        for (int i = 0; i < this.numPlayers; i++) {
            playerList.add(player);
        }
        Game g = new Game(playerList, this.wateringHole, this.drawPile, discardPile);
        g.setPhase(new PhaseTwo(g));
        assertEquals(PhaseTwo.class, g.getPhase().getClass());
    }

    @Test
    public void testDiscardToWateringHole() throws IllegalNumberOfPlayers, IllegalCardDirectionException,
            InvalidPlayerSelectException, DeckEmptyException, InvalidDiscardToWateringHoleException,
            InvalidAddToWateringHoleException {
        Deck<ICard> drawPile = new Deck<>();
        WateringHole wateringHole = new WateringHole();
        ICard card = new TestCard();
        drawPile.discard(card);
        Player player = new Player(new TestSpecies());
        ArrayList<IPlayer> playerList = new ArrayList<>();
        for (int i = 0; i < this.numPlayers; i++) {
            playerList.add(player);
        }
        Game g = new Game(playerList, wateringHole, drawPile, this.discardPile);
        g.dealToPlayer(this.playerIndex);
        assertTrue(g.getPlayerObjects().get(this.playerIndex).getCards().contains(card));
        assertTrue(!g.getWateringHole().getCards().contains(card));
        g.discardToWateringHole(this.playerIndex, g.getPlayerObjects().get(this.playerIndex).getCards().get(0));
        assertTrue(!g.getPlayerObjects().get(this.playerIndex).getCards().contains(card));
        assertTrue(g.getWateringHole().getCards().contains(card));
    }

    /**
     * BVA - Can only add card to the watering hole equal to the number of players
     */
    @Test(expected = InvalidDiscardToWateringHoleException.class)
    public void testDiscardExtraToWateringHole() throws IllegalNumberOfPlayers, IllegalCardDirectionException,
            InvalidPlayerSelectException, DeckEmptyException, InvalidDiscardToWateringHoleException,
            InvalidAddToWateringHoleException {
        Deck<ICard> drawPile = new Deck<>();
        WateringHole wateringHole = new WateringHole();
        for (int i = 0; i < this.numPlayers + 1; i++) {
            ICard card = new TestCard();
            drawPile.discard(card);
        }
        Player player = new Player(new TestSpecies());
        ArrayList<IPlayer> playerList = new ArrayList<>();
        for (int j = 0; j < this.numPlayers; j++) {
            playerList.add(player);
        }
        Game g = new Game(playerList, wateringHole, drawPile, this.discardPile);
        for (int k = 0; k < this.numPlayers + 1; k++) {
            g.dealToPlayer(k % this.numPlayers);
            g.discardToWateringHole(k % this.numPlayers, g.getPlayerObjects().get(k % this.numPlayers).getCards().get(0));
        }
    }

    @Test
    public void testDiscardForLeftSpecies() throws IllegalNumberOfPlayers, IllegalCardDirectionException {
        ArrayList<IPlayer> players = generateNumPlayers(this.numPlayers);
        Game g = new Game(players, this.wateringHole, this.drawPile, this.discardPile);
        ISpecies fakeSpecies = EasyMock.niceMock(Species.class);
        ICard fakeCard = EasyMock.niceMock(Card.class);
        players.get(this.playerIndex).addSpeciesLeft(fakeSpecies);
        this.discardPile.discard(fakeCard);
        EasyMock.replay(players.get(this.playerIndex), this.discardPile, fakeSpecies, fakeCard);
        g.discardForLeftSpecies(this.playerIndex, fakeCard, fakeSpecies);
        EasyMock.verify(players.get(this.playerIndex), this.discardPile, fakeSpecies, fakeCard);
    }

    @Test
    public void testDiscardForRightSpecies() throws IllegalNumberOfPlayers, IllegalCardDirectionException {
        ArrayList<IPlayer> players = generateNumPlayers(this.numPlayers);
        Game g = new Game(players, this.wateringHole, this.drawPile, this.discardPile);
        ISpecies fakeSpecies = EasyMock.niceMock(Species.class);
        ICard fakeCard = EasyMock.niceMock(Card.class);
        players.get(this.playerIndex).addSpeciesRight(fakeSpecies);
        this.discardPile.discard(fakeCard);
        EasyMock.replay(players.get(this.playerIndex), this.discardPile, fakeSpecies, fakeCard);
        g.discardForRightSpecies(this.playerIndex, fakeCard, fakeSpecies);
        EasyMock.verify(players.get(this.playerIndex), this.discardPile, fakeSpecies, fakeCard);
    }

    @Test
    public void testDiscardToIncreasePopulation() throws IllegalNumberOfPlayers, IllegalCardDirectionException,
            SpeciesPopulationException, InvalidPlayerSelectException, DeckEmptyException, IllegalCardDiscardException, IllegalPlayerIndexException, IllegalSpeciesIndexException {
        Deck<ICard> drawPile = new Deck<>();
        for (int i = 0; i < 4; i++) {
            ICard card = new TestCard();
            drawPile.discard(card);
        }
        Player player = new Player(new Species());
        ArrayList<IPlayer> playerList = new ArrayList<>();
        for (int j = 0; j < 3; j++) {
            playerList.add(player);
        }
        Game g = new Game(playerList, this.wateringHole, drawPile, this.discardPile);
        for (int i = 0; i < 3; i++) {
            g.dealToPlayer(i);
        }
        g.increasePopulation(0, 0, g.getPlayerObjects().get(0).getCards().get(0));
    }

    @Test(expected = IllegalCardDiscardException.class)
    public void testDiscardToIncreasePopulation2() throws IllegalNumberOfPlayers, IllegalCardDirectionException,
            SpeciesPopulationException, InvalidPlayerSelectException, DeckEmptyException, IllegalCardDiscardException,
            IllegalPlayerIndexException, IllegalSpeciesIndexException {
        Deck<ICard> drawPile = new Deck<>();
        for (int i = 0; i < 4; i++) {
            ICard card = new TestCard();
            drawPile.discard(card);
        }
        Player player = new Player(new Species());
        ArrayList<IPlayer> playerList = new ArrayList<>();
        for (int j = 0; j < 3; j++) {
            playerList.add(player);
        }
        Game g = new Game(playerList, this.wateringHole, drawPile, this.discardPile);
        for (int i = 0; i < 3; i++) {
            g.dealToPlayer(i);
        }
        g.increasePopulation(0, 0, new TestCard());
    }

    @Test(expected = IllegalPlayerIndexException.class)
    public void testDiscardToIncreasePopulation3() throws IllegalNumberOfPlayers, IllegalCardDirectionException,
            SpeciesPopulationException, InvalidPlayerSelectException, DeckEmptyException, IllegalCardDiscardException,
            IllegalPlayerIndexException, IllegalSpeciesIndexException {
        ArrayList<IPlayer> playerList = new ArrayList<>();
        for (int j = 0; j < 3; j++) {
            playerList.add(new TestPlayer(new TestSpecies()));
        }
        Game g = new Game(playerList, this.wateringHole, this.drawPile, this.discardPile);
        g.increasePopulation(3, 0, new TestCard());
    }

    @Test(expected = IllegalSpeciesIndexException.class)
    public void testDiscardToIncreasePopulation4() throws IllegalNumberOfPlayers, IllegalCardDirectionException,
            SpeciesPopulationException, InvalidPlayerSelectException, DeckEmptyException, IllegalCardDiscardException,
            IllegalPlayerIndexException, IllegalSpeciesIndexException {
        ArrayList<IPlayer> playerList = new ArrayList<>();
        for (int j = 0; j < 3; j++) {
            playerList.add(new Player(new TestSpecies()));
        }
        Deck<ICard> drawPile = new Deck<>();
        for (int i = 0; i < 4; i++) {
            ICard card = new TestCard();
            drawPile.discard(card);
        }
        Game g = new Game(playerList, this.wateringHole, drawPile, this.discardPile);
        g.dealToPlayer(0);
        g.increasePopulation(0, 1, playerList.get(0).getCards().get(0));
    }

    @Test
    public void testDiscardToIncreaseBodySize() throws IllegalNumberOfPlayers, IllegalCardDirectionException,
            InvalidPlayerSelectException, DeckEmptyException, IllegalCardDiscardException, IllegalPlayerIndexException,
            IllegalSpeciesIndexException, SpeciesBodySizeException {
        Deck<ICard> drawPile = new Deck<>();
        for (int i = 0; i < 4; i++) {
            ICard card = new TestCard();
            drawPile.discard(card);
        }
        Player player = new Player(new Species());
        ArrayList<IPlayer> playerList = new ArrayList<>();
        for (int j = 0; j < 3; j++) {
            playerList.add(player);
        }
        Game g = new Game(playerList, this.wateringHole, drawPile, this.discardPile);
        for (int i = 0; i < 3; i++) {
            g.dealToPlayer(i);
        }
        g.increaseBodySize(0, 0, playerList.get(0).getCards().get(0));
    }

    @Test(expected = IllegalPlayerIndexException.class)
    public void testDiscardToIncreaseBodySize2() throws IllegalNumberOfPlayers, IllegalCardDirectionException,
            InvalidPlayerSelectException, DeckEmptyException, IllegalCardDiscardException, IllegalPlayerIndexException,
            IllegalSpeciesIndexException, SpeciesBodySizeException {
        ArrayList<IPlayer> playerList = new ArrayList<>();
        for (int j = 0; j < 3; j++) {
            playerList.add(new TestPlayer(new TestSpecies()));
        }
        Game g = new Game(playerList, this.wateringHole, this.drawPile, this.discardPile);
        g.increaseBodySize(3, 0, new TestCard());
    }

    @Test(expected = IllegalCardDiscardException.class)
    public void testDiscardToIncreaseBodySize3() throws IllegalNumberOfPlayers, IllegalCardDirectionException,
            InvalidPlayerSelectException, DeckEmptyException, IllegalCardDiscardException, IllegalPlayerIndexException,
            IllegalSpeciesIndexException, SpeciesBodySizeException {
        Deck<ICard> drawPile = new Deck<>();
        for (int i = 0; i < 4; i++) {
            ICard card = new TestCard();
            drawPile.discard(card);
        }
        Player player = new Player(new Species());
        ArrayList<IPlayer> playerList = new ArrayList<>();
        for (int j = 0; j < 3; j++) {
            playerList.add(player);
        }
        Game g = new Game(playerList, this.wateringHole, drawPile, this.discardPile);
        for (int i = 0; i < 3; i++) {
            g.dealToPlayer(i);
        }
        g.increaseBodySize(0, 0, new TestCard());
    }

    @Test(expected = IllegalSpeciesIndexException.class)
    public void testDiscardToIncreaseBodySize4() throws IllegalNumberOfPlayers, IllegalCardDirectionException,
            InvalidPlayerSelectException, DeckEmptyException, IllegalCardDiscardException, IllegalPlayerIndexException,
            IllegalSpeciesIndexException, SpeciesBodySizeException {
        ArrayList<IPlayer> playerList = new ArrayList<>();
        for (int j = 0; j < 3; j++) {
            playerList.add(new Player(new TestSpecies()));
        }
        Deck<ICard> drawPile = new Deck<>();
        for (int i = 0; i < 4; i++) {
            ICard card = new TestCard();
            drawPile.discard(card);
        }
        Game g = new Game(playerList, this.wateringHole, drawPile, this.discardPile);
        g.dealToPlayer(0);
        g.increaseBodySize(0, 1, playerList.get(0).getCards().get(0));
    }

    @Test
    public void testAddTrait() throws IllegalNumberOfPlayers, SpeciesNumberTraitsException, SpeciesDuplicateTraitException, IllegalPlayerIndexException, IllegalSpeciesIndexException, NullGameObjectException {
        ArrayList<IPlayer> players = generateNumPlayers(this.numPlayers);
        Game g = new Game(players, this.wateringHole, this.drawPile, this.discardPile);
        Card fakeCard = EasyMock.niceMock(Card.class);
        Species fakeSpecies = EasyMock.niceMock(Species.class);
        EasyMock.expect(players.get(this.playerIndex).removeCardFromHand(fakeCard)).andReturn(true);
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

    @Test(expected = IllegalPlayerIndexException.class)
    public void testAddTraitLowPlayer() throws SpeciesNumberTraitsException, SpeciesDuplicateTraitException, IllegalNumberOfPlayers, IllegalPlayerIndexException, IllegalSpeciesIndexException, NullGameObjectException {
        ArrayList<IPlayer> players = generateNumPlayers(this.numPlayers);
        Game g = new Game(players, this.wateringHole, this.drawPile, this.discardPile);
        Card fakeCard = EasyMock.niceMock(Card.class);
        g.addTraitToSpecies(-1, 0, fakeCard);
    }

    @Test(expected = IllegalPlayerIndexException.class)
    public void testAddTraitHighPlayer() throws SpeciesNumberTraitsException, SpeciesDuplicateTraitException,
            IllegalNumberOfPlayers, IllegalPlayerIndexException, IllegalSpeciesIndexException, NullGameObjectException {
        ArrayList<IPlayer> players = generateNumPlayers(this.numPlayers);
        Game g = new Game(players, this.wateringHole, this.drawPile, this.discardPile);
        Card fakeCard = EasyMock.niceMock(Card.class);
        g.addTraitToSpecies(this.overHighBound, 0, fakeCard);
    }

    @Test(expected = IllegalSpeciesIndexException.class)
    public void testAddTraitLowSpecies() throws IllegalNumberOfPlayers, SpeciesNumberTraitsException,
            SpeciesDuplicateTraitException, IllegalPlayerIndexException, IllegalSpeciesIndexException, NullGameObjectException {
        ArrayList<IPlayer> players = generateNumPlayers(this.numPlayers);
        Game g = new Game(players, this.wateringHole, this.drawPile, this.discardPile);
        Card fakeCard = EasyMock.niceMock(Card.class);
        Species fakeSpecies = EasyMock.niceMock(Species.class);
        EasyMock.expect(players.get(this.playerIndex).removeCardFromHand(fakeCard)).andReturn(true);
        EasyMock.expect(players.get(this.playerIndex).getSpecies()).andReturn(new ArrayList<>(Arrays.asList
                (fakeSpecies)));
        EasyMock.replay(players.get(this.playerIndex), this.wateringHole, this.drawPile, this.discardPile, fakeCard);
        g.addTraitToSpecies(this.playerIndex, -1, fakeCard);
        EasyMock.verify(players.get(this.playerIndex), this.wateringHole, this.drawPile, this.discardPile, fakeCard);
    }

    @Test(expected = IllegalSpeciesIndexException.class)
    public void testAddTraitHighSpecies() throws IllegalNumberOfPlayers, SpeciesNumberTraitsException,
            SpeciesDuplicateTraitException, IllegalPlayerIndexException, IllegalSpeciesIndexException, NullGameObjectException {
        ArrayList<IPlayer> players = generateNumPlayers(this.numPlayers);
        Game g = new Game(players, this.wateringHole, this.drawPile, this.discardPile);
        Card fakeCard = EasyMock.niceMock(Card.class);
        Species fakeSpecies = EasyMock.niceMock(Species.class);
        EasyMock.expect(players.get(this.playerIndex).removeCardFromHand(fakeCard)).andReturn(true);
        EasyMock.expect(players.get(this.playerIndex).getSpecies()).andReturn(new ArrayList<>(Arrays.asList
                (fakeSpecies)));
        EasyMock.replay(players.get(this.playerIndex), this.wateringHole, this.drawPile, this.discardPile, fakeCard);
        g.addTraitToSpecies(this.playerIndex, 1, fakeCard);
        EasyMock.verify(players.get(this.playerIndex), this.wateringHole, this.drawPile, this.discardPile, fakeCard);
    }

    @Test(expected = NullGameObjectException.class)
    public void testAddTraitNullCard() throws SpeciesNumberTraitsException, SpeciesDuplicateTraitException,
            IllegalNumberOfPlayers, IllegalPlayerIndexException, IllegalSpeciesIndexException, NullGameObjectException {
        ArrayList<IPlayer> players = generateNumPlayers(this.numPlayers);
        Game g = new Game(players, this.wateringHole, this.drawPile, this.discardPile);
        g.addTraitToSpecies(this.playerIndex, 0, null);
    }
}
