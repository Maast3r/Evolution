package devops.hw1.core;

/**
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
     */
    void increasePopulation() throws SpeciesPopulationException;

    /**
     * Increases the body size of the species up to a maximum of 6
     */
    void increaseBodySize() throws SpeciesBodySizeException;

    /**
     * Decreases the population size of the species down to a minimum of 0
     */
    void decreasePopulation() throws SpeciesPopulationException;

    /**
     * Decreases the body size of the species down to a minimum of 0
     */
    void decreaseBodySize() throws SpeciesBodySizeException;

    /**
     * Returns whether or not this species has at least 1 population and body size
     *
     * @return isDead
     */
    boolean isDead();

}
