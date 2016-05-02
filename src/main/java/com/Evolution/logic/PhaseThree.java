package com.Evolution.logic;

import com.Evolution.exceptions.*;
import com.Evolution.interfaces.ICard;
import com.Evolution.interfaces.IPhases;

/**
 * Logic class for all logic for the third phase of the game
 */
public class PhaseThree implements IPhases{
    private Game game;

    public PhaseThree(Game g){
        this.game = g;
    }

    @Override
    public void execute() throws IllegalCardDirectionException, DeckEmptyException, InvalidPlayerSelectException, NullGameObjectException, InvalidWateringHoleCardCountException {
        this.game.nextTurn();
        if(this.game.getTurn() == 1){
            this.game.getWateringHole().addTotalCardFood();
            for(ICard c : this.game.getWateringHole().getCards()) {
                this.game.getDiscardPile().discard(c);
            }
            this.game.getWateringHole().removeCards();
            this.game.setPhase(new PhaseFour(this.game));
        }
    }

    @Override
    public String getName() {
        return "Play Cards";
    }

    @Override
    public int getNumber() {
        return 3;
    }
}
