package com.Evolution.logic;

import com.Evolution.exceptions.DeckEmptyException;
import com.Evolution.exceptions.IllegalCardDirectionException;
import com.Evolution.exceptions.InvalidPlayerSelectException;
import com.Evolution.interfaces.IPhases;

/**
 * Logic class for all logic for the third phase of the game
 */
public class PhaseThree implements IPhases{
    private Game game;

    public PhaseThree(Game g){
        this.game = g;
    }

    @Override
    public void execute() throws IllegalCardDirectionException, DeckEmptyException, InvalidPlayerSelectException {

    }
}