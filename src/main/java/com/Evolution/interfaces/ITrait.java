package com.Evolution.interfaces;

import com.Evolution.logic.Game;

/**
 * Method signatures for methods common amongst traits
 */
public interface ITrait {

    /**
     * Applies action to affected species on feed/attack/phase shift
     *
     * @param playerIndex  [0]: Player applying action
     *                     [1]: Player being affected
     * @param speciesIndex [0]: Species applying action
     *                     [1]: Species being affected
     */
    void executeTrait(int[] playerIndex, int[] speciesIndex);

    Game getGame();

}
