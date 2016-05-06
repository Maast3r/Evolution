package com.Evolution.steps;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;

import static org.junit.Assert.assertEquals;

/**
 * Created by goistjt on 5/5/2016.
 */
public class Phase4EndFix {

    private final Phase3CardFoodToWH phc;

    public Phase4EndFix(Phase3CardFoodToWH phc) {
        this.phc = phc;
    }

    @And("^it is player three's turn$")
    public void itIsPlayerThreeSTurn() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        while (this.phc.g.getTurn() != 3) {
            this.phc.g.nextTurn();
        }
        assertEquals(3, this.phc.g.getTurn());
    }

    @Then("^it is the first player's turn$")
    public void itIsTheFirstPlayerSTurn() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        assertEquals(this.phc.g.getFirstPlayer(), this.phc.g.getTurn());
    }
}
