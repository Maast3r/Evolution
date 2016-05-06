package com.Evolution.steps;

import com.Evolution.interfaces.IPlayer;
import com.Evolution.logic.*;
import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.easymock.EasyMock;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by goistjt on 5/5/2016.
 */
public class AttackableSpecies {
    public Game g = null;

    @Given("^I have a new Game with (\\d+) real players with real Species$")
    public void iHaveANewGameWithNumberRealPlayersWithRealSpecies(int num_players) throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        ArrayList<IPlayer> players = new ArrayList<>();
        for (int i = 0; i < num_players; i++) {
            players.add(new Player(new Species()));
        }
        g = new Game(players, EasyMock.niceMock(WateringHole.class), EasyMock.niceMock(Deck.class), EasyMock.niceMock
                (Deck.class));
    }

    @And("^Player (\\d+) Species (\\d+) is initially holding a Carnivore Card$")
    public void playerAttacking_player_indexSpeciesAttacking_species_indexIsHoldingACarnivoreCard(int player, int
            species) throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        g.getPlayerObjects().get(player).getSpecies().get(species).addTrait(new Card("Carnivore", "May attack and eat " +
                "other species. Can never eat Plant Food.", "carnivore.png", 1, 0));
        assertTrue(g.getPlayerObjects().get(player).getSpecies().get(species).getTraits().stream().anyMatch(c -> c
                .getName().equals("Carnivore")));
    }

    private ArrayList<int[]> attackables = null;

    @When("^Player (\\d+) Species (\\d+) views the attackable Species$")
    public void playerAttacking_player_indexSpeciesAttacking_species_indexViewsTheAttackableSpecies(int player, int
            species) throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        attackables = g.getAttackableSpecies(player, species);
    }

    @Then("^every other valid player_index, species_index will now be shown$")
    public void everyOtherValidPlayer_indexSpecies_indexWillNowBeShown() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        assertArrayEquals(attackables.toArray(), new ArrayList<>(Arrays.asList(new int[]{1, 0}, new int[]{2, 0})).toArray());
    }

    @And("^half of the species other than player (\\d+)'s species (\\d+) have a smaller body size$")
    public void halfOfTheOtherSpeciesHaveASmallerBodySize(int playerIndex, int speciesIndex) throws Throwable {
        int count = 0;
        for(int i = 0; i < this.g.getPlayerObjects().size(); i++){
            for(int j = 0; j < this.g.getPlayerObjects().get(i).getSpecies().size(); j++){
                if(i == playerIndex && j == speciesIndex){
                    continue;
                }
                if(count % 2 == 0){
                    for(int l = 0; l < 4; l++){
                        this.g.getPlayerObjects().get(i).getSpecies().get(j).increaseBodySize();
                    }
                } else {
                    for(int l = 0; l < 2; l++){
                        this.g.getPlayerObjects().get(i).getSpecies().get(j).increaseBodySize();
                    }
                }
                count++;
            }
        }
    }

    @Then("^every other valid player_index, species_index will now be shown if they have a smaller body size$")
    public void everyOtherValidPlayer_indexSpecies_indexWillNowBeShownIfTheyHaveASmallerBodySize() throws Throwable {
        int count = 0;
        for(int i = 0; i < this.g.getPlayerObjects().size(); i++){
            for(int j = 0; j < this.g.getPlayerObjects().get(i).getSpecies().size(); j++){
                count++;
            }
        }
        count = count - 1;
        assertTrue(attackables.size() == count / 2);
    }
}
