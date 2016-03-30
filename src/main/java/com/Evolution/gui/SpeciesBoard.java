package com.Evolution.gui;

import com.Evolution.interfaces.ICard;
import com.Evolution.interfaces.IPlayer;
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
public class SpeciesBoard extends VBox {

    private VBox board;
    private MyHBox playerPane;
    private Label populationSize;
    private Label bodySize;
    private Label foodOnBoard;
    private ChoiceBox<String> actionChoiceBox;
    private Label traitLabel1;
    private Label traitLabel2;
    private Label traitLabel3;
    private IPlayer player;
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
        DRAW_CARD("Draw Card"),
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
    public SpeciesBoard(IPlayer player, MyHBox playerPane) {
        this.board = new VBox();
        this.player = player;
        this.playerPane = playerPane;
    }

    /**
     * Create a default species pane with traits, board, and actions
     *
     * @return the species pane
     */
    public VBox createSpeciesBoard() {
        VBox speciesBoard = new VBox();

        traitLabel1 = new Label("Trait 1: ");
        traitLabel2 = new Label("Trait 2: ");
        traitLabel3 = new Label("Trait 3: ");

        populationSize = new Label("Population: " + 1);
        populationSize.setStyle("-fx-text-fill: black;");
        bodySize = new Label("Body Size: " + 1);
        bodySize.setStyle("-fx-text-fill: black;");
        foodOnBoard = new Label("Food: " + 0);
        foodOnBoard.setStyle("-fx-text-fill: black;");

        speciesBoard.getChildren().addAll(populationSize, bodySize, foodOnBoard);
        speciesBoard.setStyle("-fx-padding: 20, 0, 20, 0; -fx-min-width: 75;" +
                " -fx-min-height: 150; -fx-background-color: burlywood; -fx-alignment: center;");

        actionChoiceBox = new ChoiceBox<>();
        ObservableList<String> options = FXCollections.observableArrayList();
        for (Actions a : Actions.values()) {
            options.add(a.getName());
        }
        actionChoiceBox.setItems(options);
        actionChoiceBox.getSelectionModel().selectFirst();
        actionChoiceBox.getSelectionModel().selectedIndexProperty().addListener((observable, oldValue, newValue) -> {
            performAction(Actions.values()[newValue.intValue()]);
            this.actionChoiceBox.getSelectionModel().selectFirst();
        });

        board.getChildren().addAll(traitLabel1, traitLabel2, traitLabel3, speciesBoard, actionChoiceBox);
        board.setStyle("-fx-padding: 20, 20, 20, 20;");
        return board;
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
            case DRAW_CARD:
                // TODO draw a card into player hand
                break;
            case VIEW_CARDS:
                openCardWindow(Actions.VIEW_CARDS);
                break;
            case ADD_TRAIT:
                openCardWindow(Actions.ADD_TRAIT);
                // TODO discard selected card
                break;
            case ADD_SPECIES_LEFT:
                openCardWindow(Actions.ADD_SPECIES_LEFT);
                // TODO discard selected card
                this.playerPane.addSpecies(0);
                break;
            case ADD_SPECIES_RIGHT:
                openCardWindow(Actions.ADD_SPECIES_RIGHT);
                // TODO discard selected card
                this.playerPane.addSpecies(1);
                break;
            case INCREASE_POPULATION:
                openCardWindow(Actions.INCREASE_POPULATION);
                // TODO discard selected card
                setPopulationSize(1);
                break;
            case INCREASE_BODY_SIZE:
                openCardWindow(Actions.INCREASE_BODY_SIZE);
                // TODO discard selected card
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
            CardPopupController controller = new CardPopupController(this.player.getCards(), this);
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
        System.out.println("card selected: " + c.getName());
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
     * Get the population size for this species
     *
     * @return population size
     */
    public int getPopulationSize() {
        String[] split = this.populationSize.getText().split(": ");
        return Integer.parseInt(split[1]);
    }

    /**
     * Sets the value of the population size label for this species
     *
     * @param amount amount to increase/decrease the population by
     */
    public void setPopulationSize(int amount) {
        String[] split = this.populationSize.getText().split(": ");
        int size = Integer.parseInt(split[1]);
        size += amount;
        this.populationSize.setText("Population: " + size);
    }

    /**
     * Get the body size of the current species
     *
     * @return the body size
     */
    public int getBodySize() {
        String[] split = this.bodySize.getText().split(": ");
        return Integer.parseInt(split[1]);
    }

    /**
     * Sets the values of the body size label for this species
     *
     * @param amount amount to increase/decrease the population by
     */
    public void setBodySize(int amount) {
        String[] split = this.bodySize.getText().split(": ");
        int size = Integer.parseInt(split[1]);
        size += amount;
        this.bodySize.setText("Body Size: " + size);
    }

    /**
     * Get the amount on food for this species
     *
     * @return amount of food
     */
    public int getFoodOnBoard() {
        String[] split = this.foodOnBoard.getText().split(": ");
        return Integer.parseInt(split[1]);
    }

    /**
     * Sets the value of the food label for this species
     *
     * @param amount amount to increase/decrease the food count by
     */
    public void setFoodOnBoard(int amount) {
        String[] split = this.foodOnBoard.getText().split(": ");
        int food = Integer.parseInt(split[1]);
        food += amount;
        this.foodOnBoard.setText("Food: " + food);
    }

    /**
     * Gets the actions choiceBox for this species
     *
     * @return the ChoiceBox of the species
     */
    public ChoiceBox<String> getActionChoiceBox() {
        return this.actionChoiceBox;
    }

    /**
     * gets the first trait for this species
     *
     * @return trait # 1
     */
    public String getTrait1() {
        String[] split = this.traitLabel1.getText().split(": ");
        return split[1];
    }

    /**
     * Sets the first trait for this species
     *
     * @param trait the new trait to set for trait 1
     */
    public void setTrait1(String trait) {
        this.traitLabel1.setText("Trait 1: " + trait);
    }

    /**
     * gets the second trait for this species
     *
     * @return trait # 2
     */
    public String getTrait2() {
        String[] split = this.traitLabel2.getText().split(": ");
        return split[1];
    }

    /**
     * Sets the second trait for this species
     *
     * @param trait the new trait to set for trait 2
     */
    public void setTrait2(String trait) {
        this.traitLabel2.setText("Trait 1: " + trait);
    }

    /**
     * gets the third trait for this species
     *
     * @return trait # 3
     */
    public String getTrait3() {
        String[] split = this.traitLabel1.getText().split(": ");
        return split[1];
    }

    /**
     * Sets the third trait for this species
     *
     * @param trait the new trait to set for trait 3
     */
    public void setTrait3(String trait) {
        this.traitLabel3.setText("Trait 1: " + trait);
    }
}
