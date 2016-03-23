package devops.hw1.core;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage stage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("/layouts/start_screen.fxml"));
        stage.setTitle("Evolution!");
        stage.getIcons().add(new Image("/images/start_screen.jpg"));
        stage.setScene(new Scene(root, 500, 625, Color.BLACK));
        stage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
