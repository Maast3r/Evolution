package com.Evolution.logic;

import com.Evolution.exceptions.IllegalCardDirectionException;
import com.Evolution.interfaces.ICard;

import java.io.ByteArrayInputStream;
import java.nio.charset.StandardCharsets;

/**
 * Created by goistjt on 3/28/2016.
 */
public class DeckFactory {

    public ICard readLineToCard(ByteArrayInputStream input) throws IllegalCardDirectionException {
        int n = input.available();
        byte[] bytes = new byte[n];
        input.read(bytes, 0, n);
        String s = new String(bytes, StandardCharsets.UTF_8);
        String[] cardParams = s.split(";");
        return new Card(cardParams[0], cardParams[1], cardParams[2], Integer.parseInt(cardParams[3]),
                Integer.parseInt(cardParams[4]));
    }
}
