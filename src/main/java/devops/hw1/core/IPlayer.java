package devops.hw1.core;

import java.util.ArrayList;

/**
 * Created by goistjt on 3/22/2016.
 */
public interface IPlayer {

    /**
     * Add the provided species to the right of the list of species
     * held by the player
     *
     * @param species
     */
    void addSpeciesRight(ISpecies species);

//    /**
//     * Add the provided species to the left of the list of species
//     * held by the player
//     *
//     * @param species
//     */
//    void addSpeciesLeft(ISpecies species);

    /**
     * Returns the list of all species currently held by the player
     * in order left -> right
     *
     * @return speciesList
     */
    ArrayList<ISpecies> getSpecies();

    /**
     * Removes the species at the specified index from the player's
     * species list
     *
     * @param i
     */
    void removeSpecies(int i);
}
