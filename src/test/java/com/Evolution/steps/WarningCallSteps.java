package com.Evolution.steps;

import com.Evolution.logic.Card;
import com.Evolution.traits.Foraging;
import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;


public class WarningCallSteps {
    private ForagingSteps fs;

    public WarningCallSteps(ForagingSteps fs) {
        this.fs = fs;
    }
    @And("^there is a species that has Carnivore$")
    public void thereIsASpeciesThatHasCarnivore() throws Throwable {
        this.fs.realGame.getPlayerObjects().get(0).getSpecies().get(0)
                .addTrait(new Card("Carnivore", "", "", 0 , 0));
    }


    @Then("^the carnivore sees no available species to attack$")
    public void theCarnivoreSeesNoAvailableSpeciesToAttack() throws Throwable {
        assertArrayEquals(new Object[]{}, this.fs.realGame.getAttackableSpecies(0, 0).toArray());
    }

    @And("^there is another species that has Warning Call$")
    public void thereIsAnotherSpeciesThatHasWarningCall() throws Throwable {
        this.fs.realGame.getPlayerObjects().get(1).getSpecies().get(0)
                .addTrait(new Card("Warning Call", "", "", 0 , 0));
    }

    @And("^all other species except the one with Warning Call have a body size (\\d+)$")
    public void allOtherSpeciesExecptTheClimbingHaveABodySize(int arg0) throws Throwable {
        for(int i=0; i< this.fs.realGame.getPlayerObjects().size(); i++){
            for(int j=0; j <this.fs.realGame.getPlayerObjects().get(i).getSpecies().size(); j++){
                if(i != 1 && j != 0) {
                    for(int z=0; z< 5; z++){
                        this.fs.realGame.getPlayerObjects().get(i).getSpecies().get(j).increaseBodySize();
                    }
                }
            }
        }
    }

    @And("^all other species except the second player have a body size (\\d+)$")
    public void allOtherSpeciesExceptTheSecondPlayerHaveABodySize(int arg0) throws Throwable {
        for(int i=0; i< this.fs.realGame.getPlayerObjects().size(); i++){
            for(int j=0; j <this.fs.realGame.getPlayerObjects().get(i).getSpecies().size(); j++){
                if(i != 1) {
                    for(int z=0; z< 5; z++){
                        this.fs.realGame.getPlayerObjects().get(i).getSpecies().get(j).increaseBodySize();
                    }
                }
            }
        }
    }

    @And("^there is a species that has Carnivore and Ambush$")
    public void thereIsASpeciesThatHasCarnivoreAndAmbush() throws Throwable {
        this.fs.realGame.getPlayerObjects().get(0).getSpecies().get(0)
                .addTrait(new Card("Carnivore", "", "", 0 , 0));
        this.fs.realGame.getPlayerObjects().get(0).getSpecies().get(0)
                .addTrait(new Card("Ambush", "", "", 0 , 0));
    }

    @Then("^the carnivore sees (\\d+) available species to attack$")
    public void theCarnivoreSeesAvailableSpeciesToAttack(int arg0) throws Throwable {
        assertArrayEquals(new ArrayList<>(Arrays.asList(new int[]{1, 0}, new int[]{1, 1})).toArray(),
                this.fs.realGame.getAttackableSpecies(0, 0).toArray());
    }
}