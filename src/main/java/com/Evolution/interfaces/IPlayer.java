package com.Evolution.interfaces;

import com.Evolution.exceptions.InvalidPlayerSpeciesRemovalException;

import java.util.ArrayList;

/**
 * Interface for Players
 * Created by goistjt on 3/22/2016.
 */
public interface IPlayer {

    /**
     * Add the provided species to the right of the list of species
     * held by the player
     *
     * @param species to add
     */
    void addSpeciesRight(ISpecies species);

    /**
     * Add the provided species to the left of the list of species
     * held by the player
     *
     * @param species to add
     */
    void addSpeciesLeft(ISpecies species);

    /**
     * Returns the list of all species currently held by the player
     * in order left -> right
     *
     * @return speciesList
     */
    ArrayList<ISpecies> getSpecies();

    /**
     * Removes the species at the specified index from the player's
     * species list
     *
     * @param i index of species to remove
     * @throws InvalidPlayerSpeciesRemovalException if the index i is not within [0, size)
     *                                              of {@link IPlayer#getSpecies()}
     */
    void removeSpecies(int i) throws InvalidPlayerSpeciesRemovalException;

    /**
     * Returns the list of cards in the player's hand
     *
     * @return The list of cards in the player's hand
     */
    ArrayList<ICard> getCards();

    /**
     * Adds a card to a player's hand
     *
     * @param card The card to add
     */
    void addCardToHand(ICard card);

    /**
     * Removes the given card from the players hand
     *
     * @param card the card to remove
     * @return If the removal was successful
     */
    boolean removeCardFromHand(ICard card);
}
