package com.Evolution.traits;

import com.Evolution.exceptions.IllegalSpeciesIndexException;
import com.Evolution.exceptions.InvalidPlayerSelectException;
import com.Evolution.exceptions.SpeciesFullException;
import com.Evolution.exceptions.WateringHoleEmptyException;
import com.Evolution.interfaces.ATrait;
import com.Evolution.logic.Game;


public class Foraging extends ATrait {
    public Foraging(Game g) {
        super(g);
    }

    @Override
    public void executeTrait(int[] playerIndex, int[] speciesIndex)
            throws IllegalSpeciesIndexException, InvalidPlayerSelectException,
            SpeciesFullException, WateringHoleEmptyException {
        if(playerIndex[0] == 5) {
            throw new InvalidPlayerSelectException("Invalid trait player select.");
        }
        this.game.feedPlayerSpecies(playerIndex[0], speciesIndex[0]);
    }

}
