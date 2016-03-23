package devops.hw1.core;

/**
 * Manages the watering hole
 * Created by burchtm on 3/21/2016.
 */
public class WateringHole implements IWateringHole {

    int foodCount = 0;

    @Override
    public int getFoodCount() {
        return this.foodCount;
    }
}
