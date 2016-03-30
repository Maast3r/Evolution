package com.Evolution.logic;

import com.Evolution.exceptions.IllegalCardDirectionException;
import com.Evolution.exceptions.IllegalNumberOfPlayers;
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
     * @param wateringHole
     * @param drawPile
     * @param discardPile
     * @throws IllegalNumberOfPlayers
     * @throws IllegalCardDirectionException
     */
    public Game(ArrayList<IPlayer> players, IWateringHole wateringHole, IDeck<ICard> drawPile, IDeck<ICard> discardPile) throws
            IllegalNumberOfPlayers, IllegalCardDirectionException {
        // TODO: Refactor this to fulfill dependency injection by having the Decks and WateringHole passed in
        if (players.size() < 3 || players.size() > 6) {
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

    public int getFoodBankCount() {
        return this.foodBank;
    }


    public void decrementFoodBank() {
        this.foodBank--;
    }
}
