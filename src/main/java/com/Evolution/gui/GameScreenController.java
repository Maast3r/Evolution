package com.Evolution.gui;

import com.Evolution.exceptions.*;
import com.Evolution.interfaces.ICard;
import com.Evolution.interfaces.IDeck;
import com.Evolution.interfaces.IPlayer;
import com.Evolution.interfaces.IWateringHole;
import com.Evolution.logic.*;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

/**
 * Created by brownba1 on 3/22/2016.
 * Controller for the game screen
 */
class GameScreenController implements Initializable {

    private int numPlayers = 0;
    private ArrayList<IPlayer> players = new ArrayList<>();
    private ArrayList<MyHBox> playerPanes = new ArrayList<>();
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
    private Label wateringHoleCardLabel;
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
        assert this.topPane != null : "fx:id=\"topPane\" was not injected: check your FXML file 'game_screen.fxml'.";
        assert this.leftPane != null : "fx:id=\"leftPane\" was not injected: check your FXML file 'game_screen.fxml'.";
        assert this.bottomPane != null : "fx:id=\"bottomPane\" was not injected: check your FXML file 'game_screen" +
                ".fxml'.";
        assert this.drawLabel != null : "fx:id=\"drawLabel\" was not injected: check your FXML file 'game_screen" +
                ".fxml'.";
        assert this.discardLabel != null : "fx:id=\"discardLabel\" was not injected: check your FXML file " +
                "'game_screen.fxml'.";
        assert this.wateringHoleLabel != null : "fx:id=\"wateringHoleLabel\" was not injected: check your FXML file " +
                "'game_screen.fxml'.";
        assert this.wateringHoleCardLabel != null : "fx:id\"wateringHoleCardLabel\" was not injected: check your FXML" +
                " file 'game_screen.fxml'.";
        assert this.playerTurnLabel != null : "fx:id=\"playerTurnLabel\" was not injected: check your FXML file " +
                "'game_screen.fxml'.";
        assert this.phaseLabel != null : "fx:id=\"phaseLabel\" was not injected: check your FXML file 'game_screen" +
                ".fxml'.";
        assert this.foodBankLabel != null : "fx:id=\"foodBankLabel\" was not injected: check your FXML file " +
                "'game_screen.fxml'.";

        for (int i = 0; i < this.numPlayers; i++) {
            try {
                this.players.add(new Player(new Species()));
            } catch (NullGameObjectException e) {
                e.printStackTrace();
            }
        }

        try {
            IWateringHole wateringHole = new WateringHole();
            DeckFactory df = new DeckFactory();
            IDeck<ICard> drawPile = df.generateDrawPile(new FileInputStream(
                    new File("src/main/resources/cardFiles/cardInformation.txt")));
            drawPile.shuffle();
            IDeck<ICard> discardPile = df.generateDiscardPile();

            this.game = new Game(this.players, wateringHole, drawPile, discardPile);
            this.game.startGame();

            System.out.println("game initialized");
        } catch (IllegalNumberOfPlayers | IllegalCardDirectionException
                | IOException | WrongFileException | DeckEmptyException
                | InvalidPlayerSelectException | IllegalCardFoodException
                | NullGameObjectException | InvalidWateringHoleCardCountException
                | SpeciesFullException | WateringHoleEmptyException
                | SpeciesPopulationException | FoodBankEmptyException
                | IllegalSpeciesIndexException exception) {
            exception.printStackTrace();
        }

        staticElementsUpdate();

        try {
            if (this.numPlayers == 3) {
                threePlayerSetup();
            } else if (this.numPlayers == 4) {
                fourPlayerSetup();
            } else {
                fivePlayerSetup();
            }
        } catch (InvalidPlayerSelectException | IllegalSpeciesIndexException e) {
            e.printStackTrace();
        }

        toggleChoiceBox();
    }

    /**
     * Set up the 'static' elements on the screen (i.e. watering hole, cards, etc.)
     */
    void staticElementsUpdate() {
        this.drawLabel.setText("Draw Pile:\n" + this.game.getDrawPile().getSize() + " cards");
        this.discardLabel.setText("Discard Pile:\n" + this.game.getDiscardPile().getSize() + " cards");
        this.wateringHoleLabel.setText("Food: " + this.game.getWateringHole().getFoodCount() + " pieces");
        this.wateringHoleCardLabel.setText("Cards: " + this.game.getWateringHole().getCards().size());
        this.phaseLabel.setText("Phase: " + this.game.getPhase().getName());
        this.playerTurnLabel.setText("Player " + this.game.getTurn() + " Turn");
        this.foodBankLabel.setText("Food Bank: " + this.game.getFoodBankCount() + " pieces left");
    }

    /**
     * De/Activates the ChoiceBoxes under each SpeciesBoard depending on which player's turn it currently is.
     */
    void toggleChoiceBox() {
        int activeTurn = this.game.getTurn() - 1;
        for (int i = 0; i < this.players.size(); i++) {
            if (activeTurn == i) {
                this.playerPanes.get(i).setChoicesActive(true);
            } else {
                this.playerPanes.get(i).setChoicesActive(false);
            }
        }
    }

    /**
     * Commands each player pane to update the ChoiceBoxes under each of its SpeciesBoards
     */
    void changeChoiceBox() {
        this.playerPanes.forEach((myHBox) -> {
            try {
                myHBox.updateChoices();
            } catch (InvalidPlayerSelectException | IllegalSpeciesIndexException e) {
                e.printStackTrace();
            }
        });
    }

    /**
     * Set up the screen for three players
     * Each player gets assigned to a pane
     */
    private void threePlayerSetup() throws InvalidPlayerSelectException, IllegalSpeciesIndexException {
        startingPaneSetup(this.bottomPane, 0);
        startingPaneSetup(this.leftPane, 1);
        startingPaneSetup(this.topPane, 2);
    }

    /**
     * Set up the screen for four players
     * The bottom pane must be split to handle two players
     */
    private void fourPlayerSetup() throws InvalidPlayerSelectException, IllegalSpeciesIndexException {
        HBox player1 = new HBox();
        player1.setAlignment(Pos.CENTER);
        HBox player2 = new HBox();
        player2.setAlignment(Pos.CENTER);

        startingPaneSetup(player1, 0);
        startingPaneSetup(player2, 1);
        startingPaneSetup(this.leftPane, 2);
        startingPaneSetup(this.topPane, 3);

        this.bottomPane.getChildren().addAll(player2, player1);
    }

    /**
     * Set up the screen for five players
     * The top and bottom panes must be split to handle two players each
     */
    private void fivePlayerSetup() throws InvalidPlayerSelectException, IllegalSpeciesIndexException {
        HBox player1 = new HBox();
        player1.setAlignment(Pos.CENTER);
        HBox player2 = new HBox();
        player2.setAlignment(Pos.CENTER);
        HBox player4 = new HBox();
        player4.setAlignment(Pos.CENTER);
        HBox player5 = new HBox();
        player5.setAlignment(Pos.CENTER);

        startingPaneSetup(player1, 0);
        startingPaneSetup(player2, 1);
        startingPaneSetup(this.leftPane, 2);
        startingPaneSetup(player4, 3);
        startingPaneSetup(player5, 4);

        this.bottomPane.getChildren().addAll(player2, player1);
        this.topPane.getChildren().addAll(player4, player5);
    }

    /**
     * Set up the starting screen for each player
     *
     * @param pane  player pane
     * @param index player index in the players array
     */
    private void startingPaneSetup(HBox pane, int index) throws InvalidPlayerSelectException, IllegalSpeciesIndexException {
        MyHBox hBox = new MyHBox(index, this.game, this);
        HBox playerPane = hBox.createBox();
        pane.getChildren().addAll(playerPane);
        this.playerPanes.add(hBox);
    }

}