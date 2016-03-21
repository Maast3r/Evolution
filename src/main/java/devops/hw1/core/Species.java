package devops.hw1.core;

/**
 * Created by goistjt on 3/21/2016.
 */
public class Species {
    private int bodySize;
    private int population;

    private boolean popIncrease = false;

    public Species() {
        bodySize = 1;
        population = 1;
    }

    /**
     * Returns the current body size of the species
     * @return bodySize
     */
    public int getBodySize() {
        return 1;
    }

    /**
     * Returns the current population of the species
     * @return population
     */
    public int getPopulation() {
        return popIncrease ? 2 : 1;
    }

    public void increasePopulation() {
        popIncrease = true;
    }
}
