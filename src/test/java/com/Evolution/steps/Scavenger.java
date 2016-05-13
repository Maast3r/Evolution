package com.Evolution.steps;

import com.Evolution.logic.Card;
import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by brownba1 on 5/11/2016.
 */
public class Scavenger {
    private final ForagingSteps f;

    public Scavenger(ForagingSteps f) {
        this.f = f;
    }

    @And("^player (\\d+) has species (\\d+) which is a carnivore$")
    public void playerAHasSpeciesBWhichIsACarnivore(int a, int b) throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        this.f.realGame.getPlayerObjects().get(a).getSpecies().get(b).addTrait(new Card("Carnivore", "", "", 0, 0));
        assertTrue(this.f.realGame.getPlayerObjects().get(a).getSpecies().get(b).getTraits().parallelStream().
                anyMatch(c -> c.getName().equals("Carnivore")));
    }

    @And("^player (\\d+)'s species (\\d+) has an initial body size of (\\d+)$")
    public void playerASSpeciesBHasAnInitialBodySizeOfBody_size(int player, int species, int size) throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        while (this.f.realGame.getPlayerObjects().get(player).getSpecies().get(species).getBodySize() < size) {
            this.f.realGame.getPlayerObjects().get(player).getSpecies().get(species).increaseBodySize();
        }
        assertEquals(size, this.f.realGame.getPlayerObjects().get(player).getSpecies().get(species).getBodySize());
    }

    @And("^player (\\d+) species (\\d+) is a scavenger$")
    public void playerCSpeciesDIsAScavenger(int player, int species) throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        this.f.realGame.getPlayerObjects().get(player).getSpecies().get(species).addTrait(new Card("Scavenger", "", "", 0, 0));
        assertTrue(this.f.realGame.getPlayerObjects().get(player).getSpecies().get(species).getTraits().parallelStream().
                anyMatch(c -> c.getName().equals("Scavenger")));
    }

    @When("^player (\\d+) species (\\d+) attacks player (\\d+) species (\\d+)$")
    public void playerASpeciesBAttacksPlayerESpeciesF(int player, int species, int player2, int species2) throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        this.f.realGame.attackSpecies(player, species, player2, species2);
        assertEquals(1, this.f.realGame.getPlayerObjects().get(player).getSpecies().get(species).getEatenFood());
    }

    @Then("^player (\\d+) species (\\d+) eats from the food bank$")
    public void playerCSpeciesDEatsFromTheFoodBank(int player, int species) throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        assertEquals(1, this.f.realGame.getPlayerObjects().get(player).getSpecies().get(species).getEatenFood());
    }
}
