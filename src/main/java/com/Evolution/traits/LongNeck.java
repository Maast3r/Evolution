package com.Evolution.traits;


import com.Evolution.exceptions.*;
import com.Evolution.abstracts.ATrait;
import com.Evolution.logic.Game;

/**
 * Created by goistjt on 4/27/2016.
 */
public class LongNeck extends ATrait {

    public LongNeck(Game game) {
        super(game);
    }

    /**
     * Makes the species executing this action feed a second time from the food bank
     *
     * @param playerIndex  [0]: Player applying action
     *                     [1]: Player being affected
     * @param speciesIndex [0]: Species applying action
     *                     [1]: Species being affected
     * @throws IllegalSpeciesIndexException propagated from {@link Game#feedPlayerSpeciesFromBank(int, int)}
     * @throws InvalidPlayerSelectException propagated from {@link Game#feedPlayerSpeciesFromBank(int, int)}
     * @throws SpeciesFullException         propagated from {@link Game#feedPlayerSpeciesFromBank(int, int)}
     * @throws WateringHoleEmptyException   propagated from {@link Game#feedPlayerSpeciesFromBank(int, int)}
     * @throws FoodBankEmptyException       propagated from {@link Game#feedPlayerSpeciesFromBank(int, int)}
     */
    public void executeTrait(int[] playerIndex, int[] speciesIndex) throws IllegalSpeciesIndexException,
            InvalidPlayerSelectException, SpeciesFullException, WateringHoleEmptyException,FoodBankEmptyException {
        this.game.feedPlayerSpeciesFromBank(playerIndex[0], speciesIndex[0]);
    }
}
