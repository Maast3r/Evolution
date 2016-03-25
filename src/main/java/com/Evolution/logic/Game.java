package com.Evolution.logic;

import com.Evolution.exceptions.IllegalCardDirectionException;
import com.Evolution.exceptions.IllegalNumberOfPlayers;
import com.Evolution.interfaces.ICard;
import com.Evolution.interfaces.IPlayer;

import java.util.ArrayList;

/**
 * Created by goistjt on 3/23/2016.
 */
public class Game {
    private int round = 1;
    private int numberOfPlayers;
    private ArrayList<IPlayer> players;
    private Deck<ICard> drawPile;
    private Deck<ICard> discardPile;

    public Game(int numberOfPlayers) throws IllegalNumberOfPlayers, IllegalCardDirectionException {
        if(numberOfPlayers < 3 || numberOfPlayers > 6){
            throw new IllegalNumberOfPlayers("You must have between 3-5 players.\n");
        }
        this.numberOfPlayers = numberOfPlayers;
        this.players = new ArrayList<>();
        for(int i=0; i<this.numberOfPlayers; i++){
            this.players.add(new Player(new Species()));
        }

        this.drawPile = new Deck<ICard>();
        for(int i=0; i<50; i++){
            drawPile.add(new Card("Carnivore",
                    "Makes a species a carnivore", "./carnivore.jpg", 3, 0));
        }
        this.discardPile = new Deck<ICard>();
    }

    public int getNumPlayers(){
        return this.numberOfPlayers;
    }

    public int getRound() {
        return this.round;
    }

    public void increaseRound() {
        this.round++;
    }

    public ArrayList<IPlayer> getPlayerObjects() {
        return this.players;
    }

    public Deck getDrawPile(){
        return this.drawPile;
    }

    public Deck getDiscardPile() {
        return this.discardPile;
    }
}
