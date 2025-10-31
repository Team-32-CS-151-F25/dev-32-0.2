package cs151.application.controller;

import cs151.application.Faculty;
import cs151.application.Student;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;

public class ExistingStudentProfileController {

    private Stage stage;

    @FXML private TableView studentTableView;
    @FXML private TableColumn<String, String> studentNameColumn;
    @FXML private TableColumn<String, String> academicStatusColumn;
    @FXML private TableColumn<String, String> jobColumn;
    @FXML private TableColumn<String, String> jobDetailsColumn;
    @FXML private TableColumn<String, String> programmingLangColumn;
    @FXML private TableColumn<String, String> databasesColumn;
    @FXML private TableColumn<String, String> preferredRoleColumn;
    @FXML private TableColumn<String, String> flagsColumn;
    @FXML private TableColumn<String, String> evaluationColumn;

    private ObservableList<Student> students;

    //initialize method
    //get the data from filedata in parser and add it
    public void initialize(){

        List<List<String>> profileData = Faculty.getStudentProfileRecord();
        List<List<String>> skillData = Faculty.getStudentSkillsRecord();
        List<List<String>> evaluationData = Faculty.getStudentEvaluationRecord();
        List<List<String>> flagData = Faculty.getStudentFlagsRecord();

        String name = "";
        String academicStatus = "";
        String jobStatus = "";
        String jobDetails = "";
        String programmingLang = "";
        String databases = "";
        String preferredRole = "";
        String flags = "";
        String evaluation = "";

        students = FXCollections.observableArrayList();

        for (int i = 0; i < profileData.size() ; i++) {
            name = (profileData.get(i).get(0));
            academicStatus = (profileData.get(i).get(1));
            jobStatus = (profileData.get(i).get(2));
            jobDetails = (profileData.get(i).get(3));

            String programmingLangStr = "";
            for (int j = 2; j <  skillData.get(i).indexOf("Databases"); j++) {
                programmingLangStr += skillData.get(i).get(j);
                if(j < skillData.get(i).indexOf("Databases") - 1 ) {
                    programmingLangStr += ",";
                }
            }
            programmingLang = (programmingLangStr);

            String databaseStr = "";
            for (int j = skillData.get(i).indexOf("Databases") + 1; j <  skillData.get(i).indexOf("Role"); j++) {
                databaseStr += skillData.get(i).get(j);
                if(j < skillData.get(i).indexOf("Role") - 1 ) {
                    databaseStr += ",";
                }
            }
            databases = (databaseStr);
            preferredRole = (skillData.get(i).get((skillData.get(i).indexOf("Role")+1)));

            flags = (flagData.get(i).get(1));
            evaluation = (evaluationData.get(i).get(1));

            students.add(new Student(name, academicStatus, jobStatus, jobDetails,
                    programmingLang, databases, preferredRole,
                    flags, evaluation));

        }

        studentTableView.setItems(students);

        studentNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        academicStatusColumn.setCellValueFactory(new PropertyValueFactory<>("academicStatus"));
        jobColumn.setCellValueFactory(new PropertyValueFactory<>("jobStatus"));
        jobDetailsColumn.setCellValueFactory(new PropertyValueFactory<>("jobDetails"));
        programmingLangColumn.setCellValueFactory(new PropertyValueFactory<>("programmingLang"));
        databasesColumn.setCellValueFactory(new PropertyValueFactory<>("databases"));
        preferredRoleColumn.setCellValueFactory(new PropertyValueFactory<>("preferredRole"));
        flagsColumn.setCellValueFactory(new PropertyValueFactory<>("flags"));
        evaluationColumn.setCellValueFactory(new PropertyValueFactory<>("evaluation"));

    }

    @FXML
    protected void onHomeButtonClick(ActionEvent event) {
        changeScene(event, "/cs151/application/view/hello-view.fxml");
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

    //change back to the student profile page
    @FXML
    protected void onBackToProfilePageButtonClick(ActionEvent event){
        changeScene(event, "/cs151/application/view/profileMainPage.fxml");
    }

}
