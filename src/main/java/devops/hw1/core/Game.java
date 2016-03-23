package devops.hw1.core;

/**
 * Created by goistjt on 3/23/2016.
 */
public class Game {
    private int round = 1;
    private int numberOfPlayers;

    public Game(int numberOfPlayers) throws IllegalNumberOfPlayers {
        if(numberOfPlayers < 3 || numberOfPlayers > 6){
            throw new IllegalNumberOfPlayers("You must have between 3-5 players.\n");
        }
        this.numberOfPlayers = numberOfPlayers;
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

}
