package com.Evolution.traits;

import com.Evolution.exceptions.*;
import com.Evolution.abstracts.ATrait;
import com.Evolution.logic.Game;


public class Fertile extends ATrait {
    public Fertile(Game g) {
        super(g);
    }

    /**
     * Increases population by 1 before cards are revealed
     *
     * @param playerIndex  [0]: Player applying action
     *                     [1]: Player being affected
     * @param speciesIndex [0]: Species applying action
     *                     [1]: Species being affected
     * @throws SpeciesPopulationException propagated from {@link Game#increasePopulation(int, int)}
     */
    @Override
    public void executeTrait(int[] playerIndex, int[] speciesIndex)
            throws SpeciesPopulationException {
        this.game.increasePopulation(playerIndex[0], speciesIndex[0]);
    }
}
