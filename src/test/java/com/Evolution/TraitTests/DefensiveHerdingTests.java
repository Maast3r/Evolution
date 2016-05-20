package com.Evolution.TraitTests;

import com.Evolution.exceptions.*;
import com.Evolution.interfaces.IPlayer;
import com.Evolution.logic.*;
import org.easymock.EasyMock;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.Assert.assertTrue;

/**
 * Created by burchtm on 5/16/2016.
 */
public class DefensiveHerdingTests {

    private ArrayList<IPlayer> generateNumRealPlayers(int num) {
        ArrayList<IPlayer> players = new ArrayList<>();
        for (int i = 0; i < num; i++) {
            try {
                players.add(new Player(new Species()));
            } catch (NullGameObjectException e) {
                e.printStackTrace();
            }
        }
        return players;
    }

    @Test
    public void noDefensiveHerdingNotAttackable() throws IllegalNumberOfPlayers, NullGameObjectException, SpeciesBodySizeException, IllegalCardFoodException, IllegalCardDirectionException, SpeciesNumberTraitsException, SpeciesDuplicateTraitException {
        Game g = new Game(generateNumRealPlayers(3), EasyMock.niceMock(WateringHole.class), EasyMock.niceMock(Deck.class), EasyMock.niceMock(Deck.class));
        g.getPlayerObjects().get(0).getSpecies().get(0).increaseBodySize();
        g.getPlayerObjects().get(0).getSpecies().get(0).addTrait(new Card("Carnivore", "", "", 0, 0));
        assertTrue(g.getAttackableSpecies(0, 0).stream().anyMatch(array -> Arrays.equals(array, new int[]{1, 0})));
    }

    @Test
    public void DefensiveHerdingNotAttackable() throws IllegalNumberOfPlayers, NullGameObjectException, SpeciesBodySizeException, IllegalCardFoodException, IllegalCardDirectionException, SpeciesNumberTraitsException, SpeciesDuplicateTraitException {
        Game g = new Game(generateNumRealPlayers(3), EasyMock.niceMock(WateringHole.class), EasyMock.niceMock(Deck.class), EasyMock.niceMock(Deck.class));
        g.getPlayerObjects().get(0).getSpecies().get(0).increaseBodySize();
        g.getPlayerObjects().get(0).getSpecies().get(0).addTrait(new Card("Carnivore", "", "", 0, 0));
        g.getPlayerObjects().get(1).getSpecies().get(0).addTrait(new Card("Defensive Herding", "", "", 0, 0));
        assertTrue(!g.getAttackableSpecies(0, 0).stream().anyMatch(array -> Arrays.equals(array, new int[]{1, 0})));
    }

    @Test
    public void DefensiveHerdingAttackable() throws IllegalNumberOfPlayers, NullGameObjectException, SpeciesBodySizeException, IllegalCardFoodException, IllegalCardDirectionException, SpeciesNumberTraitsException, SpeciesDuplicateTraitException, SpeciesPopulationException {
        Game g = new Game(generateNumRealPlayers(3), EasyMock.niceMock(WateringHole.class), EasyMock.niceMock(Deck.class), EasyMock.niceMock(Deck.class));
        g.getPlayerObjects().get(0).getSpecies().get(0).increaseBodySize();
        g.getPlayerObjects().get(0).getSpecies().get(0).increasePopulation();
        g.getPlayerObjects().get(0).getSpecies().get(0).addTrait(new Card("Carnivore", "", "", 0, 0));
        g.getPlayerObjects().get(1).getSpecies().get(0).addTrait(new Card("Defensive Herding", "", "", 0, 0));
        assertTrue(g.getAttackableSpecies(0, 0).stream().anyMatch(array -> Arrays.equals(array, new int[]{1, 0})));
    }

}
