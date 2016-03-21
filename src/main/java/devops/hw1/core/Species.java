package devops.hw1.core;

/**
 * Created by goistjt on 3/21/2016.
 */
public class Species {
    private int bodySize;
    private int population;

    boolean isDecreased = false;

    public Species() {
        bodySize = 1;
        population = 1;
    }

    /**
     * Returns the current body size of the species
     *
     * @return bodySize
     */
    public int getBodySize() {
        return 1;
    }

    /**
     * Returns the current population of the species
     *
     * @return population
     */
    public int getPopulation() {
        return isDecreased ? 0 : population;
    }

    /**
     * Increases the population size of the species up to a maximum of 6
     * Needs Error Handling
     */
    public void increasePopulation() {
        population++;
    }

    /**
     * Decreases the population size of the species down to a minimum of 0
     * Needs Error Handling
     */
    public void decreasePopulation() {
        isDecreased = true;
    }
}
