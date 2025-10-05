package cs151.application;

import javafx.fxml.FXML;
import javafx.scene.control.Label;


// we need a lot of stuff from these so I imported all
import javafx.fxml.*;
import javafx.scene.*;
import javafx.stage.*;
import javafx.event.*;
import javafx.scene.control.TextField;



// errors
import java.io.IOException;


public class MainController {

    private Stage stage;

    @FXML
    private Label welcomeText;

    @FXML
    private Label appName;

    public CSVParser parser = new CSVParser("src/main/resources/cs151/application/languages.csv");

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

    @FXML
    protected void onAddLanguageClick(ActionEvent event) throws IOException {
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        TextField langField = (TextField) stage.getScene().lookup("#languageNameInput");
        String language =  langField.getText();
         // testing if method works
        if (!parser.exists(language)) {
            parser.setData(language);
            System.out.println("Added language: " + language);
        }


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