package com.Evolution.logic;

import com.Evolution.exceptions.IllegalCardDirectionException;
import com.Evolution.interfaces.IPhases;
import com.Evolution.interfaces.IPlayer;

import java.awt.*;
import java.util.ArrayList;

public class PhaseOne implements IPhases{
    private Game game;

    public PhaseOne(Game game) {
        this.game = game;
    }

    @Override
    public void execute() throws IllegalCardDirectionException {
        this.game.drawForPlayers();
        this.game.setPhase(new PhaseTwo(game));
    }

}
