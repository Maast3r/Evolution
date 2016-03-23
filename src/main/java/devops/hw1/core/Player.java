package devops.hw1.core;

import java.util.ArrayList;

/**
 * Created by goistjt on 3/22/2016.
 */
public class Player implements IPlayer {
    private ArrayList<ISpecies> speciesList = new ArrayList<>();

    public void addSpeciesRight(ISpecies species) {
        this.speciesList.add(species);
    }

    public ArrayList<ISpecies> getSpecies() {
        return this.speciesList;
    }

    public void removeSpecies(int i) {
        this.speciesList.remove(i);
    }
}
