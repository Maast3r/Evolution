package com.Evolution.gui;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class WinScreenController implements Initializable {
    private final int winner;
    @FXML
    private Button newGameButton;
    @FXML
    private Label winLabel;

    WinScreenController(int winner) {
        this.winner = winner;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        assert this.newGameButton != null : "fx:id=\"newGameButton\" was not injected: check your FXML file " +
                "'win_screen.fxml'.";
        assert this.winLabel != null : "fx:id=\"winLabel\" was not injected: check your FXML file 'win_screen.fxml'.";
        this.winLabel.setText(String.format("Player %d Wins!", winner));
        this.newGameButton.setOnMouseClicked(event->goToStartScreen());
    }

    private void goToStartScreen() {
        try {
            // TODO: Make sure this shit works some how
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/layouts/game_screen.fxml"));
            StartScreenController controller = new StartScreenController();
            loader.setController(controller);
            Parent p = loader.load();
            Stage s = (Stage) this.newGameButton.getScene().getWindow();
            s.setScene(new Scene(p, Color.BLACK));
            s.show();
            s.setMaximized(false);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
