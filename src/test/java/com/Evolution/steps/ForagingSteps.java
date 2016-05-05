package com.Evolution.steps;

import com.Evolution.exceptions.NullGameObjectException;
import com.Evolution.interfaces.IPlayer;
import com.Evolution.logic.*;
import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import java.util.ArrayList;

import static org.junit.Assert.*;


public class ForagingSteps {

    public Game realGame;

    private ArrayList<IPlayer> generateNumRealPlayers(int num) throws NullGameObjectException {
        ArrayList<IPlayer> players = new ArrayList<>();
        for (int i = 0; i < num; i++) {
            players.add(new Player(new Species()));
            players.get(i).addSpeciesRight(new Species());
        }
        return players;
    }

    @Given("^I have a new Game with (\\d+) real players$")
    public void iHaveANewGameWithNumPlayersRealPlayers(int num) throws Throwable {
        this.realGame = new Game(generateNumRealPlayers(num), new WateringHole(),
                new Deck<>(), new Deck<>());
    }

    @And("^it is currently Phase(\\d+) in real game$")
    public void itIsCurrentlyPhaseInRealGame(int arg0) throws Throwable {
        this.realGame.setPhase(new PhaseFour(this.realGame));
    }

    @And("^there is a species that has Foraging$")
    public void thereIsASpeciesThatHasForaging() throws Throwable {
        this.realGame.getPlayerObjects().get(0).getSpecies()
                .get(0).addTrait(new Card("Foraging", "", "", 0, 0));
    }

    @And("^the species has a population of (\\d+)$")
    public void theSpeciesHasAPopulationOf(int arg0) throws Throwable {
        this.realGame.getPlayerObjects().get(0).getSpecies()
                .get(0).increasePopulation();
        assertEquals(2, this.realGame.getPlayerObjects().get(0).getSpecies()
                .get(0).getPopulation());
    }

    @And("^the wateringhole has (\\d+) food$")
    public void theWateringholeHasFood(int arg0) throws Throwable {
        this.realGame.getWateringHole().addFood(1);
        assertEquals(1, this.realGame.getWateringHole().getFoodCount());
    }

    @When("^the species eats$")
    public void theSpeciesEats() throws Throwable {
        this.realGame.feedPlayerSpecies(0, 0);
    }

    @Then("^the species eaten food should increase by (\\d+)$")
    public void theSpeciesEatenFoodShouldIncreaseBy(int arg0) throws Throwable {
        int expected = arg0;
        assertEquals(expected, this.realGame.getPlayerObjects().get(0).getSpecies()
                .get(0).getEatenFood());
        assertEquals(239, this.realGame.getFoodBankCount());
    }



}
