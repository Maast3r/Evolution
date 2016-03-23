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

    @Override
    public int getBodySize() {
        return this.bodySize;
    }

    @Override
    public int getPopulation() {
        return this.population;
    }

    @Override
    public void increasePopulation() throws SpeciesPopulationException {
        if(this.population == 6) {
            throw new SpeciesPopulationException("Your species population is 6.\n");
        }
        this.population++;
    }

    @Override
    public void decreasePopulation() throws SpeciesPopulationException {
        if(this.population == 0){
            throw new SpeciesPopulationException("Your species population is 0.\n");
        }
        this.population--;
    }

    @Override
    public void increaseBodySize() throws SpeciesBodySizeException {
        if(this.bodySize == 6){
            throw new SpeciesBodySizeException("Your species body size is 6.\n");
        }
        this.bodySize++;
    }

    @Override
    public void decreaseBodySize() {
        this.bodySize--;
    }

    @Override
    public boolean isDead() {
        return this.population == 0 || this.bodySize == 0;
    }
}
