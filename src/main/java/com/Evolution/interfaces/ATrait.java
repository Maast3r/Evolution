package com.Evolution.interfaces;

import com.Evolution.exceptions.*;
import com.Evolution.logic.Game;

/**
 * Method signatures for methods common amongst traits
 */
public abstract class ATrait {

    protected final Game game;

    public ATrait(Game game) {
        this.game = game;
    }

    public abstract void executeTrait(int[] playerIndex, int[] speciesIndex) throws IllegalSpeciesIndexException, InvalidPlayerSelectException, SpeciesFullException, WateringHoleEmptyException, NullGameObjectException, IllegalCardFoodException, IllegalCardDirectionException, IllegalCardDiscardException, IllegalCardRemovalException, SpeciesPopulationException;

    public final Game getGame() {
        return this.game;
    }

}
