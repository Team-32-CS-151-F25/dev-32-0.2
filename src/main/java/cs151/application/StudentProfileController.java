package cs151.application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;

public class StudentProfileController {

    private Stage stage;

    @FXML private ChoiceBox<String> academicStatusChoiceBox;
    @FXML private TextField nameTextField;
    @FXML private TextField jobDetailsTextField;
    @FXML private RadioButton employed;
    @FXML private RadioButton unemployed;
    @FXML private Button addStudentButton;

    private String studentName;
    private String academicStatus;
    private String employmentStatus;
    private String jobDetails;



    @FXML
    public void initialize(){
        academicStatusChoiceBox.getItems().clear();
        academicStatusChoiceBox.getItems().addAll("Freshman", "Sophomore", "Junior", "Senior", "Graduate");
        academicStatusChoiceBox.getSelectionModel()
                .selectedItemProperty()
                .addListener((observable, oldValue, newValue) -> {
                    if (newValue != oldValue) {
                        academicStatus = newValue;
                        System.out.println("Academic Status is " + academicStatus);
                    }
                });

        ToggleGroup jobStatus = new ToggleGroup();
        employed.setToggleGroup(jobStatus);
        unemployed.setToggleGroup(jobStatus);

        jobDetailsTextField.setDisable(true);

        employed.setOnAction(event -> {
            jobDetailsTextField.setDisable(false);
        });
        unemployed.setOnAction(event -> {
            jobDetailsTextField.setDisable(true);
        });

        if (jobStatus.getSelectedToggle().equals(employed)) {
            employmentStatus = "Employed";
        }else if (jobStatus.getSelectedToggle().equals(unemployed)){
            employmentStatus = "Unemployed";
        }

    }
    @FXML
    private void onAddStudent()
    {

    }

    @FXML
    private void onNameEntered(){
        this.studentName = nameTextField.getText();
        System.out.println("Student name is " + studentName);
    }

    @FXML
    protected void onJobDetailsEntered(){
        this.jobDetails = jobDetailsTextField.getText();
        System.out.println("Job status is " + employmentStatus);
        System.out.println("Job details is " + jobDetails);
    }

    @FXML
    protected void onHomeButtonClick(ActionEvent event) {
        changeScene(event, "hello-view.fxml");
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
}
