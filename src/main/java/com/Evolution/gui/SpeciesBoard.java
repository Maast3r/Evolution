package com.Evolution.gui;

import com.Evolution.interfaces.ICard;
import com.Evolution.logic.Game;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Created by brownba1 on 3/28/2016.
 * Creates a new SpeciesBoard to add to a player's list of species
 * when they add a species
 */
public class SpeciesBoard extends VBox {

    private Game game;
    private VBox board;
    private MyHBox playerPane;
    private Label populationSize;
    private Label bodySize;
    private Label foodOnBoard;
    private ChoiceBox<String> actionChoiceBox;
    private Label traitLabel1;
    private Label traitLabel2;
    private Label traitLabel3;
    private int playerIndex;
    private int speciesNum;
    private ICard selectedCard;
    private String traitSelection;

    /**
     * Enum for the actions in the choiceBox
     * Action, String to show in drop down
     * New actions are automatically added to drop down, but must the execution
     * needs added to performAction
     */
    public enum Actions {
        ACTIONS("Actions"),
        VIEW_CARDS("View Cards"),
        ADD_TRAIT("Add Trait"),
        ADD_SPECIES_LEFT("Add Species (Left)"),
        ADD_SPECIES_RIGHT("Add Species (Right)"),
        INCREASE_POPULATION("Increase Population"),
        INCREASE_BODY_SIZE("Increase Body Size");

        private String name;

        Actions(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }
    }

    /**
     * Constructor for the species board
     */
    public SpeciesBoard(int index, int num, MyHBox playerPane, Game game) {
        this.board = new VBox();
        this.playerIndex = index;
        this.speciesNum = num;
        this.playerPane = playerPane;
        this.game = game;
    }

    /**
     * Create a default species pane with traits, board, and actions
     *
     * @return the species pane
     */
    public VBox createSpeciesBoard() {
        VBox speciesBoard = new VBox();

        this.traitLabel1 = new Label("Trait 1: ");
        this.traitLabel2 = new Label("Trait 2: ");
        this.traitLabel3 = new Label("Trait 3: ");

        this.populationSize = new Label("Population: " + 1);
        this.populationSize.setStyle("-fx-text-fill: black;");
        this.bodySize = new Label("Body Size: " + 1);
        this.bodySize.setStyle("-fx-text-fill: black;");
        this.foodOnBoard = new Label("Food: " + 0);
        this.foodOnBoard.setStyle("-fx-text-fill: black;");

        speciesBoard.getChildren().addAll(this.populationSize, this.bodySize, this.foodOnBoard);
        speciesBoard.setStyle("-fx-padding: 20, 0, 20, 0; -fx-min-width: 75;" +
                " -fx-min-height: 150; -fx-background-color: burlywood; -fx-alignment: center;");

        this.actionChoiceBox = new ChoiceBox<>();
        ObservableList<String> options = FXCollections.observableArrayList();
        for (Actions a : Actions.values()) {
            options.add(a.getName());
        }
        this.actionChoiceBox.setItems(options);
        this.actionChoiceBox.getSelectionModel().selectFirst();
        this.actionChoiceBox.getSelectionModel().selectedIndexProperty().addListener((observable, oldValue, newValue) -> {
            performAction(Actions.values()[newValue.intValue()]);
            this.actionChoiceBox.getSelectionModel().selectFirst();
        });

        this.board.getChildren().addAll(this.traitLabel1, this.traitLabel2, this.traitLabel3, speciesBoard,
                this.actionChoiceBox);
        this.board.setStyle("-fx-padding: 20, 20, 20, 20;");
        return this.board;
    }

    /**
     * Performs the action that the user selected from the action choiceBox for this species
     *
     * @param action the selected action
     */
    private void performAction(Actions action) {
        // perform selected action
        switch (action) {
            case ACTIONS:
                // do nothing
                break;
            case VIEW_CARDS:
                openCardWindow(Actions.VIEW_CARDS);
                break;
            case ADD_TRAIT:
                openCardWindow(Actions.ADD_TRAIT);
                this.game.discardFromPlayer(this.playerIndex, this.selectedCard);
                this.playerPane.updateGameScreen();
                break;
            case ADD_SPECIES_LEFT:
                openCardWindow(Actions.ADD_SPECIES_LEFT);
                this.game.discardFromPlayer(this.playerIndex, this.selectedCard);
                this.playerPane.updateGameScreen();
                this.playerPane.addSpecies(0);
                break;
            case ADD_SPECIES_RIGHT:
                openCardWindow(Actions.ADD_SPECIES_RIGHT);
                this.game.discardFromPlayer(this.playerIndex, this.selectedCard);
                this.playerPane.updateGameScreen();
                this.playerPane.addSpecies(1);
                break;
            case INCREASE_POPULATION:
                openCardWindow(Actions.INCREASE_POPULATION);
                this.game.discardFromPlayer(this.playerIndex, this.selectedCard);
                this.playerPane.updateGameScreen();
                setPopulationSize(1);
                break;
            case INCREASE_BODY_SIZE:
                openCardWindow(Actions.INCREASE_BODY_SIZE);
                this.game.discardFromPlayer(this.playerIndex, this.selectedCard);
                this.playerPane.updateGameScreen();
                setBodySize(1);
                break;
        }

    }

    /**
     * Opens up the card window for the player to view their hand
     */
    private void openCardWindow(Actions action) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/layouts/card_popup.fxml"));
            CardPopupController controller =
                    new CardPopupController(this.game.getPlayerObjects().get(this.playerIndex).getCards(), this);
            if (action == Actions.ADD_TRAIT) {
                controller.setAddTrait(true);
            }
            loader.setController(controller);
            Parent p = loader.load();
            Stage s = new Stage();
            s.setTitle("Evolution!");
            s.getIcons().add(new Image("/images/icon.png"));
            s.setScene(new Scene(p, Color.BLACK));
            s.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Sets the card that the user selects from the card popup
     *
     * @param c the card that was selected
     */
    public void setSelectedCard(ICard c) {
        this.selectedCard = c;
    }

    /**
     * Sets the trait that the user selects from the card popup
     *
     * @param trait which trait to set
     */
    public void setTraitSelection(int trait) {
        switch (trait) {
            case 0:
                setTrait1(this.selectedCard.getName());
                break;
            case 1:
                setTrait2(this.selectedCard.getName());
                break;
            case 2:
                setTrait3(this.selectedCard.getName());
                break;
        }
    }

    /**
     * Sets the value of the population size label for this species
     *
     * @param amount amount to increase/decrease the population by
     */
    public void setPopulationSize(int amount) {
        // Set the population size on player then update label
        this.game.getPlayerObjects().get(this.playerIndex).getSpecies();
    }


    /**
     * Sets the values of the body size label for this species
     *
     * @param amount amount to increase/decrease the population by
     */
    public void setBodySize(int amount) {
        // Set the body size on player then update label
    }

    /**
     * Sets the value of the food label for this species
     *
     * @param amount amount to increase/decrease the food count by
     */
    public void setFoodOnBoard(int amount) {
        // Set food for this species then reset label
    }

    /**
     * Sets the first trait for this species
     *
     * @param trait the new trait to set for trait 1
     */
    public void setTrait1(String trait) {
        // Set trait 1 for this species then update label
    }

    /**
     * Sets the second trait for this species
     *
     * @param trait the new trait to set for trait 2
     */
    public void setTrait2(String trait) {
        // Set trait 2 for this species then update label
    }

    /**
     * Sets the third trait for this species
     *
     * @param trait the new trait to set for trait 3
     */
    public void setTrait3(String trait) {
        // Set trait 3 for this species then update label
    }

    /**
     * Gets this species current number
     *
     * @return species number
     */
    public int getSpeciesNum() {
        return this.speciesNum;
    }

    /**
     * Sets this species current species number
     *
     * @param num the new number for this species
     */
    public void setSpeciesNum(int num) {
        this.speciesNum = num;
    }
}
