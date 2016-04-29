package com.Evolution.traits;

import com.Evolution.abstracts.CTrait;
import com.Evolution.exceptions.*;
import com.Evolution.interfaces.ICard;
import com.Evolution.logic.Game;


public class FatTissue extends CTrait {
    public FatTissue(Game g) {
        super(g);
    }

    @Override
    public void executeTrait(int[] playerIndex, int[] speciesIndex, ICard c)
            throws IllegalSpeciesIndexException, InvalidPlayerSelectException,
            SpeciesFullException, WateringHoleEmptyException,
            NullGameObjectException, IllegalCardFoodException,
            IllegalCardDirectionException, IllegalCardDiscardException,
            IllegalCardRemovalException, SpeciesPopulationException {

        this.game.getPlayerObjects().get(playerIndex[0]).getSpecies()
                .get(speciesIndex[0]).eatTemp();
    }

}
