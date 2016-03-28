package com.Evolution.logic;

import com.Evolution.interfaces.IPhases;
import com.Evolution.interfaces.IPlayer;

import java.awt.*;
import java.util.ArrayList;

/**
 * Created by maas on 3/28/2016.
 */
public class PhaseOne implements IPhases{


    public PhaseOne(ArrayList<IPlayer> players,
                    Deck<Card> drawPile, Deck<Card> discardPile) {
    }

    @Override
    public void execute() {

    }

    public ArrayList<IPlayer> getPlayers() {
        ArrayList<IPlayer> players = new ArrayList();
        players.add(new Player(new Species()));
        return players;
    }

    public Deck<Card> getDrawPile() {
        Deck<Card> drawPile = new Deck();
        return drawPile;
    }

    public Deck<Card> getDiscardPile() {
        Deck<Card> discardPile = new Deck();
        return discardPile;
    }
}
