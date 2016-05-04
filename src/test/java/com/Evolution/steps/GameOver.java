package com.Evolution.steps;

import com.Evolution.exceptions.IllegalNumberOfPlayers;
import com.Evolution.exceptions.NullGameObjectException;
import com.Evolution.interfaces.IPlayer;
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

    public GameOver() throws IllegalNumberOfPlayers, NullGameObjectException {
        ArrayList<IPlayer> players = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            players.add(new Player(new Species()));
        }
        this.g = new Game(players, new WateringHole(), new Deck<>(), EasyMock.niceMock
                (Deck.class));
    }

    @Given("^I have a new Game with (\\d+) players with a real Draw Deck$")
    public void iHaveANewGameWithPlayersWithARealDrawDeck(int arg0) throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        ArrayList<IPlayer> players = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            players.add(new Player(EasyMock.niceMock(Species.class)));
        }
        g = new Game(players, new WateringHole(), new Deck<>(), EasyMock.niceMock
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
        this.g.feedPlayerSpecies(0, 0);
    }

    @Then("^the Watering Hole now has zero food$")
    public void theWateringHoleNowHasZeroFood() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        assertEquals(0, this.g.getWateringHole().getFoodCount());
    }
}
