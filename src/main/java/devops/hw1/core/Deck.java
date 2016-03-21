package devops.hw1.core;

import java.util.Stack;

/**
 * Created by burchtm on 3/21/2016.
 */
public class Deck<T> extends Stack<T> {

    public Deck(){
        super();
    }

    /**
     * Gets the amount of cards in the deck
     * @return The number of cards in the deck
     */
    public int getSize(){
        return this.size();
    }

}
