package com.Evolution.steps;

import com.Evolution.logic.Card;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import org.junit.Assert;


public class FertileSteps {
    public ForagingSteps fs;

    public FertileSteps(ForagingSteps fs){
        this.fs = fs;
    }

    @And("^there is a species that has Fertile$")
    public void thereIsASpeciesThatHasFertile() throws Throwable {
        this.fs.realGame.getPlayerObjects().get(0).getSpecies()
                .get(0).addTrait(new Card("Fertile", "", "", 0, 0));
    }

    @Then("^the species population should be (\\d+)$")
    public void theSpeciesPopulationShouldBe(int arg0) throws Throwable {
        Assert.assertEquals(arg0, this.fs.realGame.getPlayerObjects()
            .get(0).getSpecies().get(0).getPopulation());
    }
}
