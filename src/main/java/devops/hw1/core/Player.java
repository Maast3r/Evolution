package devops.hw1.core;

import java.util.ArrayList;

/**
 * Created by goistjt on 3/22/2016.
 */
public class Player {
    public void addSpecies() {
    }

    public ArrayList<ISpecies> getSpecies() {
        ArrayList<ISpecies> speciesList = new ArrayList<>();
        speciesList.add(new TestSpecies());
        return speciesList;
    }
}
