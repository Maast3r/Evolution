package com.Evolution.steps;

import com.Evolution.logic.Card;
import com.Evolution.traits.Cooperation;
import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import org.junit.Assert;


public class CooperationSteps {

    public ForagingSteps fs;

    public CooperationSteps(ForagingSteps fs){
        this.fs = fs;
    }

    @And("^there is a species that has Cooperation$")
    public void thereIsASpeciesThatHasCooperation() throws Throwable {
        this.fs.realGame.getPlayerObjects().get(0).getSpecies().get(0)
                .addTrait(new Card("Cooperation", "", "", 0, 0));
    }

    @Then("^the species on the right eaten food should increase by (\\d+)$")
    public void theSpeciesOnTheRightEatenFoodShouldIncreaseBy(int arg0) throws Throwable {
        Assert.assertEquals(1, this.fs.realGame.getPlayerObjects().get(0).getSpecies()
            .get(1).getEatenFood());
    }
}
