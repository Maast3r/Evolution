package com.Evolution.steps;

import com.Evolution.exceptions.*;
import com.Evolution.interfaces.IPlayer;
import com.Evolution.logic.*;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.easymock.EasyMock;
import org.junit.Assert;

import java.util.ArrayList;


public class Phase3CardFoodToWH {

    static ArrayList<IPlayer> generateNumPlayers(int num) {
        ArrayList<IPlayer> players = new ArrayList<>();
        for (int i = 0; i < num; i++) {
            players.add(EasyMock.niceMock(Player.class));
        }
        return players;
    }

    protected Game g = null;

    @Given("^I have a new Game with (\\d+) players$")
    public void iHaveANewGame(int numPlayers) throws IllegalNumberOfPlayers, NullGameObjectException {
        this.g = new Game(generateNumPlayers(numPlayers), new WateringHole(), EasyMock.niceMock(Deck.class), new Deck<>());
    }

    @And("^it is currently Phase3$")
    public void itIsCurrentlyPhase() throws NullGameObjectException {
        this.g.setPhase(new PhaseThree(this.g));
    }

    @And("^I have an empty Watering Hole$")
    public void iHaveAnEmptyWateringHole() {
        Assert.assertTrue(this.g.getWateringHole().getFoodCount() == 0);
    }

    @And("^there are Cards on the Watering Hole$")
    public void thereAreCardsOnTheWateringHole() throws NullGameObjectException, IllegalCardFoodException, IllegalCardDirectionException, InvalidAddToWateringHoleException {
        for (int i = 0; i < this.g.getPlayerObjects().size(); i++) {
            this.g.getWateringHole().addCard(new Card("", "", "", i, 0));
        }
    }

    @When("^Phase3 ends$")
    public void phaseEnds() throws DeckEmptyException, IllegalCardDirectionException, NullGameObjectException,
            InvalidPlayerSelectException, InvalidWateringHoleCardCountException, FoodBankEmptyException {
        for (int i = 0; i < this.g.getPlayerObjects().size(); i++) {
            this.g.getPhase().execute();
        }
    }

    @Then("^the food on the Cards shall be added to the Watering Hole$")
    public void theFoodOnTheCardsShallBeAddedToTheWateringHole() {
        int food = 0;
        for (int i = 0; i < this.g.getPlayerObjects().size(); i++) {
            food = food + i;
        }
        Assert.assertTrue(this.g.getWateringHole().getFoodCount() == food);
    }

    @And("^the Cards shall be discarded$")
    public void theCardsShallBeDiscarded() {
        Assert.assertTrue(this.g.getDiscardPile().getSize() == this.g.getPlayerObjects().size());
    }
}
