package com.Evolution.logic;

import com.Evolution.exceptions.*;
import com.Evolution.interfaces.*;

import java.util.ArrayList;

/**
 * Game logic controller class. Handles most interaction between related game objects.
 */
public class Game {
    private int round = 1;
    private int turn = 1;
    private ArrayList<IPlayer> players;
    private IDeck<ICard> drawPile;
    private IDeck<ICard> discardPile;
    private IWateringHole wateringHole;
    private int foodBank = 240;
    private IPhases currentPhase = new PhaseOne(this);

    //TODO: Add Null check for every single method that takes in an Object

    /**
     * Evolution Game constructor which contains main logic to interact with players, species, and cards
     *
     * @param players      playing game
     * @param wateringHole food available to species
     * @param drawPile     cards available to draw from
     * @param discardPile  cards that have been discarded
     * @throws IllegalNumberOfPlayers  when an ArrayList is passed in with too many or too few player objects
     * @throws NullGameObjectException if any parameters are null
     */
    public Game(ArrayList<IPlayer> players, IWateringHole wateringHole, IDeck<ICard> drawPile, IDeck<ICard> discardPile)
            throws IllegalNumberOfPlayers, NullGameObjectException {
        if (players == null || wateringHole == null || drawPile == null || discardPile == null) {
            throw new NullGameObjectException("Unable to initialize the game with NULL objects");
        }
        if (players.size() < 3 || players.size() > 5) {
            throw new IllegalNumberOfPlayers("You must have between 3-5 players.\n");
        }
        this.players = players;
        this.wateringHole = wateringHole;

        this.drawPile = drawPile;
        this.discardPile = discardPile;
    }

    /**
     * Returns the current round of the Game
     *
     * @return round
     */
    public int getRound() {
        return this.round;
    }

    /**
     * Increases the current round by one
     */
    public void increaseRound() {
        this.round++;
    }

    /**
     * Returns the list of players in the Game
     *
     * @return players
     */
    public ArrayList<IPlayer> getPlayerObjects() {
        return this.players;
    }

    /**
     * Returns the Deck of Cards corresponding to the draw pile
     *
     * @return drawPile
     */
    public IDeck<ICard> getDrawPile() {
        return this.drawPile;
    }

    /**
     * Returns the Deck of Cards corresponding to the discard pile
     *
     * @return discardPile
     */
    public IDeck<ICard> getDiscardPile() {
        return this.discardPile;
    }

    /**
     * Starts the game with Phase 1.
     * Calls currentPhase.execute()
     *
     * @throws IllegalCardDirectionException propagated from {@link IPhases#execute()}
     * @throws DeckEmptyException            propagated from {@link IPhases#execute()}
     * @throws InvalidPlayerSelectException  propagated from {@link IPhases#execute()}
     * @throws NullGameObjectException       propagated from {@link IPhases#execute()}
     */
    public void startGame() throws IllegalCardDirectionException, DeckEmptyException, InvalidPlayerSelectException, NullGameObjectException {
        this.currentPhase.execute();
    }

    /**
     * The turn that the game is currently on
     *
     * @return The number of the player whose turn it is
     */
    public int getTurn() {
        return this.turn;
    }

    /**
     * Increments which player number's turn it is
     */
    public void nextTurn() {
        if (this.turn == this.players.size()) {
            this.turn = 1;
        } else {
            this.turn++;
        }
    }

    /**
     * Gets the IWateringHole associated with this Game
     *
     * @return IWateringHole
     */
    public IWateringHole getWateringHole() {
        return this.wateringHole;
    }

    /**
     * Returns the amount of food currently available in the bank
     *
     * @return foodBank
     */
    public int getFoodBankCount() {
        return this.foodBank;
    }

    /**
     * Decrements the food bank by one
     *
     * @throws FoodBankEmptyException if the food bank is empty and attempting to decrement
     */
    public void decrementFoodBank() throws FoodBankEmptyException {
        if (this.foodBank == 0) {
            throw new FoodBankEmptyException("The food bank is empty");
        }
        this.foodBank--;
    }

    /**
     * Decrements the food bank by i
     *
     * @param i food
     * @throws FoodBankEmptyException if attempting to remove a negative amount or more food than is in the
     *                                food bank
     */
    public void decrementFoodBank(int i) throws FoodBankEmptyException {
        if (i > this.foodBank || i < 0) {
            throw new FoodBankEmptyException("The argument is larger than the current food bank.");
        }
        this.foodBank -= i;
    }

    /**
     * Decrements the food bank by i and increments the wateringHole food by i
     *
     * @param i food
     * @throws FoodBankEmptyException propagated from {@link #decrementFoodBank(int)}
     */
    public void moveFoodFromBankToHole(int i) throws FoodBankEmptyException {
        decrementFoodBank(i);
        this.wateringHole.addFood(i);
    }

    /**
     * Deal a card from the draw pile to a player
     *
     * @param i the index of the player
     * @throws DeckEmptyException           propagated from {@link Deck#draw()}
     * @throws InvalidPlayerSelectException if the index provided is outside of [0, size) of {@link #getPlayerObjects()}
     * @throws NullGameObjectException      propagated from {@link Player#addCardToHand(ICard)}
     */
    public void dealToPlayer(int i) throws DeckEmptyException, InvalidPlayerSelectException, NullGameObjectException {
        if (i >= this.players.size() || i < 0) {
            throw new InvalidPlayerSelectException("You selected an invalid player to deal to.");
        }
        ICard card = this.drawPile.draw();
        this.players.get(i).addCardToHand(card);
    }

    /**
     * Changes the current phase to phase;
     *
     * @param phase The phase being set
     * @throws NullGameObjectException if the provided phase is null
     */
    public void setPhase(IPhases phase) throws NullGameObjectException {
        if (phase == null) {
            throw new NullGameObjectException("Cannot set the Game to a null Phase");
        }
        this.currentPhase = phase;
    }

    /**
     * Draws the appropriate amount of cards for each player.
     * Appropriate amount = # of species + 3
     *
     * @throws DeckEmptyException           propagated from {@link #dealToPlayer(int)}
     * @throws InvalidPlayerSelectException propagated from {@link #dealToPlayer(int)}
     * @throws NullGameObjectException      propagated from {@link #dealToPlayer(int)}
     */
    public void drawForPlayers() throws DeckEmptyException, InvalidPlayerSelectException, NullGameObjectException {
        for (int i = 0; i < this.players.size(); i++) {
            for (int j = 0; j < this.players.get(i).getSpecies().size() + 3; j++) {
                dealToPlayer(i);
            }
        }
    }

    /**
     * Removes the provided card object from the hand of the player specified by i
     *
     * @param i    player index
     * @param card to remove
     * @throws IllegalCardDiscardException  thrown when the given card is not in the specified
     *                                      player's hand
     * @throws InvalidPlayerSelectException thrown when the given player index is greater than the number of players
     * @throws NullGameObjectException      propagated from {@link Deck#discard(Object)}
     */
    public void discardFromPlayer(int i, ICard card) throws InvalidPlayerSelectException,
            IllegalCardDiscardException, NullGameObjectException, IllegalCardRemovalException {
        if (i > this.players.size() - 1) {
            throw new InvalidPlayerSelectException("The given player index is greater than the number of players.");
        }
        if (!this.players.get(i).getCards().contains(card)) {
            throw new IllegalCardDiscardException("Selected card is not in this players hand.");
        }
        this.players.get(i).removeCardFromHand(card);
        this.discardPile.discard(card);
    }

    /**
     * Returns the game's current phase
     *
     * @return phase
     */
    public IPhases getPhase() {
        return this.currentPhase;
    }

    /**
     * Discards the given card from the player at the given index's hand
     *
     * @param index The index of the player
     * @param card  The card to discard
     * @throws InvalidAddToWateringHoleException     propagated from {@link IWateringHole#addCard(ICard)}
     * @throws InvalidDiscardToWateringHoleException trying to discard to watering hole when it already has the
     *                                               maximum number of cards
     * @throws IllegalCardDiscardException           thrown when the given card is not in the specified
     *                                               player's hand
     * @throws InvalidPlayerSelectException          thrown when the given player index is greater than the number of players
     * @throws IllegalCardRemovalException           propagated from {@link IPlayer#removeCardFromHand(ICard)}
     * @throws NullGameObjectException               propagated from {@link IPlayer#removeCardFromHand(ICard)}
     */
    public void discardToWateringHole(int index, ICard card) throws InvalidDiscardToWateringHoleException,
            InvalidAddToWateringHoleException, InvalidPlayerSelectException, IllegalCardDiscardException, IllegalCardRemovalException, NullGameObjectException {
        if (this.wateringHole.getCards().size() == this.players.size()) {
            throw new InvalidDiscardToWateringHoleException("You can not discard more cards to the watering hole " +
                    "than the number of players.");
        }
        if (index > this.players.size() - 1) {
            throw new InvalidPlayerSelectException("The given player index is greater than the number of players.");
        }
        if (!this.players.get(index).getCards().contains(card)) {
            throw new IllegalCardDiscardException("Selected card is not in this players hand.");
        }
        this.wateringHole.addCard(card);
        this.players.get(index).removeCardFromHand(card);
    }

    /**
     * Increases the population of the species with the given index
     * for the player with the given index
     *
     * @param playerIndex  index of the player
     * @param speciesIndex index of the species
     * @param card         the card to remove from the player's hand
     * @throws SpeciesPopulationException   propagated from {@link Species#increasePopulation()}
     * @throws IllegalCardDiscardException  thrown when the given card is not in the specified
     *                                      player's hand
     * @throws InvalidPlayerSelectException thrown when the given player index is greater than the number of players
     * @throws IllegalSpeciesIndexException thrown when the given species index is greater than the number of species
     *                                      for the given player
     * @throws IllegalCardRemovalException  propagated from {@link IPlayer#removeCardFromHand(ICard)}
     * @throws NullGameObjectException      propagated from {@link IPlayer#removeCardFromHand(ICard)}
     */
    public void increasePopulation(int playerIndex, int speciesIndex, ICard card) throws SpeciesPopulationException,
            IllegalCardDiscardException, InvalidPlayerSelectException,
            IllegalSpeciesIndexException, IllegalCardRemovalException, NullGameObjectException {
        if (playerIndex > this.players.size() - 1) {
            throw new InvalidPlayerSelectException("The given player index is greater than the number of players.");
        }
        if (!this.players.get(playerIndex).getCards().contains(card)) {
            throw new IllegalCardDiscardException("Selected card is not in this player's hand.");
        }
        if (speciesIndex > this.players.get(playerIndex).getSpecies().size() - 1) {
            throw new IllegalSpeciesIndexException("The given species index is greater than the number of species for" +
                    " player " + playerIndex + 1);
        }
        this.players.get(playerIndex).getSpecies().get(speciesIndex).increasePopulation();
        this.discardFromPlayer(playerIndex, card);
    }

    /**
     * Increases the body size of the species with the given index
     * for the player with the given index
     *
     * @param playerIndex  index of the player
     * @param speciesIndex index of the species
     * @param card         the card to remove from the player's hand
     * @throws SpeciesBodySizeException     propagated from {@link Species#increaseBodySize()}
     * @throws IllegalCardDiscardException  thrown when the given card is not in the specified
     *                                      player's hand
     * @throws InvalidPlayerSelectException thrown when the given player index is greater than the number of players
     * @throws IllegalSpeciesIndexException thrown when the given species index is greater than the number of species
     *                                      for the given player
     * @throws NullGameObjectException      propagated from {@link #discardPile}
     */
    public void increaseBodySize(int playerIndex, int speciesIndex, ICard card) throws SpeciesBodySizeException,
            InvalidPlayerSelectException, IllegalCardDiscardException, IllegalSpeciesIndexException, NullGameObjectException, IllegalCardRemovalException {
        if (playerIndex > this.players.size() - 1) {
            throw new InvalidPlayerSelectException("The given player index is greater than the number of players.");
        }
        if (!this.players.get(playerIndex).getCards().contains(card)) {
            throw new IllegalCardDiscardException("Selected card is not in this players hand.");
        }
        if (speciesIndex > this.players.get(playerIndex).getSpecies().size() - 1) {
            throw new IllegalSpeciesIndexException("The given species index is greater than the number of species for" +
                    " player " + playerIndex + 1);
        }
        this.discardFromPlayer(playerIndex, card);
        this.players.get(playerIndex).getSpecies().get(speciesIndex).increaseBodySize();
    }

    /**
     * Adds a given species to the left of a player's Species
     *
     * @param playerIndex position in player list of player to add to
     * @param card        Card from player's hand that is being discarded
     * @param species     Species being added to player
     * @throws InvalidPlayerSelectException thrown when the given player index is greater than the number of players
     * @throws IllegalCardDiscardException  thrown when the given card is not in the specified
     *                                      player's hand
     * @throws NullGameObjectException      propagated from {@link Deck#discard(Object)}
     */
    public void discardForLeftSpecies(int playerIndex, ICard card, ISpecies species) throws
            InvalidPlayerSelectException, IllegalCardDiscardException, NullGameObjectException, IllegalCardRemovalException {
        if(card == null) {
            throw new NullGameObjectException("Unable to discard a null card");
        }
        if(species == null) {
            throw new NullGameObjectException("Unable to add a null species");
        }
        if (playerIndex > this.players.size() - 1) {
            throw new InvalidPlayerSelectException("The given player index is greater than the number of players.");
        }
        if (!this.players.get(playerIndex).getCards().contains(card)) {
            throw new IllegalCardDiscardException("Selected card is not in this players hand.");
        }
        discardFromPlayer(playerIndex, card);
        this.players.get(playerIndex).addSpeciesLeft(species);
    }

    /**
     * Adds a given species to the right of a player's Species
     *
     * @param playerIndex position in player list of player to add to
     * @param card        Card from player's hand that is being discarded
     * @param species     Species being added to player
     * @throws InvalidPlayerSelectException thrown when the given player index is greater than the number of players
     * @throws IllegalCardDiscardException  thrown when the given card is not in the specified
     *                                      player's hand
     * @throws NullGameObjectException      propagated from {@link Deck#discard(Object)}
     */
    public void discardForRightSpecies(int playerIndex, ICard card, ISpecies species) throws
            InvalidPlayerSelectException, IllegalCardDiscardException, NullGameObjectException,
            IllegalCardRemovalException {
        if(card == null) {
            throw new NullGameObjectException("Unable to discard a null card");
        }
        if(species == null) {
            throw new NullGameObjectException("Unable to add a null species");
        }
        if (playerIndex > this.players.size() - 1) {
            throw new InvalidPlayerSelectException("The given player index is greater than the number of players.");
        }
        if (!this.players.get(playerIndex).getCards().contains(card)) {
            throw new IllegalCardDiscardException("Selected card is not in this players hand.");
        }
        discardFromPlayer(playerIndex, card);
        this.players.get(playerIndex).addSpeciesRight(species);
    }

    /**
     * Adds the provided Card to a player's species
     *
     * @param playerIndex  position in player list of player to add to
     * @param speciesIndex position in species list of species to add to
     * @param card         Card being added to species
     * @throws SpeciesNumberTraitsException   propagated from {@link ISpecies#addTrait(ICard)}
     * @throws SpeciesDuplicateTraitException propagated from {@link ISpecies#addTrait(ICard)}
     * @throws InvalidPlayerSelectException   when the provided player index is not in [0, numPlayers)
     * @throws IllegalSpeciesIndexException   when the provided species index is not in [0, numSpecies)
     * @throws NullGameObjectException        when the provided Card is NULL
     * @throws IllegalCardRemovalException    propagated from {@link IPlayer#removeCardFromHand(ICard)}
     */
    public void addTraitToSpecies(int playerIndex, int speciesIndex, ICard card) throws SpeciesNumberTraitsException,
            SpeciesDuplicateTraitException, InvalidPlayerSelectException, IllegalSpeciesIndexException,
            NullGameObjectException, IllegalCardRemovalException {
        if (card == null) {
            throw new NullGameObjectException("The given Card object cannot be NULL");
        }
        if (playerIndex < 0 || playerIndex >= this.players.size()) {
            throw new InvalidPlayerSelectException("The given player index must be within [0,numPlayers)");
        }
        if (speciesIndex < 0 || speciesIndex >= this.players.get(playerIndex).getSpecies().size()) {
            throw new IllegalSpeciesIndexException("The given species index must be within [0, numSpecies)");
        }
        this.players.get(playerIndex).removeCardFromHand(card);
        this.players.get(playerIndex).getSpecies().get(speciesIndex).addTrait(card);

    }

    /**
     * Removes the given trait from the given species from the given player and puts the card on the discard pile
     *
     * @param playerIndex  Index of the player in the player list
     * @param speciesIndex Index of the speices for the player
     * @param traitCard    Card representing the trait to remove
     * @throws SpeciesTraitNotFoundException propagated from {@link ISpecies#removeTrait(ICard)}
     * @throws NullGameObjectException       if the trait card passed in is null
     * @throws InvalidPlayerSelectException  if the player index is not in the valid range
     * @throws IllegalSpeciesIndexException  if the species index is not in the valid range
     */
    public void removeTraitFromSpecies(int playerIndex, int speciesIndex, ICard traitCard) throws
            SpeciesTraitNotFoundException, InvalidPlayerSelectException, IllegalSpeciesIndexException,
            NullGameObjectException {
        if (traitCard == null) {
            throw new NullGameObjectException("Trait card is null!");
        } else if (this.players.size() <= playerIndex || playerIndex < 0) {
            throw new InvalidPlayerSelectException("Player index is out of range!");
        } else if (speciesIndex < 0 || speciesIndex >= this.players.get(playerIndex).getSpecies().size()) {
            throw new IllegalSpeciesIndexException("Species index is out of range!");
        }

        ICard removedCard = this.players.get(playerIndex).getSpecies().get(speciesIndex).removeTrait(traitCard);
        this.getDiscardPile().discard(removedCard);
    }

    /**
     * Returns a list of the traits for a given player's species
     * @param playerIndex the index of the player
     * @param speciesIndex the index of the species
     * @return an ArrayList of trait cards for this species
     * @throws InvalidPlayerSelectException if the player index is not in the valid range
     * @throws IllegalSpeciesIndexException if the species index is not in the valid range
     */
    public ArrayList<ICard> getTraits(int playerIndex, int speciesIndex) throws InvalidPlayerSelectException,
            IllegalSpeciesIndexException {
        if (this.players.size() <= playerIndex || playerIndex < 0) {
            throw new InvalidPlayerSelectException("Player index is out of range!");
        } else if (speciesIndex < 0 || speciesIndex >= this.players.get(playerIndex).getSpecies().size()) {
            throw new IllegalSpeciesIndexException("Species index is out of range!");
        }
        return this.players.get(playerIndex).getSpecies().get(speciesIndex).getTraits();
    }

    /**
     * Feeds the player's species
     *
     * @param playerIndex  Index of the player in the player list
     * @param speciesIndex Index of the species for the player
     * @throws InvalidPlayerSelectException  if the player index is not in the valid range
     * @throws IllegalSpeciesIndexException  if the species index is not in the valid range
     * @throws SpeciesFullException if the species' eaten is equal to species' population
     * @throws WateringHoleEmptyException if the food count in the watering hole is 0
     */
    public void feedPlayerSpecies(int playerIndex, int speciesIndex)
            throws InvalidPlayerSelectException, IllegalSpeciesIndexException,
            SpeciesFullException, WateringHoleEmptyException {
        if (this.players.size() <= playerIndex || playerIndex < 0) {
            throw new InvalidPlayerSelectException("Player index is out of range!");
        } else if (speciesIndex < 0 || speciesIndex >= this.players.get(playerIndex).getSpecies().size()) {
            throw new IllegalSpeciesIndexException("Species index is out of range!");
        } else if (this.wateringHole.getFoodCount() == 0){
            throw new WateringHoleEmptyException("Cannot feed. Watering hole is empty.");
        }

        this.wateringHole.removeFood();
        this.players.get(playerIndex).getSpecies().get(speciesIndex).eat();
    }

    /**
     * Returns the amount of food eaten by the given species of the given player during this round
     * @param playerIndex the player index
     * @param speciesIndex the species index
     * @return the amount of food eaten
     * @throws InvalidPlayerSelectException if the player index is not in the valid range
     * @throws IllegalSpeciesIndexException  if the species index is not in the valid range
     */
    public int getSpeciesFood(int playerIndex, int speciesIndex) throws InvalidPlayerSelectException,
            IllegalSpeciesIndexException {
        if (this.players.size() <= playerIndex || playerIndex < 0) {
            throw new InvalidPlayerSelectException("Player index is out of range!");
        } else if (speciesIndex < 0 || speciesIndex >= this.players.get(playerIndex).getSpecies().size()) {
            throw new IllegalSpeciesIndexException("Species index is out of range!");
        }
        return this.players.get(playerIndex).getSpecies().get(speciesIndex).getEatenFood();
    }

    /**
     * Increases a species population without discarding a card
     * TODO error handling
     * @param playerIndex the player index
     * @param speciesIndex the species index
     * @throws SpeciesPopulationException {@link Species#increasePopulation()}
     */
    public void increasePopulation(int playerIndex, int speciesIndex)
            throws SpeciesPopulationException {
        this.players.get(playerIndex).getSpecies()
                .get(speciesIndex).increasePopulation();
    }

    /**
     * Feeds species from the food bank instead of the watering hold
     * TODO error handling
     * @param playerIndex the player index
     * @param speciesIndex the species index
     * @throws SpeciesFullException propagated from {@link Species#eat()}
     */
    public void feedPlayerSpeciesFromBank(int playerIndex, int speciesIndex)
            throws SpeciesFullException {
        this.foodBank--;
        this.players.get(playerIndex).getSpecies().get(speciesIndex).eat();
    }
}
