package devops.hw1.core;

/**
 * Created by brownba1 on 3/22/2016.
 */

import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;

public class GameScreenController implements Initializable {

    private int numPlayers = 0;

    @FXML
    private BorderPane gamePane;
    @FXML
    private FlowPane topPane;
    @FXML
    private FlowPane leftPane;
    @FXML
    private FlowPane bottomPane;
    @FXML
    private Label drawLabel;
    @FXML
    private Label discardLabel;
    @FXML
    private Label wateringHoleLabel;
    @FXML
    private Label playerTurnLabel;
    @FXML
    private Label phaseLabel;

    public GameScreenController(int numPlayers) {
        this.numPlayers = numPlayers;

    }

    public void initialize(URL fxmlFileLocation, ResourceBundle resources) {
        // TODO: Null check for scene objects

        // Set up static game pane
        staticElementsUpdate();

        // Call method to initialize game

        // Generate scene from return

    }

    private void staticElementsUpdate() {
        // TODO: update to fit actual data
        drawLabel.setText("Draw Pile:\n" + 0 + " cards");
        discardLabel.setText("Discard Pile:\n" + 0 + " cards");
        wateringHoleLabel.setText("Food: " + 0 + " pieces");
        phaseLabel.setText("Phase: " + "Deal Cards");
        playerTurnLabel.setText("Player " + 1 + " Turn");
    }
}