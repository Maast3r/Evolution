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
    private int numberOfPlayers;
    private ArrayList<IPlayer> players;
    private Deck<ICard> drawPile;
    private Deck<ICard> discardPile;

    /**
     * Evolution Game constructor which contains main logic to interact with players, species, and cards
     *
     * @param numberOfPlayers playing game
     * @throws IllegalNumberOfPlayers
     * @throws IllegalCardDirectionException
     */
    public Game(int numberOfPlayers) throws IllegalNumberOfPlayers, IllegalCardDirectionException {
        if (numberOfPlayers < 3 || numberOfPlayers > 6) {
            throw new IllegalNumberOfPlayers("You must have between 3-5 players.\n");
        }
        this.numberOfPlayers = numberOfPlayers;
        this.players = new ArrayList<>();
        for (int i = 0; i < this.numberOfPlayers; i++) {
            this.players.add(new Player(new Species()));
        }

        this.drawPile = new Deck<>();
        for (int i = 0; i < 50; i++) {
            drawPile.add(new Card("Carnivore",
                    "Makes a species a carnivore", "./carnivore.jpg", 3, 0));
        }
        this.discardPile = new Deck<>();
    }

    /**
     * Returns the number of players in the Game instance
     *
     * @return numberOfPlayers
     */
    public int getNumPlayers() {
        return this.numberOfPlayers;
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
     * @param phase (1-4) of the game
     * Starts the game with Phase 1.
     * Calls PhaseOne.execute()
     */
    public void startGame(IPhases phase) {
        phase.execute();
    }
}
