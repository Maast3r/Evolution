package com.Evolution.logic;

import com.Evolution.exceptions.IllegalCardDirectionException;
import com.Evolution.interfaces.ICard;

import java.io.ByteArrayInputStream;

/**
 * Created by goistjt on 3/28/2016.
 */
public class DeckFactory {

    public ICard readLineToCard(ByteArrayInputStream input) throws IllegalCardDirectionException {
        return new Card("Carnivore", "Makes a species a carnivore", "./carnivore.jpg", 3, 0);
    }
}
