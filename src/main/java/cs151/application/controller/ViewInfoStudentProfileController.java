package cs151.application.controller;

import cs151.application.Student;
import javafx.beans.binding.Bindings;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.Region;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

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

    @FXML
    private TableView<Map.Entry<String, String>> studentInfoTable;
    @FXML
    private TableColumn<Map.Entry<String, String>, String> attributeColumn;
    @FXML
    private TableColumn<Map.Entry<String, String>, String> valueColumn;


    private Student student;


    // would be called when this page is loaded from the table
    public void setStudent(Student student) {
        this.student = student;

        Map<String, String> info = new LinkedHashMap<>();
        info.put("Name", student.getName());
        info.put("Academic Status", student.getAcademicStatus());
        info.put("Job Status", student.getJobStatus());
        info.put("Job Details", student.getJobDetails());
        info.put("Programming Languages", student.getProgrammingLang());
        info.put("Databases", student.getDatabases());
        info.put("Preferred Role", student.getPreferredRole());
        info.put("Flag", student.getFlags());
        info.put("Evaluation", student.getEvaluation());

        ObservableList<Map.Entry<String, String>> items =
                FXCollections.observableArrayList(info.entrySet());

        attributeColumn.setCellValueFactory(
                data -> new SimpleStringProperty(data.getValue().getKey()));
        valueColumn.setCellValueFactory(
                data -> new SimpleStringProperty(data.getValue().getValue()));

        // fill table - tony
        studentInfoTable.setItems(items);

        attributeColumn.setPrefWidth(150);
        valueColumn.setPrefWidth(350);

        // hides table header - tony
        studentInfoTable.skinProperty().addListener((obs, oldSkin, newSkin) -> {
            Node header = studentInfoTable.lookup("TableHeaderRow");
            if (header != null && header.isVisible()) {
                header.setVisible(false);
                if (header instanceof Region region) {
                    region.setMinHeight(0);
                    region.setPrefHeight(0);
                    region.setMaxHeight(0);
                }
            }
        });

        // hides empty rows at the bottom for prettiness - tony
        studentInfoTable.setFixedCellSize(35);
        studentInfoTable.prefHeightProperty().bind(
                studentInfoTable.fixedCellSizeProperty()
                        .multiply(Bindings.size(studentInfoTable.getItems()))
                        .add(2)
        );

        studentInfoTable.setMaxWidth(500);
        studentInfoTable.setPrefWidth(500);
        studentInfoTable.setMinWidth(500);



        /*
        nameLabel.setText("Name: " + student.getName());
        statusLabel.setText("Academic Status: " + student.getAcademicStatus());
        employmentLabel.setText("Job Status: " + student.getJobStatus());
        jobDetailsLabel.setText("Job Details: "+student.getJobDetails());
        languagesLabel.setText("Programming Languages: " + student.getProgrammingLang());
        databasesLabel.setText("Databases: " + student.getDatabases());
        roleLabel.setText("Preferred Role: " + student.getPreferredRole());
        flagLabel.setText("Flag: " + student.getFlags());
        evaluationLabel.setText("Evaluation: " + student.getEvaluation());
         */
    }

    @FXML
    private void onBackClick(ActionEvent event) throws IOException {
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("/cs151/application/view/existingStudentProfiles.fxml"));
        stage.setScene(new Scene(root));
        stage.show();
    }
}