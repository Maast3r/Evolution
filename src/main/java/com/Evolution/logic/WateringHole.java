package com.Evolution.logic;

import com.Evolution.exceptions.WateringHoleEmptyException;
import com.Evolution.interfaces.ICard;
import com.Evolution.interfaces.IWateringHole;

import java.util.ArrayList;

/**
 * Manages the watering hole
 * Created by burchtm on 3/21/2016.
 */
public class WateringHole implements IWateringHole {

    private int foodCount = 0;
    private ArrayList<ICard> cards = new ArrayList<>();

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
    public void removeFood(int i) throws WateringHoleEmptyException {
        if(i > this.foodCount || i < 0){
            throw new WateringHoleEmptyException("WateringHole is empty.");
        }
        this.foodCount -= i;
    }

    @Override
    public ArrayList<ICard> getCards() {
        return this.cards;
    }

    @Override
    public void addCard(ICard card){
        this.cards.add(card);
    }

    @Override
    public void removeCards() {
        this.cards = new ArrayList<>();
    }


    public int getCardFoodCount() {
        int count = 0;
        System.out.println(this.cards.size() + " ----");
        for(ICard c : this.cards) {
            count += c.getFood();
        }
        return count;
    }
}
