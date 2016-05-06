package com.Evolution.steps;

import com.Evolution.interfaces.ICard;
import com.Evolution.logic.Card;
import com.Evolution.logic.PhaseFour;
import com.Evolution.logic.PhaseTwo;
import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.Assert;

public class ResetDrawPileSteps {
    private ForagingSteps fs;

    public ResetDrawPileSteps(ForagingSteps fs){
        this.fs = fs;
    }

    @And("^it is currently real Phase(\\d+)$")
    public void itIsCurrentlyRealPhase(int arg0) throws Throwable {
        this.fs.realGame.setPhase(new PhaseTwo(this.fs.realGame));
    }

    @And("^the DrawPile has (\\d+) card$")
    public void theDrawPileHasCard(int arg0) throws Throwable {
        this.fs.realGame.getDrawPile().discard(new Card("", "", "", 0 ,0));
    }

    @When("^I draw last card in real game$")
    public void iDrawLastCardInRealGame() throws Throwable {
        this.fs.realGame.dealToPlayer(0);
    }

    @Then("^the discard pile gets reshuffled into the draw pile$")
    public void theDiscardPileGetsReshuffledIntoTheDrawPile() throws Throwable {
        Assert.assertEquals(1, this.fs.realGame.getDrawPile().getSize());
        Assert.assertEquals(0, this.fs.realGame.getDiscardPile().getSize());
    }
}
