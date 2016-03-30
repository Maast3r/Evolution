package com.Evolution.logic;

import com.Evolution.exceptions.WateringHoleEmptyException;
import com.Evolution.interfaces.IWateringHole;

/**
 * Manages the watering hole
 * Created by burchtm on 3/21/2016.
 */
public class WateringHole implements IWateringHole {

    private int foodCount = 0;

    @Override
    public int getFoodCount() {
        return this.foodCount;
    }

    @Override
    public void addFood() {
        this.foodCount++;
    }

    @Override
    public void addFood(int i) {
        this.foodCount += i;
    }

    @Override
    public void removeFood() throws WateringHoleEmptyException {
        if(this.foodCount == 0){
            throw new WateringHoleEmptyException("WateringHole is empty.");
        }
        this.foodCount--;
    }

    @Override
    public void removeFood(int i) {
        this.foodCount -= i;
    }
}
