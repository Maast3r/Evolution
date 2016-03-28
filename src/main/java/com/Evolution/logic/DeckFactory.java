package com.Evolution.logic;

import com.Evolution.exceptions.IllegalCardDirectionException;
import com.Evolution.interfaces.ICard;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

public class DeckFactory {

    public ICard readLineToCard(InputStream input) throws IllegalCardDirectionException, IOException {
        int n = input.available();
        byte[] bytes = new byte[n];
        input.read(bytes, 0, n);
        String s = new String(bytes, StandardCharsets.UTF_8);
        String[] cardParams = s.split(";");
        return new Card(cardParams[0], cardParams[1], cardParams[2], Integer.parseInt(cardParams[3]),
                Integer.parseInt(cardParams[4]));
    }

    public ArrayList<ICard> readFile(String s) throws IllegalCardDirectionException {
        ArrayList<ICard> cards = new ArrayList<>();
        cards.add(new Card("Random", "Does random stuff", "./random.jpg", 2, 1));
        return cards;
    }
}
