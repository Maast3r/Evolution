package com.Evolution.interfaces;

import com.Evolution.exceptions.SpeciesBodySizeException;
import com.Evolution.exceptions.SpeciesPopulationException;

import java.util.ArrayList;

/**
 * Interface for Species
 * Created by goistjt on 3/22/2016.
 */
public interface ISpecies {

    /**
     * Returns the current body size of the species
     *
     * @return bodySize
     */
    int getBodySize();

    /**
     * Returns the current population of the species
     *
     * @return population
     */
    int getPopulation();

    /**
     * Increases the population size of the species up to a maximum of 6
     *
     * @throws SpeciesPopulationException when the {@link ISpecies#getPopulation()} is already at 6
     */
    void increasePopulation() throws SpeciesPopulationException;

    /**
     * Increases the body size of the species up to a maximum of 6
     *
     * @throws SpeciesBodySizeException when the {@link ISpecies#getBodySize()} is already at 6
     */
    void increaseBodySize() throws SpeciesBodySizeException;

    /**
     * Decreases the population size of the species down to a minimum of 0
     *
     * @throws SpeciesPopulationException when the {@link ISpecies#getPopulation()} is already at 0
     */
    void decreasePopulation() throws SpeciesPopulationException;

    /**
     * Returns whether or not this species has at least 1 population
     *
     * @return isDead
     */
    boolean isDead();

    /**
     * Adds the given card as a trait on the species
     * @param c The card which to add as a trait
     */
    void addTrait(ICard c) throws Exception;

    /**
     * Gets the list of all traits on the species
     * @return The list of traits
     */
    ArrayList<ICard> getTraits();
}
