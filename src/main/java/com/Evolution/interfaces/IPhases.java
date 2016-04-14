package com.Evolution.interfaces;

import com.Evolution.exceptions.DeckEmptyException;
import com.Evolution.exceptions.IllegalCardDirectionException;

/**
 * Created by maas on 3/28/2016.
 */
public interface IPhases {
    /**
     * Executes a certain phase of the game.
     * Like a linked list, each phase knows which phase should happen next.
     * This method moves to the next phase of the game.
     */
    void execute() throws IllegalCardDirectionException, DeckEmptyException;

    /**
     * Returns the name of the current phase
     * @return phase name
     */
    String getName();

}
