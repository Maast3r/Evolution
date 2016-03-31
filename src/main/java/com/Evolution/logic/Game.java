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
     * @param phase 1 of the game
     *              Starts the game with Phase 1.
     *              Calls PhaseOne.execute()
     */
    public void startGame(IPhases phase) {
        phase.execute();
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
        if(this.foodBank == 0){
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
        if(i > this.foodBank || i < 0) {
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
     */
    public void dealToPlayer(int i) throws DeckEmptyException, InvalidPlayerSelectException {
        if(i > this.players.size()) {
            throw new InvalidPlayerSelectException("You selected an invalid player to deal to.");
        }
        ICard card = this.drawPile.draw();
        this.players.get(i).addCardToHand(card);
    }

    /**
     * Removes the provided card object from the hand of the player specified by i
     * @param i player index
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
}
