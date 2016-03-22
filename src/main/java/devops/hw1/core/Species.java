package devops.hw1.core;

/**
 * Created by goistjt on 3/21/2016.
 */
public class Species {
    private int bodySize;
    private int population;

    public Species() {
        this.bodySize = 1;
        this.population = 1;
    }

    /**
     * Returns the current body size of the species
     *
     * @return bodySize
     */
    public int getBodySize() {
        return this.bodySize;
    }

    /**
     * Returns the current population of the species
     *
     * @return population
     */
    public int getPopulation() {
        return this.population;
    }

    /**
     * Increases the population size of the species up to a maximum of 6
     * Needs Error Handling
     */
    public void increasePopulation() {
        this.population++;
    }

    /**
     * Decreases the population size of the species down to a minimum of 0
     * Needs Error Handling
     */
    public void decreasePopulation() {
        this.population--;
    }

    /**
     * Increases the body size of the species up to a maximum of 6
     * Needs Error Handling
     */
    public void increaseBodySize() {
        this.bodySize++;
    }

    /**
     * Decreases the body size of the species down to a minimum of 0
     * Needs Error Handling
     */
    public void decreaseBodySize() {
        this.bodySize--;
    }

    /**
     * Returns whether or not this species has at least 1 population and body size
     *
     * @return isDead
     */
    public boolean isDead() {
        return this.population == 0 || this.bodySize == 0;
    }
}
