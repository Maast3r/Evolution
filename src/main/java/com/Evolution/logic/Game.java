package com.Evolution.logic;

import com.Evolution.exceptions.IllegalCardDirectionException;
import com.Evolution.exceptions.IllegalNumberOfPlayers;
import com.Evolution.interfaces.ICard;
import com.Evolution.interfaces.IPhases;
import com.Evolution.interfaces.IPlayer;

import java.util.ArrayList;

/**
 * Game logic controller class. Handles most interaction between related game objects.
 */
public class Game {
    private int round = 1;
    private int turn = 1;
    private ArrayList<IPlayer> players;
    private Deck<ICard> drawPile;
    private Deck<ICard> discardPile;

    /**
     * Evolution Game constructor which contains main logic to interact with players, species, and cards
     *
     * @param players playing game
     * @throws IllegalNumberOfPlayers
     * @throws IllegalCardDirectionException
     */
    public Game(ArrayList<IPlayer> players) throws IllegalNumberOfPlayers, IllegalCardDirectionException {
        if (players.size() < 3 || players.size() > 6) {
            throw new IllegalNumberOfPlayers("You must have between 3-5 players.\n");
        }
        this.players = players;

        this.drawPile = new Deck<>();
        for (int i = 0; i < 50; i++) {
            drawPile.add(new Card("Carnivore",
                    "Makes a species a carnivore", "./carnivore.jpg", 3, 0));
        }
        this.discardPile = new Deck<>();
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
    public Deck<ICard> getDrawPile() {
        return this.drawPile;
    }

    /**
     * Returns the Deck of Cards corresponding to the discard pile
     *
     * @return discardPile
     */
    public Deck<ICard> getDiscardPile() {
        return this.discardPile;
    }

    /**
     * @param phase 1 of the game
     * Starts the game with Phase 1.
     * Calls PhaseOne.execute()
     */
    public void startGame(IPhases phase) {
        phase.execute();
    }

    /**
     * The turn that the game is currently on
     * @return The number of the player whose turn it is
     */
    public int getTurn() {
        return this.turn;
    }

    /**
     * Increments which player number's turn it is
     */
    public void nextTurn() {
        if(this.turn == this.players.size()){
            this.turn = 1;
        } else {
            this.turn++;
        }
    }
}
