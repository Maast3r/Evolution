package com.Evolution.logic;

import com.Evolution.exceptions.DeckEmptyException;
import com.Evolution.exceptions.IllegalCardDirectionException;
import com.Evolution.exceptions.InvalidPlayerSelectException;
import com.Evolution.interfaces.IPhases;

/**
 * Logic for the fourth phase of the game
 */
public class PhaseFour implements IPhases{
    private Game game;

    public PhaseFour(Game g){
        this.game = g;
    }

    @Override
    public void execute() throws IllegalCardDirectionException, DeckEmptyException, InvalidPlayerSelectException {

    }

    @Override
    public String getName() {
        return "Feeding";
    }

    @Override
    public int getNumber() {
        return 4;
    }
}
