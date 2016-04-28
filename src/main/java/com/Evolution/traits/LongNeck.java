package com.Evolution.traits;

import com.Evolution.exceptions.IllegalSpeciesIndexException;
import com.Evolution.exceptions.InvalidPlayerSelectException;
import com.Evolution.exceptions.SpeciesFullException;
import com.Evolution.exceptions.WateringHoleEmptyException;
import com.Evolution.interfaces.ATrait;
import com.Evolution.logic.Game;

/**
 * Created by goistjt on 4/27/2016.
 */
public class LongNeck extends ATrait {

    public LongNeck(Game game) {
        super(game);
    }

    /**
     * Makes the species executing this action feed a second time
     *
     * @param playerIndex  [0]: Player applying action
     *                     [1]: Player being affected
     * @param speciesIndex [0]: Species applying action
     *                     [1]: Species being affected
     * @throws IllegalSpeciesIndexException
     * @throws InvalidPlayerSelectException
     * @throws SpeciesFullException
     * @throws WateringHoleEmptyException
     */
    public void executeTrait(int[] playerIndex, int[] speciesIndex) throws IllegalSpeciesIndexException,
            InvalidPlayerSelectException, SpeciesFullException, WateringHoleEmptyException {
        this.game.feedPlayerSpecies(playerIndex[0], speciesIndex[0]);
    }
}
