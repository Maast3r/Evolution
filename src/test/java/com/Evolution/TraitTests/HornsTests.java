package com.Evolution.TraitTests;

import com.Evolution.exceptions.*;
import com.Evolution.interfaces.IPlayer;
import com.Evolution.interfaces.InvalidAttackException;
import com.Evolution.logic.*;
import org.easymock.EasyMock;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.Assert.assertTrue;

/**
 * Created by burchtm on 5/18/2016.
 */
public class HornsTests {

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
    public void noHornsAttack() throws IllegalNumberOfPlayers, NullGameObjectException, SpeciesBodySizeException, IllegalCardFoodException, IllegalCardDirectionException, SpeciesNumberTraitsException, SpeciesDuplicateTraitException, SpeciesPopulationException, SpeciesFullException, BodySizeIllegalAttack, AttackingSelfException, NonCarnivoreAttacking, FoodBankEmptyException, InvalidPlayerSelectException, InvalidAttackException, IllegalSpeciesIndexException {
        Game g = new Game(generateNumRealPlayers(3), EasyMock.niceMock(WateringHole.class), EasyMock.niceMock(Deck.class), EasyMock.niceMock(Deck.class));
        g.getPlayerObjects().get(0).getSpecies().get(0).increaseBodySize();
        g.getPlayerObjects().get(0).getSpecies().get(0).increasePopulation();
        g.getPlayerObjects().get(0).getSpecies().get(0).addTrait(new Card("Carnivore", "", "", 0, 0));
        g.attackSpecies(0, 0, 1, 0);
        assertTrue(g.getPlayerObjects().get(0).getSpecies().get(0).getPopulation() == 2);
    }

    @Test
    public void hornsAttack() throws IllegalNumberOfPlayers, NullGameObjectException, SpeciesBodySizeException, IllegalCardFoodException, IllegalCardDirectionException, SpeciesNumberTraitsException, SpeciesDuplicateTraitException, SpeciesPopulationException, SpeciesFullException, BodySizeIllegalAttack, AttackingSelfException, NonCarnivoreAttacking, FoodBankEmptyException, InvalidPlayerSelectException, InvalidAttackException, IllegalSpeciesIndexException {
        Game g = new Game(generateNumRealPlayers(3), EasyMock.niceMock(WateringHole.class), EasyMock.niceMock(Deck.class), EasyMock.niceMock(Deck.class));
        g.getPlayerObjects().get(0).getSpecies().get(0).increaseBodySize();
        g.getPlayerObjects().get(0).getSpecies().get(0).increasePopulation();
        g.getPlayerObjects().get(0).getSpecies().get(0).addTrait(new Card("Carnivore", "", "", 0, 0));
        g.getPlayerObjects().get(1).getSpecies().get(0).addTrait(new Card("Horns", "", "", 0, 0));
        g.attackSpecies(0, 0, 1, 0);
        assertTrue(g.getPlayerObjects().get(0).getSpecies().get(0).getPopulation() == 1);
    }

    @Test
    public void DefensiveHerdingAttackable() throws IllegalNumberOfPlayers, NullGameObjectException, SpeciesBodySizeException, IllegalCardFoodException, IllegalCardDirectionException, SpeciesNumberTraitsException, SpeciesDuplicateTraitException, SpeciesPopulationException, SpeciesFullException, BodySizeIllegalAttack, AttackingSelfException, NonCarnivoreAttacking, FoodBankEmptyException, InvalidPlayerSelectException, InvalidAttackException, IllegalSpeciesIndexException {
        Game g = new Game(generateNumRealPlayers(3), EasyMock.niceMock(WateringHole.class), EasyMock.niceMock(Deck.class), EasyMock.niceMock(Deck.class));
        g.getPlayerObjects().get(0).getSpecies().get(0).increaseBodySize();
        g.getPlayerObjects().get(0).getSpecies().get(0).increasePopulation();
        g.getPlayerObjects().get(0).getSpecies().get(0).addTrait(new Card("Carnivore", "", "", 0, 0));
        g.getPlayerObjects().get(1).getSpecies().get(0).addTrait(new Card("Defensive Herding", "", "", 0, 0));
        g.getPlayerObjects().get(1).getSpecies().get(0).addTrait(new Card("Horns", "", "", 0, 0));
        g.attackSpecies(0, 0, 1, 0);
        assertTrue(g.getPlayerObjects().get(0).getSpecies().get(0).getPopulation() == 1);
    }
}
