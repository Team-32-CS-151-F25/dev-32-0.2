package cs151.application;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

// we need a lot of stuff from these so I imported all
import javafx.fxml.*;
import javafx.scene.*;
import javafx.stage.*;

// errors
import java.io.IOException;

public class MainController {
    @FXML
    private Label welcomeText;

    @FXML
    private Label appName;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }

    // change back to homepage
    @FXML
    protected void onHomeButtonClick() {
        changeScene("hello-view.fxml");
    }

    // switch to programming language scene
    @FXML
    protected void onProgrammingLanguageButtonClick() {
        welcomeText.setText("Select Programming Language"); // unnecessary, testing to see if button worked


        changeScene("ProgrammingLanguage.fxml");
    }


    private void changeScene(String fxmlFile) {
        try {
            // load fxml file
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlFile));
            Parent root = loader.load();

            // get the current stage
            Stage stage = (Stage) welcomeText.getScene().getWindow();

            // set the new scene
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            System.out.println("scene change failed");
        }
    }

}