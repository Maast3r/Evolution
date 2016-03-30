package com.Evolution.logic;

import com.Evolution.exceptions.IllegalCardDirectionException;
import com.Evolution.exceptions.WrongFileException;
import com.Evolution.interfaces.ICard;
import com.Evolution.interfaces.IDeck;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 * Contains methods to generate an empty Deck or a filled Deck given an InputStream
 */
public class DeckFactory {

    /**
     * Parses a string and converts it into a new Card
     * @param input string containing card information
     * @return Card
     * @throws IllegalCardDirectionException
     * @throws IOException
     */
    public ICard readLineToCard(String input) throws IllegalCardDirectionException, IOException, WrongFileException {
        String pattern = "^.{0,150};.{0,150};.{0,150}jpg;[0-9]+;[0-9]+$";
        if(!input.matches(pattern)){
            throw new WrongFileException("You are reading from a bad file.");
        }
        String[] cardParams = input.split(";");
        return new Card(cardParams[0], cardParams[1], cardParams[2], Integer.parseInt(cardParams[3]),
                Integer.parseInt(cardParams[4]));
    }

    /**
     * Read an InputStream and convert it to an ArrayList of ICard line by line
     * @param input input stream containing strings of card info
     * @return ArrayList<ICard>
     * @throws IllegalCardDirectionException
     * @throws IOException
     */
    public ArrayList<ICard> readFile(InputStream input) throws IllegalCardDirectionException,
            IOException, WrongFileException {
        ArrayList<ICard> cards = new ArrayList<>();
        BufferedReader br = new BufferedReader(new InputStreamReader(input));

        String strLine;

        while ((strLine = br.readLine()) != null){
            cards.add(readLineToCard(strLine));
        }

        return cards;
    }

    /**
     * Constructs a Deck filled with cards to be used during gameplay
     * @param s input stream containing strings of card info
     * @return IDeck<ICard>
     * @throws IOException
     * @throws IllegalCardDirectionException
     */
    public IDeck<ICard> generateDrawPile(InputStream s) throws IOException, IllegalCardDirectionException, WrongFileException {
        Deck<ICard> drawPile = new Deck<>();
        drawPile.addAll(readFile(s));
        return drawPile;
    }

    /**
     * Constructs an empty Deck to be used as a discard pile during gameplay
     * @return empty IDeck<ICard>
     */
    public IDeck<ICard> generateDiscardPile() {
        return new Deck<>();
    }
}
