package com.Evolution.gui;

import javafx.collections.FXCollections;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

/**
 * Created by brownba1 on 3/28/2016.
 * Creates a new SpeciesBoard to add to a player's list of species
 * when they add a species
 */
public class SpeciesBoard extends VBox{

    private VBox board;
    private Label populationSize;
    private Label bodySize;
    private ChoiceBox<String> actionChoiceBox;
    private Label traitLabel1;
    private Label traitLabel2;
    private Label traitLabel3;

    /**
     * Constructor for the species board
     */
    public SpeciesBoard() {
        this.board = new VBox();
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

        speciesBoard.getChildren().addAll(populationSize, bodySize);
        speciesBoard.setStyle("-fx-padding: 20, 0, 20, 0; -fx-min-width: 75;" +
                " -fx-min-height: 150; -fx-background-color: burlywood; -fx-alignment: center;");

        actionChoiceBox = new ChoiceBox<>();
        actionChoiceBox.setItems(FXCollections.observableArrayList("Actions", "Add Trait", "Add Species (Left)",
                "Add Species (Right)", "Increase Population", "Increase Body Size"));
        actionChoiceBox.getSelectionModel().selectFirst();

        board.getChildren().addAll(traitLabel1, traitLabel2, traitLabel3, speciesBoard, actionChoiceBox);
        board.setStyle("-fx-padding: 20, 20, 20, 20;");
        return board;
    }

    /**
     * Get the population size label for this species
     * @return population size label
     */
    public Label getPopulationSize() {
        return this.populationSize;
    }

    /**
     * Sets the value of the population size label for this species
     * @param populationSize the new population size of the species
     */
    public void setPopulationSize(Label populationSize) {
        this.populationSize = populationSize;
    }

    /**
     * Get the body size label of the current species
     * @return the body size label
     */
    public Label getBodySize() {
        return this.bodySize;
    }

    /**
     * Sets the values of the body size label for this species
     * @param bodySize the new body size for this species
     */
    public void setBodySize(Label bodySize) {
        this.bodySize = bodySize;
    }

    /**
     * Gets the actions choiceBox for this species
     * @return the ChoiceBox of the species
     */
    public ChoiceBox<String> getActionChoiceBox() {
        return this.actionChoiceBox;
    }

    /**
     * gets the first trait label for this species
     * @return trait label # 1
     */
    public Label getTraitLabel1() {
        return this.traitLabel1;
    }

    /**
     * Sets the first trait for this species
     * @param traitLabel1 the new trait to set for trait 1
     */
    public void setTraitLabel1(Label traitLabel1) {
        this.traitLabel1 = traitLabel1;
    }

    /**
     * gets the second trait label for this species
     * @return trait label # 2
     */
    public Label getTraitLabel2() {
        return this.traitLabel2;
    }

    /**
     * Sets the second trait for this species
     * @param traitLabel2 the new trait to set for trait 2
     */
    public void setTraitLabel2(Label traitLabel2) {
        this.traitLabel2 = traitLabel2;
    }

    /**
     * gets the third trait label for this species
     * @return trait label # 3
     */
    public Label getTraitLabel3() {
        return this.traitLabel3;
    }

    /**
     * Sets the third trait for this species
     * @param traitLabel3 the new trait to set for trait 3
     */
    public void setTraitLabel3(Label traitLabel3) {
        this.traitLabel3 = traitLabel3;
    }
}
