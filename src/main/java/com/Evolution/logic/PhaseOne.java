package com.Evolution.logic;

import com.Evolution.interfaces.IPhases;
import com.Evolution.interfaces.IPlayer;

import java.awt.*;
import java.util.ArrayList;

/**
 * Created by maas on 3/28/2016.
 */
public class PhaseOne implements IPhases{
    private ArrayList<IPlayer> players;
    private Deck<Card> drawPile;
    private Deck<Card> discardPile;

    public PhaseOne(ArrayList<IPlayer> players,
                    Deck<Card> drawPile, Deck<Card> discardPile) {
        this.players = players;
        this.drawPile = drawPile;
        this.discardPile = discardPile;
    }

    @Override
    public void execute() {

    }

    public ArrayList<IPlayer> getPlayers() {
        return players;
    }

    public Deck<Card> getDrawPile() {
        return drawPile;
    }

    public Deck<Card> getDiscardPile() {
        return discardPile;
    }
}
