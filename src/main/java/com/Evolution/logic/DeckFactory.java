package com.Evolution.logic;

import com.Evolution.exceptions.IllegalCardDirectionException;
import com.Evolution.interfaces.ICard;
import com.Evolution.interfaces.IDeck;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

public class DeckFactory {

    public ICard readLineToCard(String input) throws IllegalCardDirectionException, IOException {
        String[] cardParams = input.split(";");
        return new Card(cardParams[0], cardParams[1], cardParams[2], Integer.parseInt(cardParams[3]),
                Integer.parseInt(cardParams[4]));
    }

    public ArrayList<ICard> readFile(InputStream input) throws IllegalCardDirectionException,
            IOException {
        ArrayList<ICard> cards = new ArrayList<>();
        BufferedReader br = new BufferedReader(new InputStreamReader(input));

        String strLine;

        while ((strLine = br.readLine()) != null){
            cards.add(readLineToCard(strLine));
        }

        return cards;
    }

    public IDeck<ICard> generateDrawPile(InputStream s) throws IOException, IllegalCardDirectionException {
        Deck<ICard> drawPile = new Deck<>();
        drawPile.addAll(readFile(s));
        return drawPile;
    }

    public IDeck<ICard> generateDiscardPile() {
        return new Deck<>();
    }
}
