package devops.hw1.core;

/**
 * Created by brownba1 on 3/22/2016.
 */

import javafx.collections.FXCollections;
import javafx.fxml.Initializable;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.layout.*;

/**
 * Controller for the game screen
 */
public class GameScreenController implements Initializable {

    private int numPlayers = 0;

    @FXML
    private BorderPane gamePane;
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
     * @param numPlayers the number of players selected on the start screen
     */
    GameScreenController(int numPlayers) {
        this.numPlayers = numPlayers;
    }

    /**
     * Initialize the game controller and screen
     * @param fxmlFileLocation fxml file this controller is for (game_screen.fxml)
     * @param resources resources available in the package
     */
    public void initialize(URL fxmlFileLocation, ResourceBundle resources) {
        // TODO: Null check for scene objects

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
        drawLabel.setText("Draw Pile:\n" + 0 + " cards");
        discardLabel.setText("Discard Pile:\n" + 0 + " cards");
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
        player1.setMinWidth(600);
        HBox player2 = new HBox();
        player2.setAlignment(Pos.CENTER);
        player2.setMinWidth(600);

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
        player1.setMinWidth(600);
        HBox player2 = new HBox();
        player2.setAlignment(Pos.CENTER);
        player2.setMinWidth(600);
        HBox player4 = new HBox();
        player4.setAlignment(Pos.CENTER);
        player4.setMinWidth(600);
        HBox player5 = new HBox();
        player5.setAlignment(Pos.CENTER);
        player5.setMinWidth(600);

        startingPaneSetup(player1, 1);
        startingPaneSetup(player2, 2);
        startingPaneSetup(leftPane, 3);
        startingPaneSetup(player4, 4);
        startingPaneSetup(player5, 5);

        bottomPane.getChildren().addAll(player2, player1);
        topPane.getChildren().addAll(player4, player5);
    }

    /**
     * Set up the default screen with player info and single species
     * @param playerPane player pane
     * @param num player number
     */
    private void startingPaneSetup(HBox playerPane, int num) {
        Label foodBagLabel = new Label("Food bag: " + 0);
        Label playerNumLabel = new Label("Player " + num);

        VBox playerInfo = new VBox();
        playerInfo.setAlignment(Pos.CENTER);
        playerInfo.getChildren().addAll(playerNumLabel, foodBagLabel);

        VBox speciesPane = makeSpeciesPane();
        playerPane.getChildren().addAll(playerInfo, speciesPane);
    }

    /**
     * Create a default species pane with traits, board, and actions
     * @return the species pane
     */
    private VBox makeSpeciesPane() {
        VBox speciesPane = new VBox();
        VBox speciesBoard = new VBox();

        Label trait1Label = new Label("Trait 1: " + "T1");
        Label trait2Label = new Label("Trait 2: " + "T2");
        Label trait3Label = new Label("Trait 3: " + "T3");

        Label populationSize = new Label("Population: " + 1);
        populationSize.setStyle("-fx-text-fill: black");
        Label bodySize = new Label("Body Size: " + 1);
        bodySize.setStyle("-fx-text-fill: black");

        speciesBoard.getChildren().addAll(populationSize, bodySize);
        speciesBoard.setStyle("-fx-padding: 20, 0, 20, 0; -fx-min-width: 75;" +
                " -fx-min-height: 150; -fx-background-color: burlywood; -fx-alignment: center");

        ChoiceBox<String> actionChoiceBox = new ChoiceBox<>();
        actionChoiceBox.setItems(FXCollections.observableArrayList("Actions", "Action1", "Action2"));
        actionChoiceBox.getSelectionModel().selectFirst();

        speciesPane.getChildren().addAll(trait1Label, trait2Label, trait3Label, speciesBoard, actionChoiceBox);
        speciesPane.setStyle("-fx-padding: 20, 20, 20, 20");
        return speciesPane;
    }
}