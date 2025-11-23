package cs151.application.controller;

import cs151.application.Student;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

import java.io.IOException;

public class ReportController {
    private Stage stage;
    @FXML private TextField searchEntry;
    @FXML private TableView<Student> studentTableView;
    private ObservableList<Student> students;
    private ObservableList<Student> matchedStudents;


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

    @FXML
    protected void onHomeButtonClick(ActionEvent event) {
        changeScene(event, "/cs151/application/view/hello-view.fxml");
    }

    @FXML
    public void onReportsButtonClick(ActionEvent event) {
        changeScene(event, "/cs151/application/view/reportMainPage.fxml");
    }

    @FXML
    public void onWhitelistReportClick(ActionEvent event) {
        changeScene(event, "/cs151/application/view/whitelisted.fxml");
    }

    @FXML
    public void onBlacklistReportClick(ActionEvent event) {
        changeScene(event, "/cs151/application/view/blacklisted.fxml");
    }

    @FXML
    protected void onSearchFieldEnter(KeyEvent event) {

        searchStudent();
    }

    @FXML
    private void searchStudent() {
        String searchText = searchEntry.getText();
        if(searchText.isEmpty()) {
            studentTableView.setItems(students);
            return;
        }

        searchText = searchText.trim();
        String[] searchTexts = searchText.split(" ", -1);


        matchedStudents = FXCollections.observableArrayList();
        for (Student student : students) {
            String allText;
            allText = student.getName() + student.getAcademicStatus() + student.getJobStatus() +
                    student.getJobDetails() + student.getProgrammingLang() + student.getDatabases() +
                    student.getPreferredRole() + student.getFlags() + student.getEvaluation();
            for(String searchItem : searchTexts) {
                if (allText.toLowerCase().contains(searchItem.toLowerCase())) {
                    if(!matchedStudents.contains(student))
                        matchedStudents.add(student);
                }
            }
        }
        studentTableView.setItems(matchedStudents);


    }

}
