package com.Evolution.interfaces;

/**
 * Interface for decks
 * Created by burchtm on 3/23/2016.
 */
public interface IDeck<T> {
    /**
     * Gets the amount of cards in the deck
     * @return The number of cards in the deck
     */
    int getSize();

    /**
     * Draws from the deck
     * @return The instance of the object stored in the deck
     */
    T draw();

    /**
     * Discards an object onto the deck
     */
    void discard(T object);

    /**
     * Shuffles the contents of the deck into a new order
     */
    void shuffle();
}
