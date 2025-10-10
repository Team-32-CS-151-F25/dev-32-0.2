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
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.PropertyValueFactory;
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
        //refreshLanguageList(stage);
        refreshLanguageTable(stage);
    }

//    @FXML
//    private void refreshLanguageList(Stage stage) {
//        try {
//            TextArea languageList = (TextArea) stage.getScene().lookup("#languageList");
//            if (languageList == null) {
//                return;
//            }
//            StringBuilder sb = new StringBuilder();
//            for (String lang : Faculty.getProgrammingLanguage()) {
//                sb.append(lang.trim()).append("\n");
//            }
//            languageList.setText(sb.toString());
//
//        } catch (Exception e) {
//             System.out.println(e);
//             System.out.println("Language list could not be loaded");
//        }
//    }

    @FXML
    private void refreshLanguageTable(Stage stage) {
        try {
            tableView = (TableView) stage.getScene().lookup("#table");
            ObservableList<String> data = FXCollections.observableArrayList(Faculty.getProgrammingLanguage());
            tableView.setItems(data);

            languageColumn = new TableColumn("Languages");
            languageColumn.setCellValueFactory(names -> new ReadOnlyStringWrapper(names.getValue()));

            tableView.getColumns().setAll(languageColumn);

        } catch (Exception e) {
            //System.out.println(e);
            System.out.println("Language Table could not be loaded");
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
            if (Faculty.getProgrammingLanguage() != null) {
                //refreshLanguageList(stage);
                refreshLanguageTable(stage);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}