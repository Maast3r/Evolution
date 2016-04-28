package com.Evolution.traits;

import com.Evolution.exceptions.*;
import com.Evolution.interfaces.ATrait;
import com.Evolution.logic.Game;
import com.Evolution.logic.Card;


public class Intelligence extends ATrait {
    public Intelligence(Game g) {
        super(g);
    }

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
