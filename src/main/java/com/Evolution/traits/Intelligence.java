package com.Evolution.traits;

import com.Evolution.exceptions.*;
import com.Evolution.interfaces.ATrait;
import com.Evolution.logic.Game;
import com.Evolution.logic.Card;


public class Intelligence extends ATrait {
    public Intelligence(Game g) {
        super(g);
    }

    /**
     * Discard Intelligence to eat twice.
     *
     * @param playerIndex  [0]: Player applying action
     *                     [1]: Player being affected
     * @param speciesIndex [0]: Species applying action
     *                     [1]: Species being affected
     * @throws IllegalSpeciesIndexException propagated from {@link Game#feedPlayerSpecies(int, int)}
     * @throws InvalidPlayerSelectException propagated from {@link Game#feedPlayerSpecies(int, int)}
     * @throws SpeciesFullException         propagated from {@link Game#feedPlayerSpecies(int, int)}
     * @throws WateringHoleEmptyException   propagated from {@link Game#feedPlayerSpecies(int, int)}
     */
    @Override
    public void executeTrait(int[] playerIndex, int[] speciesIndex)
            throws IllegalSpeciesIndexException, InvalidPlayerSelectException,
            SpeciesFullException, WateringHoleEmptyException,
            NullGameObjectException, IllegalCardFoodException,
            IllegalCardDirectionException, IllegalCardDiscardException, IllegalCardRemovalException {

        this.game.discardFromPlayer(playerIndex[0], new Card("Carnivore",
                "Makes a species a carnivore", "./carnivore.jpg", 3, 0));
        this.game.feedPlayerSpecies(playerIndex[0], speciesIndex[0]);
        this.game.feedPlayerSpecies(playerIndex[0], speciesIndex[0]);
    }
}
