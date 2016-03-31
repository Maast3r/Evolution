package com.Evolution.logic;

import com.Evolution.exceptions.DeckEmptyException;
import com.Evolution.exceptions.IllegalCardDirectionException;
import com.Evolution.exceptions.InvalidPlayerSelectException;
import com.Evolution.interfaces.IPhases;

/**
 * Logic class handling the logic for the first phase of the game
 */
public class PhaseOne implements IPhases{
    private Game game;

    public PhaseOne(Game game) {
        this.game = game;
    }

    @Override
    public void execute() throws IllegalCardDirectionException, DeckEmptyException, InvalidPlayerSelectException {
        this.game.drawForPlayers();
        this.game.setPhase(new PhaseTwo(game));
    }

}
