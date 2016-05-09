package com.Evolution.steps;

import com.Evolution.logic.Card;
import com.Evolution.traits.Foraging;
import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;


public class ClimbingSteps {
    private ForagingSteps fs;

    public ClimbingSteps(ForagingSteps fs) {
        this.fs = fs;
    }
    @And("^there is a species that has Carnivore$")
    public void thereIsASpeciesThatHasCarnivore() throws Throwable {
        this.fs.realGame.getPlayerObjects().get(0).getSpecies().get(0)
                .addTrait(new Card("Carnivore", "", "", 0 , 0));
    }

    @And("^there is another species that has Climbing$")
    public void thereIsAnotherSpeciesThatHasClimbing() throws Throwable {
        this.fs.realGame.getPlayerObjects().get(1).getSpecies().get(0)
                .addTrait(new Card("Climbing", "", "", 0 , 0));
    }

    @Then("^the carnivore sees no available species to attack$")
    public void theCarnivoreSeesNoAvailableSpeciesToAttack() throws Throwable {
        assertArrayEquals(new Object[]{}, this.fs.realGame.getAttackableSpecies(0, 0).toArray());
    }

    @And("^the carnivore has a body size of (\\d+)$")
    public void theCarnivoreHasABodySizeOf(int arg0) throws Throwable {
        for(int i=0; i< 5; i++){
            this.fs.realGame.getPlayerObjects().get(0).getSpecies().get(0).increaseBodySize();
        }
        assertEquals(6, this.fs.realGame.getPlayerObjects().get(0).getSpecies().get(0).getBodySize());
    }
}
