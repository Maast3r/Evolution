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

    /**
     * Eats an extra food from the watering hole
     * @param playerIndex  [0]: Player applying action
     *                     [1]: Player being affected
     * @param speciesIndex [0]: Species applying action
     *                     [1]: Species being affected
     * @throws IllegalSpeciesIndexException propagated from {@link Game#feedPlayerSpecies(int, int)}
     * @throws InvalidPlayerSelectException propagated from {@link Game#feedPlayerSpecies(int, int)}
     * @throws SpeciesFullException         propagated from {@link Game#feedPlayerSpecies(int, int)}
     * @throws WateringHoleEmptyException   propagated from {@link Game#feedPlayerSpecies(int, int)}
     */
    @Override
    public void executeTrait(int[] playerIndex, int[] speciesIndex)
            throws IllegalSpeciesIndexException, InvalidPlayerSelectException,
            SpeciesFullException, WateringHoleEmptyException {
        if(playerIndex[0] > 4 || playerIndex[0] < 0) {
            throw new InvalidPlayerSelectException("Invalid trait player select.");
        }
        this.game.feedPlayerSpecies(playerIndex[0], speciesIndex[0]);
    }

}
