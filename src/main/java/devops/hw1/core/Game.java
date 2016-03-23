package devops.hw1.core;

import java.util.ArrayList;

/**
 * Created by goistjt on 3/23/2016.
 */
public class Game {
    private int round = 1;
    private ArrayList<IPlayer> players;

    public Game(ArrayList<IPlayer> players) {
        this.players = players;
    }

    public int getRound() {
        return this.round;
    }

    public void increaseRound() {
        this.round++;
    }

    public ArrayList<IPlayer> getPlayers() {
        return this.players;
    }
}
