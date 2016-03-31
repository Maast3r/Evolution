package com.Evolution.testClasses;

import com.Evolution.interfaces.IWateringHole;

/**
 * Test version of WateringHOle for use in testing
 * when a full implementation of WaterningHole is not needed
 * Created by goistjt on 3/23/2016.
 */
public class TestWateringHole implements IWateringHole {
    @Override
    public int getFoodCount() {
        return 0;
    }

    @Override
    public void addFood() {

    }

    @Override
    public void addFood(int i) {

    }

    @Override
    public void removeFood() {

    }

    @Override
    public void removeFood(int i) {

    }
}
