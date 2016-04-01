package com.Evolution.interfaces;

import com.Evolution.exceptions.WateringHoleEmptyException;

/**
 * Interface for the WateringHole
 * Created by goistjt on 3/23/2016.
 */
public interface IWateringHole {

    /**
     * Returns the current amount of food available in the
     * watering hole
     *
     * @return size
     */
    int getFoodCount();

    /**
     * Adds a single piece of food to the watering hole
     */
    void addFood();

    /**
     * Adds i pieces of food to the watering hole
     *
     * @param i amount of food to add
     */
    void addFood(int i);

    /**
     * Removes a single piece of food from the watering hole
     *
     * @throws WateringHoleEmptyException when more food is removed from the {@link IWateringHole} than is available
     */
    void removeFood() throws WateringHoleEmptyException;

    /**
     * Removes i pieces of food from the watering hole
     *
     * @throws WateringHoleEmptyException when more food is removed from the {@link IWateringHole} than is available
     */
    void removeFood(int i) throws WateringHoleEmptyException;
}
