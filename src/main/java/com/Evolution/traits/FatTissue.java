package com.Evolution.traits;

import com.Evolution.abstracts.CTrait;
import com.Evolution.exceptions.*;
import com.Evolution.interfaces.ICard;
import com.Evolution.logic.Game;


public class FatTissue extends CTrait {
    public FatTissue(Game g) {
        super(g);
    }

    /**
     * Stores food up to the body tissue on the species.
     *
     * @param playerIndex
     * @param speciesIndex
     * @param c
     * * @throws IllegalSpeciesIndexException propagated from {@link Game#feedPlayerSpecies(int, int)}
     * @throws InvalidPlayerSelectException propagated from {@link Game#getPlayerObjects()} (int, int)}
     * @throws WateringHoleEmptyException   propagated from {@link Game#feedPlayerSpecies(int, int)}
     * @throws SpeciesPopulationException   propagated from {@link Game#feedPlayerSpecies(int, int)}
     * @throws IllegalSpeciesIndexException propagated from ({@link Game#feedPlayerSpecies(int, int)}
     */
    @Override
    public void executeTrait(int[] playerIndex, int[] speciesIndex, ICard c)
            throws IllegalSpeciesIndexException, InvalidPlayerSelectException,
            WateringHoleEmptyException, SpeciesPopulationException {

        this.game.getPlayerObjects().get(playerIndex[0]).getSpecies()
                .get(speciesIndex[0]).eatTemp();
    }

}
