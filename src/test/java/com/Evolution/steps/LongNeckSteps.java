package com.Evolution.steps;

import com.Evolution.logic.Card;
import com.Evolution.logic.PhaseThree;
import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.When;


public class LongNeckSteps {

    private ForagingSteps fs;

    public LongNeckSteps(ForagingSteps fs) {
        this.fs = fs;
    }

    @And("^there is a species that has LongNeck$")
    public void thereIsASpeciesThatHasLongNeck() throws Throwable {
        this.fs.realGame.getPlayerObjects().get(0).getSpecies().get(0)
                .addTrait(new Card("Long Neck", "", "", 0, 0));
    }

    @And("^it is currently real game Phase(\\d+)$")
    public void itIsCurrentlyRealGamePhase(int arg0) throws Throwable {
        this.fs.realGame.setPhase(new PhaseThree(this.fs.realGame));
    }

    @When("^Phase(\\d+) ends in real game$")
    public void phase3EndsInRealGame(int arg0) throws Throwable {
        this.fs.realGame.setPhase(new PhaseThree(this.fs.realGame));
        for(int i = 0; i < this.fs.realGame.getPlayerObjects().size(); i++) {
            this.fs.realGame.getPhase().execute();
        }
    }
}
