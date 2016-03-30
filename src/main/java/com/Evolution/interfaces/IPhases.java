package com.Evolution.interfaces;

/**
 * Created by maas on 3/28/2016.
 */
public interface IPhases {
    /**
     * Executes a certain phase of the game.
     */
    void execute();

    /**
     * Like a linked list, each phase knows which phase should happen next.
     * This method moves to the next phase of the game.
     *
     * @param nextPhase
     */
    void nextPhase(IPhases nextPhase);
}
