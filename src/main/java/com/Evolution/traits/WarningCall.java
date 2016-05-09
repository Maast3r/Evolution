package com.Evolution.traits;

import com.Evolution.abstracts.ATrait;
import com.Evolution.exceptions.*;
import com.Evolution.interfaces.ICard;
import com.Evolution.logic.Game;

import java.util.ArrayList;


public class WarningCall extends ATrait {
    public WarningCall(Game g) {
        super(g);
    }

    @Override
    public void executeTrait(int[] playerIndex, int[] speciesIndex) throws IllegalSpeciesIndexException, InvalidPlayerSelectException, SpeciesFullException, WateringHoleEmptyException, SpeciesPopulationException, FoodBankEmptyException {

    }

    @Override
    public boolean canBeAttacked(int[] players, int[] species) {
        ArrayList<ICard> attackerTraits = this.game.getPlayerObjects().get(players[1]).getSpecies()
                .get(species[1]).getTraits();

        return false;
    }
}
