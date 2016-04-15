package com.Evolution.gui;

import com.Evolution.interfaces.IPlayer;
import com.Evolution.logic.Game;
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
class MyHBox extends HBox {

    private GameScreenController gameScreen;
    private Game game;
    private HBox playerPane;
    private Label foodLabel;
    private ImageView firstPlayerMarker;
    private ArrayList<SpeciesBoard> playerSpeciesBoards = new ArrayList<>();
    private int playerIndex;

    /**
     * Constructor for our HBox that stores children
     */
    MyHBox(int playerIndex, Game g, GameScreenController gameScreen) {
        this.playerPane = new HBox();
        this.playerIndex = playerIndex;
        this.game = g;
        this.gameScreen = gameScreen;
    }

    /**
     * Set up the default screen with player info and single species
     * Places the first player marker on player one to start the game
     *
     * @return the pane created for this player
     */
    HBox createBox() {
        this.firstPlayerMarker = (this.playerIndex == 0) ? new ImageView("/images/first_player_marker.png") :
                new ImageView("/images/empty.png");

        this.foodLabel = new Label("Food bag: " + 0);
        Label playerNumLabel = new Label("Player " + (this.playerIndex + 1));

        VBox playerInfo = new VBox();
        playerInfo.setAlignment(Pos.CENTER);
        playerInfo.getChildren().addAll(this.firstPlayerMarker, playerNumLabel, this.foodLabel);

        SpeciesBoard speciesBoard = new SpeciesBoard(this.playerIndex, 0, this, this.game, this.gameScreen);
        VBox speciesPane = speciesBoard.createSpeciesBoard();
        this.playerSpeciesBoards.add(speciesBoard);

        this.playerPane.getChildren().addAll(playerInfo, speciesPane);
        return this.playerPane;
    }

    /**
     * Gets the amount of food in this player's food bag
     *
     * @return amount of food
     */
    public int getFoodAmount() {
        String[] text = this.foodLabel.getText().split(": ");
        return Integer.parseInt(text[1]);
    }

    /**
     * Sets the amount of food in this players food bag to the foodAmt
     *
     * @param foodAmt
     */
    public void setFoodLabel(int foodAmt) {
        this.foodLabel.setText("Food Bag: " + foodAmt);
    }

    /**
     * Sets the first player marker image to be empty if this player
     * is not the first player for this round, or to the first player
     * marker if they are
     *
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
     *
     * @return ArrayList of species boards
     */
    public ArrayList<SpeciesBoard> getPlayerSpecies() {
        return this.playerSpeciesBoards;
    }

    /**
     * Adds a species to this player on the given side
     *
     * @param side "left" or "right" - the side to add the new species to
     */
    void addSpecies(int side) {
        int numSpecies = this.game.getPlayerObjects().get(this.playerIndex).getSpecies().size();
        if (side == 0) {
            // TODO add species to player through game
            for (int i = 0; i < numSpecies; i++) {
                int oldNum = this.playerSpeciesBoards.get(i).getSpeciesNum();
                this.playerSpeciesBoards.get(i).setSpeciesNum(oldNum + 1);
            }
            SpeciesBoard speciesBoard = new SpeciesBoard(this.playerIndex, 0, this, this.game, this.gameScreen);
            VBox speciesPane = speciesBoard.createSpeciesBoard();
            this.playerSpeciesBoards.add(0, speciesBoard);
            this.playerPane.getChildren().add(1, speciesPane);
        } else {
            // TODO add species to player through game
            SpeciesBoard speciesBoard = new SpeciesBoard(this.playerIndex,
                    this.playerSpeciesBoards.size(), this, this.game, this.gameScreen);
            VBox speciesPane = speciesBoard.createSpeciesBoard();
            this.playerSpeciesBoards.add(speciesBoard);
            this.playerPane.getChildren().add(speciesPane);
        }
    }

    /**
     * Updates the static game objects if this player pane causes any changes
     */
    void updateGameScreen() {
        this.gameScreen.staticElementsUpdate();
    }

    /**
     * De/Activates this player pane's SpeciesBoards' ChoiceBoxes
     *
     * @param active whether or not this player's ChoiceBoxes are enables
     */
    void setChoicesActive(boolean active) {
        for (SpeciesBoard board : this.playerSpeciesBoards) {
            board.setChoiceBoxViewable(active);
        }
    }

    void updateChoices() {
        int phase = this.game.getPhase().getNumber();
        for(SpeciesBoard board : this.playerSpeciesBoards) {
            board.setChoiceBoxPhase(phase);
        }
    }
}
