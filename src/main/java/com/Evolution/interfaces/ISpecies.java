package com.Evolution.interfaces;

import com.Evolution.exceptions.SpeciesBodySizeException;
import com.Evolution.exceptions.SpeciesPopulationException;

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

}
