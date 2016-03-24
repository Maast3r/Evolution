package devops.hw1.core;

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
    public void removeFood() {
        this.foodCount--;
    }

    @Override
    public void removeFood(int i) {
        this.foodCount -= i;
    }
}
