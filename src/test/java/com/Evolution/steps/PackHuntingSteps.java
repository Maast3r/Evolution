package com.Evolution.steps;

import com.Evolution.traits.Foraging;
import com.Evolution.traits.WarningCall;
import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.Assert.assertArrayEquals;


public class PackHuntingSteps {
    private ForagingSteps fs;

    public PackHuntingSteps(ForagingSteps fs) {
        this.fs = fs;
    }

    @And("^one species has a body size of (\\d+)$")
    public void oneSpeciesHasABodySizeOf(int arg0) throws Throwable {
        for(int i=0; i< arg0-1; i++){
            this.fs.realGame.getPlayerObjects().get(1).getSpecies().get(0).increaseBodySize();
        }
    }

    @And("^the next has a body size of (\\d+)$")
    public void theNextHasABodySizeOf(int arg0) throws Throwable {
        for(int i=0; i< arg0-1; i++){
            this.fs.realGame.getPlayerObjects().get(2).getSpecies().get(0).increaseBodySize();
        }
    }

    @And("^the carnivore has a body size of (\\d+) and a population of (\\d+)$")
    public void theCarnivoreHasABodySizeOfAndAPopulationOf(int arg0, int arg1) throws Throwable {
        for(int i=0; i< arg0-1; i++){
            this.fs.realGame.getPlayerObjects().get(0).getSpecies().get(0).increaseBodySize();
        }
        for(int i=0; i<arg1-1; i++){
            this.fs.realGame.getPlayerObjects().get(0).getSpecies().get(0).increasePopulation();
        }
    }


    @Then("^the carnivore sees four available species to attack$")
    public void theCarnivoreSeesFourAvailableSpeciesToAttack() throws Throwable {
        assertArrayEquals(new ArrayList<>(Arrays.asList(new int[]{0, 1}, new int[]{1, 0}
                , new int[]{1, 1}, new int[]{2, 1})).toArray(), this.fs.realGame
                .getAttackableSpecies(0, 0).toArray());
    }
}
