package com.Evolution.logic;

import com.Evolution.exceptions.SpeciesBodySizeException;
import com.Evolution.exceptions.SpeciesDuplicateTraitException;
import com.Evolution.exceptions.SpeciesNumberTraitsException;
import com.Evolution.exceptions.SpeciesPopulationException;
import com.Evolution.interfaces.ICard;
import com.Evolution.interfaces.ISpecies;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Logic class for handling the logic behind Species during gameplay
 * Created by goistjt on 3/21/2016.
 */
public class Species implements ISpecies {
    private int bodySize;
    private int population;
    private ArrayList<ICard> traits = new ArrayList<>();

    /**
     * Creates a species defaulted with body size and population equal to 0.
     */
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
    public boolean isDead() {
        return this.population == 0;
    }

    @Override
    public void addTrait(ICard c) throws SpeciesNumberTraitsException, SpeciesDuplicateTraitException {
        if(this.traits.size() == 3){
            throw new SpeciesNumberTraitsException("To many traits");
        } else if(this.traits.stream().filter(t -> t.getName().equals(c.getName())).count() > 0){
            throw new SpeciesDuplicateTraitException("Duplicate trait tried to be added");
        }
        this.traits.add(c);
    }

    @Override
    public ArrayList<ICard> getTraits(){
        return this.traits;
    }
}
