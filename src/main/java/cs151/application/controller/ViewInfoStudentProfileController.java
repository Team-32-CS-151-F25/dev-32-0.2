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
    @FXML private Label jobDetailsLabel;
    @FXML private Label languagesLabel;
    @FXML private Label databasesLabel;
    @FXML private Label roleLabel;
    @FXML private Label flagLabel;
    @FXML private Label evaluationLabel;

    private Student studentInfo;


    // would be called when this page is loaded from the table
    public void setStudent(Student studentInfo) {
        this.studentInfo = studentInfo;
        nameLabel.setText("Name: " + studentInfo.getName());
        statusLabel.setText("Academic Status: " + studentInfo.getAcademicStatus());
        employmentLabel.setText("Job Status: " + studentInfo.getJobStatus());
        jobDetailsLabel.setText("Job Details: "+studentInfo.getJobDetails());
        languagesLabel.setText("Programming Languages: " + studentInfo.getProgrammingLang());
        databasesLabel.setText("Databases: " + studentInfo.getDatabases());
        roleLabel.setText("Preferred Role: " + studentInfo.getPreferredRole());
        flagLabel.setText("Flag: " + studentInfo.getFlags());
        evaluationLabel.setText("Evaluation: " + studentInfo.getEvaluation());
    }

    @FXML
    private void onBackClick(ActionEvent event) throws IOException {
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("/cs151/application/view/existingStudentProfiles.fxml"));
        stage.setScene(new Scene(root));
        stage.show();
    }
}