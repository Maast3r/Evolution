package com.Evolution.steps;

import com.Evolution.exceptions.*;
import com.Evolution.logic.Card;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

/**
 * Created by brownba1 on 5/9/2016.
 */
public class Climbing {
    AttackableSpecies as;
    private ForagingSteps foraging = null;

    public Climbing(AttackableSpecies as, ForagingSteps foraging){
        this.as = as;
        this.foraging = foraging;
    }

    @And("^Player (\\d+) Species (\\d+) can climb$")
    public void playerAttacking_player_indexSpeciesAttacking_species_indexCanClimb(int playerIndex, int speciesIndex)
            throws NullGameObjectException, IllegalCardFoodException, IllegalCardDirectionException,
            SpeciesNumberTraitsException, SpeciesDuplicateTraitException {
        this.foraging.realGame.getPlayerObjects().get(playerIndex).getSpecies().get(speciesIndex).addTrait(new Card("Climbing", "", "", 0, 0));
    }

    @And("^player (\\d+)'s species (\\d+) can climb$")
    public void playerPlayer_under_attackSSpeciesSpecies_under_attackCanClimb(int playerIndex, int speciesIndex)
            throws NullGameObjectException, IllegalCardFoodException, IllegalCardDirectionException,
            SpeciesNumberTraitsException, SpeciesDuplicateTraitException {
        this.foraging.realGame.getPlayerObjects().get(playerIndex).getSpecies().get(speciesIndex).addTrait(new Card("Climbing", "", "", 0, 0));
    }

    public ArrayList<int[]> attackables = null;

    @When("^Player (\\d+)'s Species (\\d+) views the attackable Species$")
    public void playerAttacking_player_indexSpeciesAttacking_species_indexViewsTheAttackableSpecies(int player, int
            species) throws Throwable {
        for(int i = 0; i < 4; i++){
            this.foraging.realGame.getPlayerObjects().get(player).getSpecies().get(species).increaseBodySize();
        }
        attackables = foraging.realGame.getAttackableSpecies(player, species);
    }

    @Then("^you see player (\\d+)'s species (\\d+)$")
    public void youSeePlayerPlayer_under_attackSSpeciesSpecies_under_attack(int uaPlayerIndex, int speciesIndex) {
        // all species except self
        assertEquals(5, this.attackables.toArray().length);
    }
}
