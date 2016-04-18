package com.Evolution.logic;

import com.Evolution.exceptions.*;
import com.Evolution.interfaces.*;

import java.util.ArrayList;

/**
 * Game logic controller class. Handles most interaction between related game objects.
 */
public class Game {
    private int round = 1;
    private int turn = 1;
    private ArrayList<IPlayer> players;
    private IDeck<ICard> drawPile;
    private IDeck<ICard> discardPile;
    private IWateringHole wateringHole;
    private int foodBank = 240;
    private IPhases currentPhase = new PhaseOne(this);

    /**
     * Evolution Game constructor which contains main logic to interact with players, species, and cards
     *
     * @param players      playing game
     * @param wateringHole food available to species
     * @param drawPile     cards available to draw from
     * @param discardPile  cards that have been discarded
     * @throws IllegalNumberOfPlayers
     * @throws IllegalCardDirectionException
     */
    public Game(ArrayList<IPlayer> players, IWateringHole wateringHole, IDeck<ICard> drawPile, IDeck<ICard> discardPile)
            throws IllegalNumberOfPlayers, IllegalCardDirectionException {
        if (players.size() < 3 || players.size() > 5) {
            throw new IllegalNumberOfPlayers("You must have between 3-5 players.\n");
        }
        this.players = players;
        this.wateringHole = wateringHole;

        this.drawPile = drawPile;
        this.discardPile = discardPile;
    }

    /**
     * Returns the current round of the Game
     *
     * @return round
     */
    public int getRound() {
        return this.round;
    }

    /**
     * Increases the current round by one
     */
    public void increaseRound() {
        this.round++;
    }

    /**
     * Returns the list of players in the Game
     *
     * @return players
     */
    public ArrayList<IPlayer> getPlayerObjects() {
        return this.players;
    }

    /**
     * Returns the Deck of Cards corresponding to the draw pile
     *
     * @return drawPile
     */
    public IDeck<ICard> getDrawPile() {
        return this.drawPile;
    }

    /**
     * Returns the Deck of Cards corresponding to the discard pile
     *
     * @return discardPile
     */
    public IDeck<ICard> getDiscardPile() {
        return this.discardPile;
    }

    /**
     * Starts the game with Phase 1.
     * Calls currentPhase.execute()
     */
    public void startGame() throws IllegalCardDirectionException, DeckEmptyException, InvalidPlayerSelectException {
        this.currentPhase.execute();
    }

    /**
     * The turn that the game is currently on
     *
     * @return The number of the player whose turn it is
     */
    public int getTurn() {
        return this.turn;
    }

    /**
     * Increments which player number's turn it is
     */
    public void nextTurn() {
        if (this.turn == this.players.size()) {
            this.turn = 1;
        } else {
            this.turn++;
        }
    }

    /**
     * Gets the IWateringHole associated with this Game
     *
     * @return IWateringHole
     */
    public IWateringHole getWateringHole() {
        return this.wateringHole;
    }

    /**
     * Returns the amount of food currently available in the bank
     *
     * @return foodBank
     */
    public int getFoodBankCount() {
        return this.foodBank;
    }

    /**
     * Decrements the food bank by one
     */
    public void decrementFoodBank() throws FoodBankEmptyException {
        if (this.foodBank == 0) {
            throw new FoodBankEmptyException("The food bank is empty");
        }
        this.foodBank--;
    }

    /**
     * Decrements the food bank by i
     *
     * @param i food
     */
    public void decrementFoodBank(int i) throws FoodBankEmptyException {
        if (i > this.foodBank || i < 0) {
            throw new FoodBankEmptyException("The argument is larger than the current food bank.");
        }
        this.foodBank -= i;
    }

    /**
     * Decrements the food bank by i and increments the wateringHole food by i
     *
     * @param i food
     */
    public void moveFoodFromBankToHole(int i) throws FoodBankEmptyException {
        decrementFoodBank(i);
        this.wateringHole.addFood(i);
    }

    /**
     * Deal a card from the draw pile to a player
     *
     * @param i the index of the player
     * @throws DeckEmptyException           propagated from {@link Deck#draw()}
     * @throws InvalidPlayerSelectException if the index provided is outside of [0, size) of {@link #getPlayerObjects()}
     */
    public void dealToPlayer(int i) throws DeckEmptyException, InvalidPlayerSelectException {
        if (i >= this.players.size() || i < 0) {
            throw new InvalidPlayerSelectException("You selected an invalid player to deal to.");
        }
        ICard card = this.drawPile.draw();
        this.players.get(i).addCardToHand(card);
    }

    /**
     * Changes the current phase to phase;
     *
     * @param phase The phase being set
     */
    public void setPhase(IPhases phase) {
        this.currentPhase = phase;
    }

    /**
     * Draws the appropriate amount of cards for each player.
     * Appropriate amount = # of species + 3
     *
     * @throws DeckEmptyException           propagated from {@link #dealToPlayer(int)}
     * @throws InvalidPlayerSelectException propagated from {@link #dealToPlayer(int)}
     */
    public void drawForPlayers() throws DeckEmptyException, InvalidPlayerSelectException {
        for (int i = 0; i < this.players.size(); i++) {
            for (int j = 0; j < this.players.get(i).getSpecies().size() + 3; j++) {
                dealToPlayer(i);
            }
        }
    }

    /**
     * Removes the provided card object from the hand of the player specified by i
     *
     * @param i    player index
     * @param card to remove
     * @return successful discard
     */
    public boolean discardFromPlayer(int i, ICard card) {
        if (this.players.get(i).removeCardFromHand(card)) {
            this.discardPile.discard(card);
            return true;
        }
        return false;
    }

    /**
     * Returns the game's current phase
     *
     * @return phase
     */
    public IPhases getPhase() {
        return this.currentPhase;
    }

    /**
     * Discards the given card from the player at the given index's hand
     *
     * @param index The index of the player
     * @param card  The card to discard
     * @throws InvalidAddToWateringHoleException propagated from {@link IWateringHole#addCard(ICard)}
     * @throws InvalidDiscardToWateringHoleException trying to discard to watering hole when it already has the
     * maximum number of cards
     */
    public void discardToWateringHole(int index, ICard card) throws InvalidDiscardToWateringHoleException,
            InvalidAddToWateringHoleException {
        if (this.wateringHole.getCards().size() == this.players.size()) {
            throw new InvalidDiscardToWateringHoleException("You can not discard more cards to the watering hole " +
                    "than the number of players.");
        }
        this.wateringHole.addCard(card);
        this.players.get(index).removeCardFromHand(card);
    }

    public void discardForLeftSpecies(int playerIndex, ICard card, ISpecies species) {
        this.discardPile.discard(card);
        this.players.get(0).addSpeciesLeft(species);
    }
}
