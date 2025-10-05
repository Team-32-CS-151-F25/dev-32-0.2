package cs151.application;

import javafx.fxml.FXML;
import javafx.scene.control.Label;


// we need a lot of stuff from these so I imported all
import javafx.fxml.*;
import javafx.scene.*;
import javafx.stage.*;
import javafx.event.*;

// errors
import java.io.IOException;


public class MainController {

    private Stage stage;

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
    protected void onHomeButtonClick(ActionEvent event) {
        changeScene(event, "hello-view.fxml");
    }

    // switch to programming language scene
    @FXML
    protected void onProgrammingLanguageButtonClick(ActionEvent event) {
        changeScene(event,"ProgrammingLanguage.fxml");
    }


    protected void changeScene(ActionEvent event, String fxmlFile) {
        try {
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlFile));
            Parent root = loader.load();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}