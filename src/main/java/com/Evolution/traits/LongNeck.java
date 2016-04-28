package com.Evolution.traits;

import com.Evolution.interfaces.ITrait;
import com.Evolution.logic.Game;

/**
 * Created by goistjt on 4/27/2016.
 */
public class LongNeck implements ITrait {
    private Game game;

    public LongNeck(Game game) {
        this.game = game;
    }

    @Override
    public void executeTrait(int[] playerIndex, int[] speciesIndex) {

    }

    @Override
    public Game getGame() {
        return game;
    }
}
