package devops.hw1.core;

import java.util.ArrayList;

/**
 * Created by goistjt on 3/22/2016.
 */
public class Player implements IPlayer {
    private ArrayList<ISpecies> speciesList = new ArrayList<>();

    public Player(ISpecies species) {
        speciesList.add(species);
    }

    public void addSpeciesRight(ISpecies species) {
        this.speciesList.add(species);
    }

    @Override
    public void addSpeciesLeft(ISpecies species) {
        this.speciesList.add(0, species);
    }

    public ArrayList<ISpecies> getSpecies() {
        return this.speciesList;
    }

    public void removeSpecies(int i) throws InvalidPlayerSpeciesRemovalException {
        if(speciesList.size() == 0 ||
                i < 0 || i >= speciesList.size()){
            throw new InvalidPlayerSpeciesRemovalException("Remova Species index is wrong.\n");
        } else {
            this.speciesList.remove(i);
        }
    }
}
