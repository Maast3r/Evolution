package com.Evolution.gui;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.util.ArrayList;

/**
 * Created by brownba1 on 3/28/2016.
 * Class to create our version of an HBox so that children are
 * stored and easily accessible
 */
public class MyHBox extends HBox {

    private HBox playerPane;
    private Label foodLabel;
    private ImageView firstPlayerMarker;
    private ArrayList<VBox> playerSpecies = new ArrayList<>();

    /**
     * Constructor for our HBox that stores children
     */
    public MyHBox() {
        this.playerPane = new HBox();
    }

    /**
     * Set up the default screen with player info and single species
     * Places the first player marker on player one to start the game
     *
     * @param playerNum the number of the player
     * @return the pane created for this player
     */
    public HBox createBox(int playerNum) {
        this.firstPlayerMarker = new ImageView("/images/empty.png");
        if (playerNum == 1) {
            this.firstPlayerMarker = new ImageView("/images/first_player_marker.png");
        }
        this.foodLabel = new Label("Food bag: " + 0);
        Label playerNumLabel = new Label("Player " + playerNum);

        VBox playerInfo = new VBox();
        playerInfo.setAlignment(Pos.CENTER);
        playerInfo.getChildren().addAll(this.firstPlayerMarker, playerNumLabel, foodLabel);

        SpeciesBoard speciesBoard = new SpeciesBoard();
        VBox speciesPane = speciesBoard.createSpeciesBoard();
        this.playerSpecies.add(speciesPane);
        this.playerPane.getChildren().addAll(playerInfo, speciesPane);
        return this.playerPane;
    }

    /**
     * Gets the amount of food in this player's food bag
     * @return amount of food
     */
    public int getFoodAmount() {
        String[] text = this.foodLabel.getText().split(": ");
        return Integer.parseInt(text[1]);
    }

    /**
     * Sets the amount of food in this players food bag to the foodAmt
     * @param foodAmt
     */
    public void setFoodLabel(int foodAmt) {
        this.foodLabel.setText("Food Bag: " + foodAmt);
    }

    /**
     * Sets the first player marker image to be empty if this player
     * is not the first player for this round, or to the first player
     * marker if they are
     * @param firstPlayer true if this is the first player for this round, flase otherwise
     */
    public void setFirstPlayerMarker(boolean firstPlayer) {
        if (firstPlayer) {
            this.firstPlayerMarker.setImage(new Image("/images/first_player_marker.png"));
        } else {
            this.firstPlayerMarker.setImage(new Image("/images/empty.png"));
        }
    }

    /**
     * Get the list of species boards for this player
     * @return ArrayList of species boards
     */
    public ArrayList<VBox> getPlayerSpecies() {
        return this.playerSpecies;
    }

    /**
     * Adds a species to this player on the given side
     * @param side "left" or "right" - the side to add the new species to
     */
    public void addSpecies(String side) {
        SpeciesBoard speciesBoard = new SpeciesBoard();
        VBox speciesPane = speciesBoard.createSpeciesBoard();
        if (side.equals("left")) {
            this.playerSpecies.add(0, speciesPane);
            this.playerPane.getChildren().add(1, speciesPane);
        } else {
            this.playerSpecies.add(speciesPane);
            this.playerPane.getChildren().add(speciesPane);
        }
    }
}
