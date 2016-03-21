package devops.hw1.core;

/**
 * Created by burchtm on 3/21/2016.
 */
public class Card {

    private String name;
    private String desc;
    private String imgPath;
    private int food;

    public Card(String name, String desc, String imgPath, int food){
        this.name = name;
        this.desc = desc;
        this.imgPath = imgPath;
        this.food = food;
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

    public int getFood() {
        return food;
    }
}
