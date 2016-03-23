package devops.hw1.core;

/**
 * Created by brownba1 on 3/21/2016.
 */
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class StartScreenController implements Initializable {

    @FXML
    private ChoiceBox<Integer> numPlayersChoiceBox;

    @FXML
    private Button playGameButton;

    public void initialize(URL fxmlFileLocation, ResourceBundle resources) {
        assert numPlayersChoiceBox != null : "fx:id=\"numPlayerChoiceBox\" was not injected: check your FXML file 'start_screen.fxml'.";
        assert playGameButton != null : "fx:id=\"playGameButton\" was not injected: check your FXML file 'start_screen.fxml'.";

        numPlayersChoiceBox.setItems(FXCollections.observableArrayList(3, 4, 5));
        numPlayersChoiceBox.getSelectionModel().selectFirst();

        playGameButton.setOnMouseClicked(event -> goToGameScene());
    }

    private void goToGameScene() {
        int players = numPlayersChoiceBox.getValue();
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/layouts/game_screen.fxml"));
            GameScreenController controller = new GameScreenController(players);
            loader.setController(controller);
            Parent p = loader.load();
            numPlayersChoiceBox.getScene().setRoot(p);
        } catch (IOException e) {
            e.printStackTrace();
        }



    }
}