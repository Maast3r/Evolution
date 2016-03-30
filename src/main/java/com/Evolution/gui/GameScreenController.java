package com.Evolution.gui;

/**
 * Created by brownba1 on 3/22/2016.
 */

import com.Evolution.exceptions.IllegalCardDirectionException;
import com.Evolution.exceptions.IllegalNumberOfPlayers;
import com.Evolution.interfaces.IPlayer;
import com.Evolution.interfaces.IWateringHole;
import com.Evolution.logic.Game;
import com.Evolution.logic.Player;
import com.Evolution.logic.Species;
import com.Evolution.testClasses.TestWateringHole;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.*;

/**
 * Controller for the game screen
 */
public class GameScreenController implements Initializable {

    private int numPlayers = 0;
    private ArrayList<IPlayer> players = new ArrayList<>();
    private ArrayList<HBox> playerPanes = new ArrayList<>();
    private Game game;

    @FXML
    private HBox topPane;
    @FXML
    private HBox leftPane;
    @FXML
    private HBox bottomPane;
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
    @FXML
    private Label foodBankLabel;

    /**
     * Set the number of players after initializing the controller
     *
     * @param numPlayers the number of players selected on the start screen
     */
    GameScreenController(int numPlayers) {
        this.numPlayers = numPlayers;
    }

    /**
     * Initialize the game, game controller, and screen for the number of selected players
     *
     * @param fxmlFileLocation fxml file this controller is for (game_screen.fxml)
     * @param resources        resources available in the package
     */
    public void initialize(URL fxmlFileLocation, ResourceBundle resources) {
        assert topPane != null : "fx:id=\"topPane\" was not injected: check your FXML file 'game_screen.fxml'.";
        assert leftPane != null : "fx:id=\"leftPane\" was not injected: check your FXML file 'game_screen.fxml'.";
        assert bottomPane != null : "fx:id=\"bottomPane\" was not injected: check your FXML file 'game_screen.fxml'.";
        assert drawLabel != null : "fx:id=\"drawLabel\" was not injected: check your FXML file 'game_screen.fxml'.";
        assert discardLabel != null : "fx:id=\"discardLabel\" was not injected: check your FXML file 'game_screen.fxml'.";
        assert wateringHoleLabel != null : "fx:id=\"wateringHoleLabel\" was not injected: check your FXML file 'game_screen.fxml'.";
        assert playerTurnLabel != null : "fx:id=\"playerTurnLabel\" was not injected: check your FXML file 'game_screen.fxml'.";
        assert phaseLabel != null : "fx:id=\"phaseLabel\" was not injected: check your FXML file 'game_screen.fxml'.";
        assert foodBankLabel != null : "fx:id=\"foodBankLabel\" was not injected: check your FXML file 'game_screen.fxml'.";

        for (int i = 0; i < numPlayers; i++) {
            players.add(new Player(new Species()));
        }

        // TODO: ANDREW - edit the exceptions here
        try {
            IWateringHole wateringHole = new TestWateringHole();
            game = new Game(players, wateringHole);
            System.out.println("game initialized");
        } catch (IllegalNumberOfPlayers illegalNumberOfPlayers) {
            illegalNumberOfPlayers.printStackTrace();
        } catch (IllegalCardDirectionException e) {
            e.printStackTrace();
        }

        staticElementsUpdate();

        if (numPlayers == 3) {
            threePlayerSetup();

        } else if (numPlayers == 4) {
            fourPlayerSetup();
        } else {
            fivePlayerSetup();
        }
    }

    /**
     * Set up the 'static' elements on the screen (i.e. watering hole, cards, etc.)
     */
    private void staticElementsUpdate() {
        // TODO: update to fit actual data
        drawLabel.setText("Draw Pile:\n" + game.getDrawPile().getSize() + " cards");
        discardLabel.setText("Discard Pile:\n" + game.getDiscardPile().getSize() + " cards");
        wateringHoleLabel.setText("Food: " + 0 + " pieces");
        phaseLabel.setText("Phase: " + "Deal Cards");
        playerTurnLabel.setText("Player " + 1 + " Turn");
        foodBankLabel.setText("Food Bank: " + 0 + " pieces left");
    }

    /**
     * Set up the screen for three players
     * Each player gets assigned to a pane
     */
    private void threePlayerSetup() {
        startingPaneSetup(bottomPane, 1);
        startingPaneSetup(leftPane, 2);
        startingPaneSetup(topPane, 3);
    }

    /**
     * Set up the screen for four players
     * The bottom pane must be split to handle two players
     */
    private void fourPlayerSetup() {
        HBox player1 = new HBox();
        player1.setAlignment(Pos.CENTER);
        HBox player2 = new HBox();
        player2.setAlignment(Pos.CENTER);

        startingPaneSetup(player1, 1);
        startingPaneSetup(player2, 2);
        startingPaneSetup(leftPane, 3);
        startingPaneSetup(topPane, 4);

        bottomPane.getChildren().addAll(player2, player1);
    }

    /**
     * Set up the screen for five players
     * The top and bottom panes must be split to handle two players each
     */
    private void fivePlayerSetup() {
        HBox player1 = new HBox();
        player1.setAlignment(Pos.CENTER);
        HBox player2 = new HBox();
        player2.setAlignment(Pos.CENTER);
        HBox player4 = new HBox();
        player4.setAlignment(Pos.CENTER);
        HBox player5 = new HBox();
        player5.setAlignment(Pos.CENTER);

        startingPaneSetup(player1, 1);
        startingPaneSetup(player2, 2);
        startingPaneSetup(leftPane, 3);
        startingPaneSetup(player4, 4);
        startingPaneSetup(player5, 5);

        bottomPane.getChildren().addAll(player2, player1);
        topPane.getChildren().addAll(player4, player5);
    }

    /**
     * Set up the starting screen for each player
     *
     * @param pane player pane
     * @param num  player number
     */
    private void startingPaneSetup(HBox pane, int num) {
        MyHBox hBox = new MyHBox();
        HBox playerPane = hBox.createBox(num);
        pane.getChildren().addAll(playerPane);
        playerPanes.add(playerPane);
    }
}