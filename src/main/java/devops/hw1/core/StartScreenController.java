package devops.hw1.core;

/**
 * Created by brownba1 on 3/21/2016.
 */
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Controller for the start screen
 */
public class StartScreenController implements Initializable {

    @FXML
    private ChoiceBox<Integer> numPlayersChoiceBox;

    @FXML
    private Button playGameButton;

    /**
     * Initialize the start screen
     * @param fxmlFileLocation fxml file location for the screen (start_screen.fxml)
     * @param resources resources available to the screen
     */
    public void initialize(URL fxmlFileLocation, ResourceBundle resources) {
        assert numPlayersChoiceBox != null : "fx:id=\"numPlayerChoiceBox\" was not injected: check your FXML file 'start_screen.fxml'.";
        assert playGameButton != null : "fx:id=\"playGameButton\" was not injected: check your FXML file 'start_screen.fxml'.";

        numPlayersChoiceBox.setItems(FXCollections.observableArrayList(3, 4, 5));
        numPlayersChoiceBox.getSelectionModel().selectFirst();

        playGameButton.setOnMouseClicked(event -> goToGameScene());
    }

    /**
     * Navigate to the game screen after passing along the selected number of players
     */
    private void goToGameScene() {
        int players = numPlayersChoiceBox.getValue();
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/layouts/game_screen.fxml"));
            GameScreenController controller = new GameScreenController(players);
            loader.setController(controller);
            Parent p = loader.load();
            Stage s = (Stage) numPlayersChoiceBox.getScene().getWindow();
            s.setScene(new Scene(p, Color.BLACK));
            s.show();
            s.setMaximized(true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}