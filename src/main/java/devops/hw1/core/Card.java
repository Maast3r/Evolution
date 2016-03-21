package devops.hw1.core;

/**
 * Class for handling gameplay cards
 * Created by burchtm on 3/21/2016.
 */
public class Card {

    private String name;
    private String desc;
    private String imgPath;
    private int food;
    private int direction;

    public Card(String name, String desc, String imgPath, int food, int direction){
        this.name = name;
        this.desc = desc;
        this.imgPath = imgPath;
        this.food = food;
        this.direction = direction;
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

    /**
     * Returns the card's image path
     * @return The card's image path
     */
    public String getImgPath() {
        return imgPath;
    }

    /**
     * Returns the amount of food the card is worth
     * @return The card's worth
     */
    public int getFood() {
        return food;
    }

    /**
     * Returns the direction on the board that the card effects
     * 0 - The card effects the species that the card is played on
     * 1 - The card effects the species that is left of the species the card is played on
     * 2 - The card effects the species that is right of the species the card is played on
     * Others - Invalid
     * @return The direction on the board that the card effects
     * Needs Error Handling
     */
    public int getDirection() {
        return direction;
    }
}
