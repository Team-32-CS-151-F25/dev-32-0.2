package cs151.application;

import javafx.fxml.FXML;

// we need a lot of stuff from these so I imported all
import javafx.fxml.*;
import javafx.scene.*;
import javafx.scene.control.TextArea;
import javafx.stage.*;
import javafx.event.*;
import javafx.scene.control.TextField;

// errors
import java.io.IOException;


public class MainController {

    private Stage stage;

    /*
    public void initialize() {
        stage = // get stage somehow
        refreshLanguageList(stage);
    }
    */

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
        String language = langField.getText().trim();
        langField.clear();

        Faculty.setProgrammingLanguage(language);

        // Update the TextArea after adding
        refreshLanguageList(stage);
    }

    @FXML
    private void refreshLanguageList(Stage stage) {
        try {
            TextArea languageList = (TextArea) stage.getScene().lookup("#languageList");
            if (languageList == null) {
                return;
            }
            StringBuilder sb = new StringBuilder();
            for (String lang : Faculty.getProgrammingLanguage()) {
                sb.append(lang.trim()).append("\n");
            }
            languageList.setText(sb.toString());

        } catch (Exception e) {
             System.out.println(e);
             System.out.println("Language list could not be loaded");
        }
    }


    protected void changeScene(ActionEvent event, String fxmlFile) {
        try {
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlFile));
            Parent root = loader.load();
            stage.setScene(new Scene(root));
            stage.show();

            // Only for programminglanguage.fxml scene, update list if it exists
            TextArea languageList = (TextArea) root.lookup("#languageList");
            if (languageList != null) {
                refreshLanguageList(stage);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}