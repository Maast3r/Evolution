package com.Evolution.steps;

import com.Evolution.interfaces.ISpecies;
import com.Evolution.logic.*;
import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.easymock.EasyMock;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


public class GameOver {

    private Game g = null;

    @Given("^I have a new Game with (\\d+) players with a real Draw Deck$")
    public void iHaveANewGameWithPlayersWithARealDrawDeck(int arg0) throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        g = new Game(Phase3CardFoodToWH.generateNumPlayers(arg0), new WateringHole(), new Deck<>(), EasyMock.niceMock
                (Deck.class));
    }

    @And("^the Draw Deck initally has one card$")
    public void theDrawDeckInitallyHasOneCard() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        this.g.getDrawPile().discard(EasyMock.niceMock(Card.class));
    }

    @When("^I draw the last card$")
    public void iDrawTheLastCard() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        this.g.dealToPlayer(0);
    }

    @Then("^the Game is now signaled to end$")
    public void theGameIsNowSignaledToEnd() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        assertTrue(this.g.isOver());
    }

    @And("^the Watering Hole initially has one food$")
    public void theWateringHoleInitiallyHasOneFood() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        this.g.getWateringHole().addFood(1);
    }

    @And("^the Food Bank initially has zero food$")
    public void theFoodBankInitiallyHasZeroFood() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        this.g.decrementFoodBank(240);
    }

    @When("^one of my species eats the last food from the Watering Hole$")
    public void oneOfMySpeciesEatsTheLastFoodFromTheWateringHole() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        ISpecies fakeSpecies = EasyMock.niceMock(Species.class);
        EasyMock.expect(this.g.getPlayerObjects().get(0).getSpecies()).times(2).andReturn(new ArrayList<>(Arrays.asList
                (fakeSpecies)));
        fakeSpecies.eat();
        EasyMock.expect(this.g.getPlayerObjects().get(0).getSpecies()).andReturn(new ArrayList<>(Arrays.asList
                (fakeSpecies)));
        EasyMock.expect(fakeSpecies.getTraits()).andReturn(new ArrayList<>());
        EasyMock.replay(this.g.getPlayerObjects().get(0), fakeSpecies);
        this.g.feedPlayerSpecies(0, 0);
        EasyMock.verify(this.g.getPlayerObjects().get(0), fakeSpecies);
    }

    @Then("^the Watering Hole now has zero food$")
    public void theWateringHoleNowHasZeroFood() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        assertEquals(0, this.g.getWateringHole().getFoodCount());
    }
}
