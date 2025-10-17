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
import java.util.ArrayList;
import java.util.List;

public class StudentProfileController {

    private Stage stage;

    @FXML private ChoiceBox<String> academicStatusChoiceBox;
    @FXML private ChoiceBox<String> professionChoiceBox;
    @FXML private TextField nameTextField;
    @FXML private TextField jobDetailsTextField;
    @FXML private RadioButton employed;
    @FXML private RadioButton unemployed;
    @FXML private Button addStudentButton;
    @FXML private Button addComment;
    @FXML private TextArea studentEvaluationArea;
    @FXML private ListView<CheckBox> programmingLanguages;
    @FXML private ListView<CheckBox> databaseList;;


    private String studentName;
    private String academicStatus;
    private String employmentStatus;
    private String jobDetails;
    private String studentEvaluation;
    private ArrayList<String> programmingLang = new ArrayList<>();
    private ArrayList<String> databaseKnown = new ArrayList<>();
    private String preferredRole;
    private List<CheckBox> languages;
    private List<CheckBox> database;
    private ToggleGroup jobStatus;
    private ToggleGroup flags;


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

        professionChoiceBox.getItems().clear();
        professionChoiceBox.getItems().addAll("Front-End", "Back-End", "Full-Stack",
                "Data", "Other");
        professionChoiceBox.getSelectionModel()
                .selectedItemProperty()
                .addListener((observable, oldValue, newValue) -> {
                    if (newValue != oldValue) {
                        preferredRole = newValue;
                        System.out.println("Preferred Role is " + preferredRole);
                    }
                });

        jobStatus = new ToggleGroup();
        employed.setToggleGroup(jobStatus);
        unemployed.setToggleGroup(jobStatus);

        flags = new ToggleGroup();


        jobDetailsTextField.setDisable(true);

        employed.setOnAction(event -> {
            jobDetailsTextField.setDisable(false);
        });
        unemployed.setOnAction(event -> {
            jobDetailsTextField.setDisable(true);
        });

        programmingLanguages.getItems().clear();
        languages = new ArrayList<>();
        for(String lang : ProgrammingLanguage.getLanguage() ){
            CheckBox languageChoiceBox = new CheckBox(lang);
            languages.add(languageChoiceBox);
        }
        programmingLanguages.getItems().addAll(languages);

        databaseList.getItems().clear();
        database = new ArrayList<>();
        for (String db : new String[]{"MySQL", "Postgres", "MongoDB"} ) {
            CheckBox databaseChoiceBox = new CheckBox(db);
            database.add(databaseChoiceBox);
        }
        databaseList.getItems().addAll(database);



    }
    @FXML
    private void onAddStudent()
    {
        if (jobStatus.getSelectedToggle().equals(employed)) {
            employmentStatus = "Employed";
        }else if (jobStatus.getSelectedToggle().equals(unemployed)){
            employmentStatus = "Unemployed";
        }

        programmingLang.clear();
        for (CheckBox language : languages) {
            if (language.isSelected()) {
                programmingLang.add(language.getText());
                System.out.println("Programming Language is " + programmingLang.toString());
            }
        }

        databaseKnown.clear();
        for (CheckBox db : database) {
            if (db.isSelected()) {
                databaseKnown.add(db.getText());
                System.out.println("Database is " + databaseKnown.toString());
            }
        }

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
    private void onAddCommentButton(){
        this.studentEvaluation = studentEvaluationArea.getText();
        System.out.println("Student evaluation is " + studentEvaluation);
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
