package com.Evolution.gui;

import com.Evolution.exceptions.*;
import com.Evolution.interfaces.ICard;
import com.Evolution.interfaces.ISpecies;
import com.Evolution.logic.Game;
import com.Evolution.logic.Species;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
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
class SpeciesBoard extends VBox {

    private Game game;
    private GameScreenController gameController;
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

    private ChangeListener actionListener;

    private ObservableList<String> phase2Options = FXCollections.observableArrayList(Actions.ACTIONS.getName(),
            Actions.VIEW_CARDS.getName(), Actions.DISCARD_TO_WATERINGHOLE.getName());

    private ObservableList<String> phase3Options = FXCollections.observableArrayList(Actions.ACTIONS.getName(),
            Actions.VIEW_CARDS.getName(), Actions.ADD_TRAIT.getName(), Actions.ADD_SPECIES_LEFT.getName(),
            Actions.ADD_SPECIES_RIGHT.getName(), Actions.INCREASE_POPULATION.getName(),
            Actions.INCREASE_BODY_SIZE.getName(), Actions.END_TURN.getName());

    private ObservableList<String> phase4Options = FXCollections.observableArrayList(Actions.ACTIONS.getName(),
            Actions.VIEW_CARDS.getName(), Actions.FEED_SPECIES.getName(), Actions.ATTACK_SPECIES.getName());

    /**
     * Enum for the actions in the choiceBox
     * Name is the string to show in drop down
     */
    private enum Actions {
        ACTIONS("Actions"),
        VIEW_CARDS("View Cards"),
        ADD_TRAIT("Add Trait"),
        ADD_SPECIES_LEFT("Add Species (Left)"),
        ADD_SPECIES_RIGHT("Add Species (Right)"),
        INCREASE_POPULATION("Increase Population"),
        INCREASE_BODY_SIZE("Increase Body Size"),
        FEED_SPECIES("Adds a Food to the Species"),
        ATTACK_SPECIES("Attacks Another Species"),
        END_TURN("End Your Turn"),
        DISCARD_TO_WATERINGHOLE("Discard to Watering Hole");

        private String name;

        /**
         * Used in enum initialization to store its corresponding string as a field
         *
         * @param name Action string representation
         */
        Actions(String name) {
            this.name = name;
        }

        /**
         * Returns the string representation of this Action.
         *
         * @return Action.name
         */
        public String getName() {
            return name;
        }
    }

    /**
     * Constructor for the species board
     */
    SpeciesBoard(int index, int num, MyHBox playerPane, Game game, GameScreenController controller) {
        this.board = new VBox();
        this.playerIndex = index;
        this.speciesNum = num;
        this.playerPane = playerPane;
        this.game = game;
        this.gameController = controller;
    }

    /**
     * Create a default species pane with traits, board, and actions
     *
     * @return the species pane
     */
    VBox createSpeciesBoard() {
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
        setChoiceBoxPhase(this.game.getPhase().getNumber());
        initChangeListener();
        this.actionChoiceBox.getSelectionModel().selectedIndexProperty().addListener(actionListener);
        actionChoiceBox.getSelectionModel().selectFirst();

        this.board.getChildren().addAll(this.traitLabel1, this.traitLabel2, this.traitLabel3, speciesBoard,
                this.actionChoiceBox);
        this.board.setStyle("-fx-padding: 20, 20, 20, 20;");
        return this.board;
    }

    /**
     * Creates a ChangeListener which is used by a ChoiceBox to perform actions
     */
    private void initChangeListener() {
        this.actionListener = (ObservableValue observable, Object oldValue, Object newValue) -> {
            int val = ((int) newValue < 0) ? 0 : (int) newValue;
            try {
                switch (this.game.getPhase().getNumber()) {
                    // TODO: This block will need edited as future phases are implemented
                    case 2:
                        if (newValue.equals(2)) {
                            performAction(Actions.values()[10]);
                        } else {
                            performAction(Actions.values()[val]);
                        }
                        break;
                    case 3:
                        if(newValue.equals(7)) {
                            performAction(Actions.values()[9]);
                        }
                    default:
                        System.out.println(Actions.values()[val] + " ---- " + val + " ++++ " + newValue);
                        performAction(Actions.values()[val]);
                        break;
                }
            } catch (InvalidDiscardToWateringHoleException | InvalidAddToWateringHoleException
                    | IllegalPlayerIndexException | SpeciesPopulationException | IllegalSpeciesIndexException
                    | IllegalCardDiscardException | SpeciesBodySizeException | DeckEmptyException
                    | IllegalCardDirectionException | InvalidPlayerSelectException e) {
                e.printStackTrace();
            }
        };
    }

    /**
     * Performs the action that the user selected from the action choiceBox for this species
     *
     * @param action the selected action
     */
    private void performAction(Actions action) throws InvalidDiscardToWateringHoleException,
            InvalidAddToWateringHoleException, IllegalPlayerIndexException, SpeciesPopulationException,
            IllegalSpeciesIndexException, IllegalCardDiscardException, SpeciesBodySizeException,
            InvalidPlayerSelectException, IllegalCardDirectionException, DeckEmptyException {
        // perform selected action
        switch (action) {
            case ACTIONS:
                // do nothing
                break;
            case VIEW_CARDS:
                openCardWindow(Actions.VIEW_CARDS);
                this.selectedCard = null;
                break;
            case ADD_TRAIT:
                openCardWindow(Actions.ADD_TRAIT);
                if (this.selectedCard != null) {
                    this.game.discardFromPlayer(this.playerIndex, this.selectedCard);
                    // this
                    this.playerPane.updateGameScreen();
                    this.selectedCard = null;
                }
                break;
            case ADD_SPECIES_LEFT:
                openCardWindow(Actions.ADD_SPECIES_LEFT);
                if (this.selectedCard != null) {
                    this.game.discardFromPlayer(this.playerIndex, this.selectedCard);
                    this.playerPane.updateGameScreen();
                    this.playerPane.addSpecies(0);
                    this.game.discardForLeftSpecies(this.playerIndex, this.selectedCard, new Species());
                    this.selectedCard = null;
                }
                break;
            case ADD_SPECIES_RIGHT:
                openCardWindow(Actions.ADD_SPECIES_RIGHT);
                if (this.selectedCard != null) {
                    this.game.discardFromPlayer(this.playerIndex, this.selectedCard);
                    this.playerPane.updateGameScreen();
                    this.playerPane.addSpecies(1);
                    this.game.discardForRightSpecies(this.playerIndex, this.selectedCard, new Species());
                    this.selectedCard = null;
                }
                break;
            case INCREASE_POPULATION:
                openCardWindow(Actions.INCREASE_POPULATION);
                if (this.selectedCard != null) {
                    this.game.increasePopulation(this.playerIndex, this.speciesNum, this.selectedCard);
                    this.playerPane.updateGameScreen();
                    setPopulationSize(this.game.getPlayerObjects()
                            .get(this.playerIndex).getSpecies().get(this.speciesNum).getPopulation());
                    this.selectedCard = null;
                }
                break;
            case INCREASE_BODY_SIZE:
                openCardWindow(Actions.INCREASE_BODY_SIZE);
                if (this.selectedCard != null) {
                    this.game.increaseBodySize(this.playerIndex, this.speciesNum, this.selectedCard);
                    this.playerPane.updateGameScreen();
                    setBodySize(this.game.getPlayerObjects()
                            .get(this.playerIndex).getSpecies().get(this.speciesNum).getBodySize());
                    this.selectedCard = null;
                }
                break;
            case FEED_SPECIES:
                break;
            case ATTACK_SPECIES:
                break;
            case END_TURN:
                this.game.getPhase().execute();
                this.playerPane.updateGameScreen();
                this.gameController.toggleChoiceBox();
                this.gameController.changeChoiceBox();
                this.selectedCard = null;
                break;
            case DISCARD_TO_WATERINGHOLE:
                openCardWindow(Actions.DISCARD_TO_WATERINGHOLE);
                if (this.selectedCard != null) {
                    this.game.discardToWateringHole(this.playerIndex, this.selectedCard);
                    try {
                        this.game.getPhase().execute();
                    } catch (IllegalCardDirectionException | InvalidPlayerSelectException | DeckEmptyException e) {
                        e.printStackTrace();
                    }
                    this.playerPane.updateGameScreen();
                    this.gameController.toggleChoiceBox();
                    this.gameController.changeChoiceBox();
                    this.selectedCard = null;
                }
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
            s.focusedProperty().addListener((observable, oldValue, newValue) -> {
                if (!newValue) {
                    s.hide();
                    this.actionChoiceBox.getSelectionModel().selectFirst();
                }
            });
            s.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Sets the ability to open this SpeciesBoard's ChoiceBox
     *
     * @param viewable whether or not it should be clickable
     */
    void setChoiceBoxViewable(boolean viewable) {
        this.actionChoiceBox.setDisable(!viewable);
    }

    /**
     * Changes the viewable options for this SpeciesBoard based on the given phase number
     *
     * @param phaseNum current phase
     */
    void setChoiceBoxPhase(int phaseNum) {
        switch (phaseNum) {
            case 1:
                this.actionChoiceBox.setItems(this.phase2Options);
                break;
            case 2:
                this.actionChoiceBox.setItems(this.phase2Options);
                break;
            case 3:
                this.actionChoiceBox.setItems(this.phase3Options);
                break;
            case 4:
                this.actionChoiceBox.setItems(this.phase4Options);
                break;
            default:
                ObservableList<String> options = FXCollections.observableArrayList();
                for (Actions a : Actions.values()) {
                    options.add(a.getName());
                }
                this.actionChoiceBox.setItems(options);
                break;
        }
        this.actionChoiceBox.getSelectionModel().selectFirst();
    }

    /**
     * Sets the card that the user selects from the card popup
     *
     * @param c the card that was selected
     */
    void setSelectedCard(ICard c) {
        this.selectedCard = c;
    }

    /**
     * Sets the trait that the user selects from the card popup
     *
     * @param trait which trait to set
     */
    void setTraitSelection(int trait) {
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
    private void setPopulationSize(int amount) {
        // Set the population size on player then update label
        this.populationSize.setText("Population: " + amount);
    }


    /**
     * Sets the values of the body size label for this species
     *
     * @param amount amount to increase/decrease the population by
     */
    private void setBodySize(int amount) {
        // Set the body size on player then update label
        this.bodySize.setText("Body Size: " + amount);
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
    private void setTrait1(String trait) {
        // Set trait 1 for this species then update label
    }

    /**
     * Sets the second trait for this species
     *
     * @param trait the new trait to set for trait 2
     */
    private void setTrait2(String trait) {
        // Set trait 2 for this species then update label
    }

    /**
     * Sets the third trait for this species
     *
     * @param trait the new trait to set for trait 3
     */
    private void setTrait3(String trait) {
        // Set trait 3 for this species then update label
    }

    /**
     * Gets this species current number
     *
     * @return species number
     */
    int getSpeciesNum() {
        return this.speciesNum;
    }

    /**
     * Sets this species current species number
     *
     * @param num the new number for this species
     */
    void setSpeciesNum(int num) {
        this.speciesNum = num;
    }
}
