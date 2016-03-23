package devops.hw1.core;

import java.util.ArrayList;

/**
 * Created by goistjt on 3/23/2016.
 */
public class Game {
    private int round = 1;
    private int numberOfPlayers;
    private ArrayList<Player> players;

    public Game(int numberOfPlayers) throws IllegalNumberOfPlayers {
        if(numberOfPlayers < 3 || numberOfPlayers > 6){
            throw new IllegalNumberOfPlayers("You must have between 3-5 players.\n");
        }
        this.numberOfPlayers = numberOfPlayers;
        this.players = new ArrayList<>();
        for(int i=0; i<this.numberOfPlayers; i++){
            this.players.add(new Player(new Species()));
        }
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

    public ArrayList<Player> getPlayerObjects() {
        return this.players;
    }

    public Deck getDrawPile(){
        Deck drawPile = new Deck();
        for(int i=0; i<50; i++){
            drawPile.add(new Card("Carnivore", "Makes a species a carnivore", "./carnivore.jpg", 3, 0));
        }
        return drawPile;
    }
}
