package cs151.application;

import javafx.beans.property.ReadOnlyStringProperty;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;

// we need a lot of stuff from these so I imported all
import javafx.fxml.*;
import javafx.scene.*;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.*;
import javafx.event.*;
import javafx.scene.control.TextField;

// errors
import java.io.IOException;


public class MainController {

    private Stage stage;

    @FXML
    private TableView tableView;
    @FXML
    private TableColumn<String, String> languageColumn;

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

    //navigate to student profile page
    @FXML
    protected void onStudentProfileButtonClick(ActionEvent event){
        changeScene(event, "profileMainPage.fxml");
    }

    @FXML
    protected void onCreateNewProfileClick(ActionEvent event){
        changeScene(event, "studentProfile.fxml");
    }

    @FXML
    protected void onExistingProfileClick(ActionEvent event){
        changeScene(event, "existingStudentProfiles.fxml");
    }

    @FXML
    protected void onAddLanguageClick(ActionEvent event) throws IOException {
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        TextField langField = (TextField) stage.getScene().lookup("#languageNameInput");
        String language = langField.getText().trim();
        langField.clear();

        Faculty.setProgrammingLanguage(language);

        // Update the TextArea after adding
        refreshLanguageTable(stage);
    }

    @FXML
    private void refreshLanguageTable(Stage stage) {
        try {
            tableView = (TableView) stage.getScene().lookup("#table");
            ObservableList<String> data = FXCollections.observableArrayList(Faculty.getProgrammingLanguage());
            tableView.setItems(data);

            languageColumn = new TableColumn("Languages");
            //sets the language column to update the value based on the return of names.getValue()
            languageColumn.setCellValueFactory(names -> new ReadOnlyStringWrapper(names.getValue()));

            tableView.getColumns().setAll(languageColumn);

        } catch (Exception e) {
            System.out.println("Language Table could not be loaded");
        }
    }

    protected void changeScene(ActionEvent event, String fxmlFile) {
        try {

            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

            // window size - tony
            double currentWidth = stage.getWidth();
            double currentHeight = stage.getHeight();

            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlFile));
            Parent root = loader.load();
            stage.setScene(new Scene(root, currentWidth, currentHeight));
            stage.show();

            // Only for programminglanguage.fxml scene, update list if it exists
            if ("ProgrammingLanguage.fxml".equals(fxmlFile)) {
                refreshLanguageTable(stage);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}