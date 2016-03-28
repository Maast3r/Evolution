package com.Evolution.interfaces;

import com.Evolution.exceptions.InvalidPlayerSpeciesRemovalException;

import java.util.ArrayList;

/**
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
     * @param i index of species to remove
     */
    void removeSpecies(int i) throws InvalidPlayerSpeciesRemovalException;

}