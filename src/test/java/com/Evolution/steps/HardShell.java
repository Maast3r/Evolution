package com.Evolution.steps;

import com.Evolution.logic.Card;
import cucumber.api.PendingException;
import cucumber.api.java.en.And;

/**
 * Created by brownba1 on 5/11/2016.
 */
public class HardShell {

    private final Burrowing b;

    public HardShell(Burrowing b) {
        this.b = b;
    }

    @And("^the Species is initially holding Hard Shell$")
    public void theSpeciesIsInitiallyHoldingHardShell() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        b.s.addTrait(new Card("Hard Shell", "", "", 0, 0));
    }
}
