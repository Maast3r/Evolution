package devops.hw1.core;

/**
 * Created by goistjt on 3/21/2016.
 */
public class Species implements ISpecies {
    private int bodySize;
    private int population;

    public Species() {
        this.bodySize = 1;
        this.population = 1;
    }


    public int getBodySize() {
        return this.bodySize;
    }


    public int getPopulation() {
        return this.population;
    }


    public void increasePopulation() {
        this.population++;
    }


    public void decreasePopulation() {
        this.population--;
    }


    public void increaseBodySize() {
        this.bodySize++;
    }


    public void decreaseBodySize() {
        this.bodySize--;
    }


    public boolean isDead() {
        return this.population == 0 || this.bodySize == 0;
    }
}
