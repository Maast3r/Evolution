package devops.hw1.core;

/**
 * Created by burchtm on 3/21/2016.
 */
public class Card {

    String name;
    String desc;

    public Card(String name, String desc){
        this.name = name;
        this.desc = desc;
    }

    /**
     * Returns the card's name
     * @return The card's name
     */
    public String getName(){
        return name;
    }

    /**
     * Returns the card's description
     * @return The card's description
     */
    public String getDesc() {
        return desc;
    }
}
