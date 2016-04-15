package com.Evolution.interfaces;

import com.Evolution.exceptions.DeckEmptyException;
import com.Evolution.exceptions.IllegalCardDirectionException;
import com.Evolution.exceptions.InvalidPlayerSelectException;

/**
 * Interface for Phases
 * Created by maas on 3/28/2016.
 */
public interface IPhases {
    /**
     * Executes a certain phase of the game.
     * Like a linked list, each phase knows which phase should happen next.
     * This method moves to the next phase of the game.
     *
     * @throws IllegalCardDirectionException propagated from
     *                                       {@link com.Evolution.logic.Card#Card(String, String, String, int, int)}
     * @throws DeckEmptyException            propagated from {@link IDeck#draw()}
     * @throws InvalidPlayerSelectException  propagated from {@link com.Evolution.logic.Game#dealToPlayer(int)}
     */
    void execute() throws IllegalCardDirectionException, DeckEmptyException, InvalidPlayerSelectException;

    /**
     * Returns the name of the current phase
     * @return phase name
     */
    String getName();

    /**
     * Returns the number of the phase
     * @return phase number
     */
    int getNumber();

}
