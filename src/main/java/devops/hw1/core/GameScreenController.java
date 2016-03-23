package devops.hw1.core;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.image.ImageView;

import java.net.URL;
import java.util.ResourceBundle;

public class GameScreenController implements Initializable {

    @FXML
    private ImageView startImage;

    @FXML
    private ChoiceBox<Integer> numPlayersChoiceBox;

    @FXML
    private Button playGameButton;

    public void initialize(URL fxmlFileLocation, ResourceBundle resources) {
        assert startImage != null : "fx:id=\"startImage\" was not injected: check your FXML file 'start_screen.fxml'.";
        assert numPlayersChoiceBox != null : "fx:id=\"numPlayerChoiceBox\" was not injected: check your FXML file 'start_screen.fxml'.";
        assert playGameButton != null : "fx:id=\"playGameButton\" was not injected: check your FXML file 'start_screen.fxml'.";

        numPlayersChoiceBox.setItems(FXCollections.observableArrayList(3, 4, 5));
        numPlayersChoiceBox.getSelectionModel().selectFirst();

        playGameButton.setOnMouseClicked(event -> {
            int players = numPlayersChoiceBox.getSelectionModel().getSelectedItem();
            // System.out.println("Number of players: " + players);
            // TODO: make call to begin game function with 'players'
        });
    }
}