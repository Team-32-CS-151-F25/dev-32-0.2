package cs151.application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;


import java.io.IOException;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 400);
        stage.setTitle("Student Hub");
        stage.setScene(scene);

        // sets min window size
        stage.setMinWidth(300);
        stage.setMinHeight(200);
        //stage.setMaxWidth(1200);
        //stage.setMaxHeight(900);

        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}