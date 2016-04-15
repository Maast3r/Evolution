package com.Evolution.gui;

import com.Evolution.interfaces.ICard;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

/**
 * Controller for the card selection popup
 * Created by brownba1 on 3/27/2016.
 */
class CardPopupController implements Initializable {

    private ArrayList<ICard> hand;
    private int numCards = 0;
    private SpeciesBoard board;
    private boolean addTrait = false;

    @FXML
    private Label infoLabel;
    @FXML
    private GridPane gridPane;

    /**
     * Set the current player hand after initializing the controller
     *
     * @param playerHand the hand that is being shown
     */
    CardPopupController(ArrayList<ICard> playerHand, SpeciesBoard board) {
        this.hand = playerHand;
        this.numCards = this.hand.size();
        this.board = board;
    }

    /**
     * Sets the boolean to true if the action that opened this window was
     * the ADD_TRAIT action
     *
     * @param addTrait boolean value
     */
    void setAddTrait(boolean addTrait) {
        this.addTrait = addTrait;
    }

    /**
     * Initialize the card controller and popup
     *
     * @param fxmlFileLocation fxml file this controller is for (card_popup.fxml)
     * @param resources        resources available in the package
     */
    public void initialize(URL fxmlFileLocation, ResourceBundle resources) {
        assert this.gridPane != null : "fx:id=\"gridPane\" was not injected: check your FXML file 'card_popup.fxml'.";
        assert this.infoLabel != null : "fx:id=\"infoLabel\" was not injected: check your FXML file 'card_popup.fxml'.";

        gridSetup();

        displayCards();
    }

    /**
     * Add the cards in the player's hand to the window
     */
    private void displayCards() {
        int index = 0;
        if (this.hand.size() == 0) {
            this.infoLabel.setText("No cards in your hand");
            return;
        }
        for (ICard card : this.hand) {
            ImageView cardView = new ImageView("/images/card_images/" + (card.getImgPath()));
            Label cardFoodLabel = new Label("Food value: " + card.getFood());
            cardView.setOnMouseClicked(event -> {
                if (event.getButton() == MouseButton.SECONDARY) {
                    this.infoLabel.setText(card.getName() + ": " + card.getDesc());
                } else {
                    this.board.setSelectedCard(card);
                    if (this.addTrait) {
                        // TODO show this box to let player choose
                        ChoiceBox<String> choices = new ChoiceBox<>();
                        choices.setItems(FXCollections.observableArrayList("Trait 1", "Trait 2",
                                "Trait 3"));
                        choices.getSelectionModel().selectedIndexProperty().addListener((observable, oldValue, newValue) -> {
                            this.board.setTraitSelection(newValue.intValue());
                        });
                    }
                    this.gridPane.getScene().getWindow().hide();
                }
            });
            VBox cardPane = new VBox();
            cardPane.setAlignment(Pos.CENTER);
            cardPane.getChildren().addAll(cardView, cardFoodLabel);
            addToGrid(cardPane, index);
            index++;
        }
    }

    /**
     * Adds the provided image to the grid pane at the correct position
     * based on the card index
     *
     * @param cardPane the card to add (with it's food label)
     * @param cardNum the card index within the hand
     */
    private void addToGrid(VBox cardPane, int cardNum) {
        int row = (int) Math.ceil(cardNum / 3);
        int col;
        if (cardNum < 3) {
            col = cardNum;
        } else {
            col = cardNum % 3;
        }
        this.gridPane.add(cardPane, col, row);
    }

    /**
     * Sets up the grid pane with the appropriate number of rows based on the number
     * of cards the player has in their hand
     * Minimum 1 row, 3 columns (these are the default setup, no changes required)
     */
    private void gridSetup() {
        int numRows = 0;
        if (this.numCards > 3) {
            numRows = (int) Math.ceil(this.numCards / 3);
        }
        for (int i = 0; i < numRows - 1; i++) {
            RowConstraints rowConst = new RowConstraints();
            rowConst.setPercentHeight(100.0 / numRows);
            this.gridPane.getRowConstraints().add(rowConst);
        }
    }
}