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
import javafx.scene.control.*;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.List;

public class ViewInfoStudentProfileController {

    private Stage stage;

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
        info.put("Evaluation", getAllEvaluationsFor(student.getName()));

        ObservableList<Map.Entry<String, String>> items =
                FXCollections.observableArrayList(info.entrySet());

        // Attribute column
        attributeColumn.setCellValueFactory(data ->
                new SimpleStringProperty(data.getValue().getKey()));

        // Value column with wrapping for Evaluation
        valueColumn.setCellValueFactory(data ->
                new SimpleStringProperty(data.getValue().getValue()));

        // this one is specifically complicated to adjust for the evaluations value - tony
        valueColumn.setCellFactory(col -> new TableCell<>() {
            private final Label lbl = new Label();

            {
                lbl.setWrapText(true);
                lbl.setFont(new Font(14));
                lbl.setMaxWidth(350);
            }

            @Override
            protected void updateItem(String value, boolean empty) {
                super.updateItem(value, empty);

                if (empty || value == null) {
                    setText(null);
                    setGraphic(null);
                    setPrefHeight(35);
                    return;
                }

                String key = getTableView().getItems().get(getIndex()).getKey();

                if ("Evaluation".equals(key)) {
                    lbl.setText(value);

                    // vbox helps height setting
                    VBox vbox = new VBox(lbl);
                    vbox.setPrefWidth(valueColumn.getWidth());
                    vbox.setMinHeight(Region.USE_PREF_SIZE);
                    vbox.setMaxHeight(Region.USE_COMPUTED_SIZE);

                    setGraphic(vbox);
                    setText(null);

                    // force layout so height is correctly calculated
                    vbox.applyCss();
                    vbox.layout();

                    getTableRow().setPrefHeight(lbl.getHeight() + 20); // padding
                } else {
                    setText(value);
                    setGraphic(null);
                    getTableRow().setPrefHeight(35);
                }
            }
        });


        studentInfoTable.setItems(items);

        // Column widths
        attributeColumn.setPrefWidth(150);
        valueColumn.setPrefWidth(350);

        // Hide table header
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

        // fix table width
        studentInfoTable.setMaxWidth(500);
        studentInfoTable.setPrefWidth(500);
        studentInfoTable.setMinWidth(500);

        // set default height for rows that are NOT evaluation - tony
        studentInfoTable.setRowFactory(tv -> new TableRow<>() {
            @Override
            protected void updateItem(Map.Entry<String, String> item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) return;

                if (!"Evaluation".equals(item.getKey())) {
                    setPrefHeight(35);
                }
            }
        });
    }






    @FXML
    private void onBackClick(ActionEvent event) throws IOException {
        changeScene(event, "/cs151/application/view/existingStudentProfiles.fxml");
    }

//    @FXML
//    private void onCommentsClick(ActionEvent event) throws IOException {
//            try{
//            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
//            FXMLLoader loader = new FXMLLoader(getClass().getResource("/cs151/application/view/studentComments.fxml"));
//            Parent root = loader.load();
//
//            StudentCommentsController controller = loader.getController();
//            controller.setStudent(student);
//
//            stage.setScene(new Scene(root));
//            stage.show();
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        }


    private String getAllEvaluationsFor(String studentName) {
        StringBuilder sb = new StringBuilder();

        for (List<String> row : cs151.application.Faculty.getStudentEvaluationRecord()) {
            if (row.get(0).equals(studentName)) {
                if (sb.length() > 0) sb.append("\n");
                sb.append(row.get(1));
            }
        }

        return sb.toString();
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

}