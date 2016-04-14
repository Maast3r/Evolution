package com.Evolution.testClasses;

import com.Evolution.interfaces.ISpecies;

/**
 * Test version of Species that is used for testing
 * when a full impolementation of Species is not needed
 * Created by goistjt on 3/22/2016.
 */
public class TestSpecies implements ISpecies {

    @Override
    public int getBodySize() {
        return 0;
    }

    @Override
    public int getPopulation() {
        return 0;
    }

    @Override
    public void increasePopulation() {

    }

    @Override
    public void increaseBodySize() {

    }

    @Override
    public void decreasePopulation() {

    }

    @Override
    public void decreaseBodySize() {

    }

    @Override
    public boolean isDead() {
        return false;
    }
}
