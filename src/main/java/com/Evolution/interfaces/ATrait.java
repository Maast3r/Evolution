package com.Evolution.interfaces;

import com.Evolution.exceptions.IllegalSpeciesIndexException;
import com.Evolution.exceptions.InvalidPlayerSelectException;
import com.Evolution.exceptions.SpeciesFullException;
import com.Evolution.exceptions.WateringHoleEmptyException;
import com.Evolution.logic.Game;

/**
 * Method signatures for methods common amongst traits
 */
public abstract class ATrait {

    protected final Game game;

    public ATrait(Game game) {
        this.game = game;
    }

    public abstract void executeTrait(int[] playerIndex, int[] speciesIndex) throws IllegalSpeciesIndexException, InvalidPlayerSelectException, SpeciesFullException, WateringHoleEmptyException;

    public final Game getGame() {
        return this.game;
    }

}
