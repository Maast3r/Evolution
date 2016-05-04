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
    public void execute() throws IllegalCardDirectionException, DeckEmptyException, InvalidPlayerSelectException, NullGameObjectException, InvalidWateringHoleCardCountException, FoodBankEmptyException {
        this.game.nextTurn();
        if(this.game.getTurn() == this.game.getFirstPlayer()){
            this.game.moveFoodFromBankToHole(this.game.getWateringHole().getCardFoodCount());
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
