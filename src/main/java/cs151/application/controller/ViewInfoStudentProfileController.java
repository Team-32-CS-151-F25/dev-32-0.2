package cs151.application.controller;

import cs151.application.Student;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import java.io.IOException;

public class ViewInfoStudentProfileController {

    @FXML private Label nameLabel;
    @FXML private Label statusLabel;
    @FXML private Label employmentLabel;
    @FXML private Label languagesLabel;
    @FXML private Label databasesLabel;
    @FXML private Label roleLabel;
    @FXML private Label flagLabel;
    @FXML private Label evaluationLabel;

    private Student student; // your model class

    // called when you load this page from the table
    public void setStudent(Student student) {
        this.student = student;
        nameLabel.setText("Name: " + student.getName());
        statusLabel.setText("Academic Status: " + student.getAcademicStatus());
        employmentLabel.setText("Job Status: " + student.getJobStatus());
        languagesLabel.setText("Programming Languages: " + student.getProgrammingLang());
        databasesLabel.setText("Databases: " + student.getDatabases());
        roleLabel.setText("Preferred Role: " + student.getPreferredRole());
        flagLabel.setText("Flag: " + student.getFlags());
        evaluationLabel.setText("Evaluation: " + student.getEvaluation());
    }

    @FXML
    private void onBackClick(ActionEvent event) throws IOException {
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("/cs151/application/view/existingStudentProfiles.fxml"));
        stage.setScene(new Scene(root));
        stage.show();
    }
}