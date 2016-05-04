package com.Evolution.steps;

import com.Evolution.logic.Card;
import com.Evolution.logic.Species;
import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.Assert;

/**
 * Created by burchtm on 5/4/2016.
 */
public class Attacking {


    private Phase3CardFoodToWH phc = null;

    public Attacking(Phase3CardFoodToWH phc) {
        this.phc = phc;
    }

    @And("^player (\\d+)'s species (\\d+) initially has a population of (\\d+)$")
    public void playerPlayer_under_attackSSpeciesSpecies_under_attackHasAPopulationOfFinal_player__population(int playerIndex, int speciesIndex, int population) throws Throwable {
        for(int i = 0; i < population; i++) {
            this.phc.g.getPlayerObjects().get(playerIndex).getSpecies().get(speciesIndex).increasePopulation();
        }
    }

    @And("^player (\\d+)'s species (\\d+) initially has a body size of (\\d+)$")
    public void playerPlayer_attackingSSpeciesSpecies_attackingHasABodySizeOfPlayer_attacking_body_size(int playerIndex, int speciesIndex, int bodySize) throws Throwable {
        for(int i = 0; i < bodySize; i++) {
            this.phc.g.getPlayerObjects().get(playerIndex).getSpecies().get(speciesIndex).increaseBodySize();
        }
    }

    @And("^player (\\d+)'s species (\\d+) is a carnivore$")
    public void playerPlayer_attackingSSpeciesSpecies_attackingIsACarnivore(int playerIndex, int speciesIndex) throws Throwable {
        this.phc.g.getPlayerObjects().get(playerIndex).getSpecies().get(speciesIndex).addTrait(new Card("Carnivore", "","", 0, 0));
    }

    @When("^player (\\d+)'s species (\\d+) attacks player (\\d+)'s species (\\d+)$")
    public void playerPlayer_attackingSSpeciesSpecies_attackingAttacksPlayerPlayer_under_attackSSpeciesSpecies_under_attack(int playerIndex1, int speciesIndex1, int playerIndex2, int speciesIndex2) throws Throwable {
        this.phc.g.attackSpecies(playerIndex1, speciesIndex1, playerIndex2, speciesIndex2);
    }

    @Then("^player (\\d+)'s species (\\d+) now has a food count of (\\d+)$")
    public void playerPlayer_attackingSSpeciesSpecies_attackingHasAFoodCountOfPlayer_attacking_body_size(int playerIndex, int speciesIndex, int foodCount) throws Throwable {
        Assert.assertTrue(this.phc.g.getPlayerObjects().get(playerIndex).getSpecies().get(speciesIndex).getEatenFood() == foodCount);
    }

    @And("^player (\\d+) has (\\d+) species$")
    public void playerPlayer_attackingHasNum_player__speciesSpecies(int playerIndex, int numSpecies) throws Throwable {
        for(int i = 0; i < numSpecies; i++){
            this.phc.g.getPlayerObjects().get(playerIndex).addSpeciesLeft(new Species());
        }
    }

    @And("^player (\\d+)'s species (\\d+) now has a population of (\\d+)$")
    public void playerPlayer_under_attackSSpeciesSpecies_under_attackNowHasAPopulationOfFinal_player__population(int playerIndex, int speciesIndex, int population) throws Throwable {
        Assert.assertTrue(this.phc.g.getPlayerObjects().get(playerIndex).getSpecies().get(speciesIndex).getPopulation() == population);
    }
}
