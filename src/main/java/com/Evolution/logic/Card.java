package com.Evolution.logic;

import com.Evolution.exceptions.IllegalCardDirectionException;
import com.Evolution.interfaces.ICard;

/**
 * Class for handling gameplay cards
 * Created by burchtm on 3/21/2016.
 */
public class Card implements ICard {

    private String name;
    private String desc;
    private String imgPath;
    private int food;
    private int direction;

    public Card(String name, String desc, String imgPath, 
    		int food, int direction) throws IllegalCardDirectionException {
    	if(direction != 0 && direction != 1 && direction != 2){
    		throw new IllegalCardDirectionException("The direction is not 0, 1, or 2.\n");
    	}
        this.name = name;
        this.desc = desc;
        this.imgPath = imgPath;
        this.food = food;
        this.direction = direction;
    }


    @Override
    public String getName(){
        return name;
    }


    @Override
    public String getDesc() {
        return desc;
    }


    @Override
    public String getImgPath() {
        return imgPath;
    }

    @Override
    public int getFood() {
        return food;
    }

    @Override
    public int getDirection() {
        return direction;
    }
}
