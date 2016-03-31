package com.Evolution.logic;

import com.Evolution.exceptions.IllegalCardDirectionException;
import com.Evolution.interfaces.IPhases;
import com.Evolution.interfaces.IPlayer;

import java.awt.*;
import java.util.ArrayList;

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
    public void execute() throws IllegalCardDirectionException {
        this.players.get(0).addCardToHand(this.drawPile.draw());
        this.players.get(0).addCardToHand(this.drawPile.draw());
        this.players.get(0).addCardToHand(this.drawPile.draw());
        this.players.get(0).addCardToHand(this.drawPile.draw());

    }

    @Override
    public void nextPhase(IPhases nextPhase) throws IllegalCardDirectionException {
        nextPhase.execute();
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
