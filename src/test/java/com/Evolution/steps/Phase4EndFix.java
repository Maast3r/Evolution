package com.Evolution.steps;

import com.Evolution.exceptions.SpeciesFullException;
import com.Evolution.logic.PhaseFour;
import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by goistjt on 5/5/2016.
 */
public class Phase4EndFix {

    private final Phase3CardFoodToWH phc;
    private final AttackableSpecies as;

    public Phase4EndFix(Phase3CardFoodToWH phc, AttackableSpecies as) {
        this.phc = phc;
        this.as = as;
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

    @And("^each species initially has (\\d+) population$")
    public void eachSpeciesInitiallyHasPopulation(int arg0) throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        this.as.g.getPlayerObjects().forEach(p -> p.getSpecies().forEach(s -> assertEquals(arg0, s.getPopulation())));
    }

    @And("^each species initially has (\\d+) food$")
    public void eachSpeciesInitiallyHasFood(int arg0) throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        this.as.g.getPlayerObjects().forEach(p -> p.getSpecies().forEach(s -> {
            try {
                s.eat();
            } catch (SpeciesFullException e) {
                e.printStackTrace();
            }
        }));
        this.as.g.getPlayerObjects().forEach(p -> p.getSpecies().forEach(s -> assertEquals(arg0, s.getEatenFood())));
    }

    @When("^phase(\\d+) finishes$")
    public void phaseFinishes(int arg0) throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        this.as.g.getPhase().execute();
        assertEquals(1, this.as.g.getPhase().getNumber());
    }

    @Then("^the food on each species moves to its player's food bag$")
    public void theFoodOnEachSpeciesMovesToItsPlayerSFoodBag() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        this.as.g.getPlayerObjects().forEach(p -> p.getSpecies().forEach(s -> assertEquals(0, s.getEatenFood())));
    }

    @And("^each player now has (\\d+) food in their food bag$")
    public void eachPlayerNowHasFoodInTheirFoodBag(int arg0) throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        this.as.g.getPlayerObjects().forEach(p->assertEquals(arg0, p.getFoodBag()));
    }

    @And("^it is initially Phase(\\d+) with real players and species$")
    public void itIsCurrentlyPhaseWithRealPlayersAndSpecies(int arg0) throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        this.as.g.setPhase(new PhaseFour(this.as.g));
    }

    @And("^it is initially player three's turn$")
    public void itIsInitiallyPlayerThreeSTurn() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        while (this.as.g.getTurn() != 3) {
            this.as.g.nextTurn();
        }
        assertEquals(3, this.as.g.getTurn());
    }
}
