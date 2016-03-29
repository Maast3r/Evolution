package com.Evolution.gui;

/**
 * Created by brownba1 on 3/27/2016.
 */

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Controller for the card selection popup
 */
public class CardPopupController implements Initializable {

    private int player = 0;
    private int numCards = 0;

    @FXML
    private GridPane gridPane;

    /**
     * Set the current player number after initializing the controller
     *
     * @param playerNum the number of the player whose hand is being shown
     */
    CardPopupController(int playerNum) {
        this.player = playerNum;
        //this.numCards = player.getNumCards();
    }

    /**
     * Initialize the card controller and popup
     *
     * @param fxmlFileLocation fxml file this controller is for (card_popup.fxml)
     * @param resources        resources available in the package
     */
    public void initialize(URL fxmlFileLocation, ResourceBundle resources) {
        assert gridPane != null : "fx:id=\"gridPane\" was not injected: check your FXML file 'card_popup.fxml'.";

        gridSetup();
    }

    /**
     * Sets up the grid pane with the appropriate number of rows based on the number
     * of cards the player has in their hand
     * Minimum 1 row, 3 columns (these are the default setup, no changes required)
     */
    private void gridSetup() {
        int numRows = 0;
        if (numCards > 3) {
            numRows = (int) Math.ceil(numCards / 3);
        }
        for (int i = 0; i < numRows - 1; i++) {
            RowConstraints rowConst = new RowConstraints();
            rowConst.setPercentHeight(100.0 / numRows);
            gridPane.getRowConstraints().add(rowConst);
        }
    }
}