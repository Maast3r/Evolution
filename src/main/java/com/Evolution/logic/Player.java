package com.Evolution.logic;

import com.Evolution.exceptions.IllegalCardRemovalException;
import com.Evolution.exceptions.InvalidPlayerSpeciesRemovalException;
import com.Evolution.exceptions.NullGameObjectException;
import com.Evolution.interfaces.ICard;
import com.Evolution.interfaces.IPlayer;
import com.Evolution.interfaces.ISpecies;

import java.util.ArrayList;

/**
 * Logic class handling all logic for Players of the game
 * Created by goistjt on 3/22/2016.
 */
public class Player implements IPlayer {
    private ArrayList<ISpecies> speciesList = new ArrayList<>();
    private ArrayList<ICard> cardList = new ArrayList<>();

    public Player(ISpecies species) {
        speciesList.add(species);
    }

    public void addSpeciesRight(ISpecies species) {
        this.speciesList.add(species);
    }

    @Override
    public void addSpeciesLeft(ISpecies species) {
        this.speciesList.add(0, species);
    }

    public ArrayList<ISpecies> getSpecies() {
        return this.speciesList;
    }

    public void removeSpecies(int i) throws InvalidPlayerSpeciesRemovalException {
        if(speciesList.size() == 0 ||
                i < 0 || i >= speciesList.size()){
            throw new InvalidPlayerSpeciesRemovalException("Removal Species index is wrong.\n");
        } else {
            this.speciesList.remove(i);
        }
    }

    public ArrayList<ICard> getCards() {
        return cardList;
    }

    @Override
    public void addCardToHand(ICard card) throws NullGameObjectException {
        if(card == null) {
            throw new NullGameObjectException("Unable o add a null Card to player hand");
        }
        cardList.add(card);
    }

    @Override
    public void removeCardFromHand(ICard card) throws IllegalCardRemovalException {
        if(!cardList.remove(card)){
            throw new IllegalCardRemovalException("Card not contained in hand!");
        }
    }
}
