package com.Evolution.steps;

import com.Evolution.logic.Card;
import cucumber.api.PendingException;
import cucumber.api.java.en.And;

public class SymbiosisSteps {
    private ForagingSteps fs;

    public SymbiosisSteps(ForagingSteps fs){
        this.fs = fs;
    }

    @And("^there is another species that has Symbiosis$")
    public void thereIsAnotherSpeciesThatHasSymbiosis() throws Throwable {
        this.fs.realGame.getPlayerObjects().get(1).getSpecies().get(0)
                .addTrait(new Card("Symbiosis", "", "", 0 , 0));
    }

    @And("^all other species except the symbiosis left have a body size (\\d+)$")
    public void allOtherSpeciesExceptTheSymbiosisLeftHaveABodySize(int arg0) throws Throwable {
        for(int i=0; i< this.fs.realGame.getPlayerObjects().size(); i++){
            for(int j=0; j <this.fs.realGame.getPlayerObjects().get(i).getSpecies().size(); j++){
                if(i == 1 && j == 0) {
                }else {
                    for(int z=0; z< 5; z++){
                        this.fs.realGame.getPlayerObjects().get(i).getSpecies().get(j).increaseBodySize();
                    }
                }
            }
        }
    }
}
