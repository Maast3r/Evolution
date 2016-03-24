package devops.hw1.core;

import java.util.Collections;
import java.util.Stack;

/**
 * Deck for dealing cards
 * Created by burchtm on 3/21/2016.
 */
public class Deck<T> extends Stack<T> implements IDeck<T> {

    public Deck(){
        super();
    }

    @Override
    public int getSize(){
        return this.size();
    }

    @Override
    public T draw() {
        return this.pop();
    }

    @Override
    public void shuffle() {
        Collections.shuffle(this);
    }
}
