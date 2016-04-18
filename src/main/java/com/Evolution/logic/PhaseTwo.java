package com.Evolution.logic;

import com.Evolution.interfaces.IPhases;


/**
 * Logic class for all logic for the second phase of the game
 */
public class PhaseTwo implements IPhases {
    private Game game;

    public PhaseTwo(Game g){
        this.game = g;
    }

    @Override
    public void execute() {
        game.nextTurn();
        if(game.getWateringHole().getCards().size() == game.getPlayerObjects().size()) {
            game.setPhase(new PhaseThree(this.game));
        }
    }

    @Override
    public String getName() {
        return "Discard to Watering Hole";
    }

    @Override
    public int getNumber() {
        return 2;
    }
}
