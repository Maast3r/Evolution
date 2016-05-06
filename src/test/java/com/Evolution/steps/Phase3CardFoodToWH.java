package com.Evolution.steps;

import com.Evolution.exceptions.*;
import com.Evolution.interfaces.IPlayer;
import com.Evolution.logic.*;
import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.easymock.EasyMock;
import org.junit.Assert;

import java.util.ArrayList;

/**
 * Created by goistjt on 5/2/2016.
 */
public class Phase3CardFoodToWH {

    public ForagingSteps fs;

    private ArrayList<IPlayer> generateNumPlayers(int num) {
        ArrayList<IPlayer> players = new ArrayList<>();
        for (int i = 0; i < num; i++) {
            players.add(EasyMock.niceMock(Player.class));
        }
        return players;
    }

    public Phase3CardFoodToWH(ForagingSteps fs){
        this.fs = fs;
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
    public void phaseEnds() throws DeckEmptyException, IllegalCardDirectionException,
            NullGameObjectException, InvalidPlayerSelectException,
            InvalidWateringHoleCardCountException, SpeciesPopulationException,
            IllegalSpeciesIndexException, WateringHoleEmptyException, SpeciesFullException,
            FoodBankEmptyException {
        for(int i = 0; i < this.g.getPlayerObjects().size(); i++) {
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

    @And("^I have an empty Watering Hole in real game$")
    public void iHaveAnEmptyWateringHoleInRealGame() throws Throwable {
        Assert.assertEquals(0, this.fs.realGame.getWateringHole().getFoodCount());
    }

    @And("^there are Cards on the Watering Hole in real game$")
    public void thereAreCardsOnTheWateringHoleInRealGame() throws Throwable {
        for(int i = 0; i < this.fs.realGame.getPlayerObjects().size(); i ++){
            this.fs.realGame.getWateringHole().addCard(new Card("", "", "", i, 0));
        }
    }

    @Then("^the food on the Cards shall be added to the Watering Hole in real game$")
    public void theFoodOnTheCardsShallBeAddedToTheWateringHoleInRealGame() throws Throwable {
        int food = 0;
        for(int i = 0; i < this.fs.realGame.getPlayerObjects().size(); i++){
            food += i;
        }
        System.out.println(this.fs.realGame.getWateringHole().getFoodCount() + "\n");
        Assert.assertTrue(this.fs.realGame.getWateringHole().getFoodCount() == food);
    }

    @And("^the Cards shall be discarded in real game$")
    public void theCardsShallBeDiscardedInRealGame() throws Throwable {
        Assert.assertTrue(this.fs.realGame.getDiscardPile().getSize()
                == this.fs.realGame.getPlayerObjects().size());

    }
}
