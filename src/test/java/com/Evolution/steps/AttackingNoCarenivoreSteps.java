package com.Evolution.steps;

import cucumber.api.PendingException;
import cucumber.api.java.en.Then;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.Assert.assertArrayEquals;


public class AttackingNoCarenivoreSteps {
    private AttackableSpecies as;

    public AttackingNoCarenivoreSteps(AttackableSpecies as){
        this.as = as;
    }
    @Then("^you see no attackable species$")
    public void youSeeNoAttackableSpecies() throws Throwable {
        assertArrayEquals(new Object[]{}, this.as.attackables.toArray());
    }
}