package com.Evolution;

/**
 * Main class for running the game
 * Created by brownba1 on 3/21/2016.
 */
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/layouts/start_screen.fxml"));
        stage.setTitle("Evolution!");
        stage.getIcons().add(new Image("/images/icon.png"));
        stage.setScene(new Scene(root, Color.BLACK));

        stage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
