package com.Evolution.logic;

import com.Evolution.exceptions.IllegalCardDirectionException;
import com.Evolution.interfaces.ICard;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

public class DeckFactory {

    public ICard readLineToCard(String input) throws IllegalCardDirectionException, IOException {
        String[] cardParams = input.split(";");
        return new Card(cardParams[0], cardParams[1], cardParams[2], Integer.parseInt(cardParams[3]),
                Integer.parseInt(cardParams[4]));
    }

    public ArrayList<ICard> readFile(String s) throws IllegalCardDirectionException,
            IOException {
        ArrayList<ICard> cards = new ArrayList<>();

        FileInputStream fstream = new FileInputStream(s);
        BufferedReader br = new BufferedReader(new InputStreamReader(fstream));

        String strLine;

//        while ((strLine = br.readLine()) != null){
//            cards.add(readLineToCard(strLine));
//        }
        strLine = br.readLine();
        cards.add(readLineToCard(strLine));

        return cards;
    }
}
