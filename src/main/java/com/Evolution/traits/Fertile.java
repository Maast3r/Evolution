package com.Evolution.traits;

import com.Evolution.exceptions.*;
import com.Evolution.interfaces.ATrait;
import com.Evolution.logic.Game;


public class Fertile extends ATrait {
    public Fertile(Game g) {
        super(g);
    }

    @Override
    public void executeTrait(int[] playerIndex, int[] speciesIndex) throws IllegalSpeciesIndexException, InvalidPlayerSelectException, SpeciesFullException, WateringHoleEmptyException, NullGameObjectException, IllegalCardFoodException, IllegalCardDirectionException, IllegalCardDiscardException, IllegalCardRemovalException {

    }
}
