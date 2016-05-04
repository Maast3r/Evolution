package com.Evolution.steps;

import com.Evolution.interfaces.IPlayer;
import com.Evolution.logic.PhaseFour;
import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.easymock.EasyMock;

import static org.junit.Assert.assertEquals;

/**
 * Created by goistjt on 5/3/2016.
 */
public class FirstPlayerUpdate {

    private Phase3CardFoodToWH phc = null;
    private int initRound;

    public FirstPlayerUpdate(Phase3CardFoodToWH phc) {
        this.phc = phc;
    }

    @And("^it is currently Round (\\d+)$")
    public void itIsCurrentlyRoundRound(int round) throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        this.initRound = round;
        for(int i = 1; i< round;i++) {
            this.phc.g.increaseRound();
        }
        assertEquals(round, this.phc.g.getRound());
    }

    @And("^the First Player is currently Player (\\d+)$")
    public void theFirstPlayerIsCurrentlyPlayerFirst_player_init(int initPlayer) throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        assertEquals(initPlayer, this.phc.g.getFirstPlayer());
    }

    @And("^it is currently Phase4$")
    public void itIsCurrentlyPhase() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        this.phc.g.setPhase(new PhaseFour(this.phc.g));
    }

    @When("^Phase4 ends$")
    public void phaseEnds() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        this.phc.g.getPlayerObjects().forEach(p -> EasyMock.expect(p.allSpeciesFull()).andReturn(true));
        this.phc.g.getPlayerObjects().forEach(EasyMock::replay);
        this.phc.g.getPhase().execute();
        assertEquals(1, this.phc.g.getPhase().getNumber());
        this.phc.g.getPlayerObjects().forEach(EasyMock::verify);
    }

    @Then("^the Round shall increment$")
    public void theRoundShallIncrement() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        assertEquals(initRound + 1, this.phc.g.getRound());
    }

    @And("^the First Player shall be Player (\\d+)$")
    public void theFirstPlayerShallBePlayerFirst_player_result(int resultPlayuer) throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        assertEquals(resultPlayuer, this.phc.g.getFirstPlayer());
    }
}
