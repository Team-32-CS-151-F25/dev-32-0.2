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

    @FXML
    private Label nameLabel;
    @FXML
    private Label statusLabel;
    @FXML
    private Label employmentLabel;
    @FXML
    private Label jobDetailsLabel;
    @FXML
    private Label languagesLabel;
    @FXML
    private Label databasesLabel;
    @FXML
    private Label roleLabel;
    @FXML
    private Label flagLabel;
    @FXML
    private Label evaluationLabel;

    @FXML
    private TableView<Map.Entry<String, String>> studentInfoTable;
    @FXML
    private TableColumn<Map.Entry<String, String>, String> attributeColumn;
    @FXML
    private TableColumn<Map.Entry<String, String>, String> valueColumn;


    private Student student;
    private Stage stage;

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

    }

    @FXML
    private void onBackClick(ActionEvent event) throws IOException {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("/cs151/application/view/existingStudentProfiles.fxml"));
        stage.setScene(new Scene(root));
        stage.show();
    }

     @FXML
     private void onCommentsClick(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/cs151/application/view/studentComments.fxml"));
        Parent root = loader.load();

        StudentCommentsController controller = loader.getController();
        controller.setStudent(student);

        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.setTitle("Comments for " + student.getName());
        stage.show();
    }

    protected void changeScene(ActionEvent event, String fxmlFile) {
        try{
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

    //navigate to student profile page
    @FXML
    protected void onStudentProfileButtonClick(ActionEvent event){
        changeScene(event, "/cs151/application/view/profileMainPage.fxml");
    }
}