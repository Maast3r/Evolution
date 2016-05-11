package com.Evolution.steps;

import com.Evolution.logic.Card;
import com.Evolution.logic.Species;
import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import static org.junit.Assert.assertTrue;

/**
 * Created by brownba1 on 5/11/2016.
 */
public class Burrowing {
    Species s = null;
    @Given("^I have a new Species$")
    public void iHaveANewSpecies() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        s = new Species();
    }

    @And("^the Species is initially holding Burrowing$")
    public void theSpeciesIsInitiallyHoldingBurrowing() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        s.addTrait(new Card("Burrowing", "", "", 1, 0));
        assertTrue(s.getTraits().stream().anyMatch(c->c.getName().equals("Burrowing")));
    }

    int size;
    @When("^I get the Species body size$")
    public void iGetTheSpeciesBodySize() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        size = s.getBodySize();
    }

    @Then("^the Species body size is at least (\\d+)$")
    public void theSpeciesBodySizeIsAtLeast(int arg0) throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        assertTrue(size >= arg0);
    }
}
