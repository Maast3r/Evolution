package devops.hw1.core;

/**
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

    void addFood();

    void addFood(int i);
}
