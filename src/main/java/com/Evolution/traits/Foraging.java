package com.Evolution.traits;


import com.Evolution.exceptions.*;
import com.Evolution.abstracts.ATrait;
import com.Evolution.logic.Game;


public class Foraging extends ATrait {
    public Foraging(Game g) {
        super(g);
    }

    /**
     * Eats an extra food from the watering hole
     *
     * @param playerIndex  [0]: Player applying action
     *                     [1]: Player being affected
     * @param speciesIndex [0]: Species applying action
     *                     [1]: Species being affected
     * @throws IllegalSpeciesIndexException propagated from {@link Game#feedPlayerSpecies(int, int)}
     * @throws InvalidPlayerSelectException propagated from {@link Game#feedPlayerSpecies(int, int)}
     * @throws SpeciesFullException         propagated from {@link Game#feedPlayerSpecies(int, int)}
     * @throws WateringHoleEmptyException   propagated from {@link Game#feedPlayerSpecies(int, int)}
     * @throws SpeciesPopulationException   propagated from {@link Game#feedPlayerSpecies(int, int)}
     * @throws FoodBankEmptyException       propagated from {@link Game#feedPlayerSpecies(int, int)}
     */
    @Override
    public void executeTrait(int[] playerIndex, int[] speciesIndex)
            throws IllegalSpeciesIndexException, InvalidPlayerSelectException,
            SpeciesFullException, WateringHoleEmptyException, SpeciesPopulationException, FoodBankEmptyException {
        this.game.feedPlayerSpeciesFromBank(playerIndex[0], speciesIndex[0]);
    }

    /**
     * Empty method
     * @param ints
     * @param ints1
     * @return
     */
    @Override
    public boolean canBeAttacked(int[] ints, int[] ints1) {
        return false;
    }

}
