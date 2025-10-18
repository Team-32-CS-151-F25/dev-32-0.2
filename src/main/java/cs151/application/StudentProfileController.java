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
import java.util.Objects;

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
    @FXML private CheckBox whitelist;
    @FXML private CheckBox blacklist;
    @FXML private TextArea studentEvaluationArea;
    @FXML private ListView<CheckBox> programmingLanguages;
    @FXML private ListView<CheckBox> databaseList;;
    private List<CheckBox> languages;
    private List<CheckBox> database;
    private ToggleGroup jobStatus;


    private String studentName = "";
    private String academicStatus = "";
    private String employmentStatus = "";
    private String jobDetails = "N/A";
    private String studentEvaluation = "";
    private ArrayList<String> programmingLang = new ArrayList<>();
    private ArrayList<String> databaseKnown = new ArrayList<>();
    private String preferredRole = "";
    private String flag = "";


    @FXML
    public void initialize(){
        //populate the academic status choicebox with the hard-coded data
        academicStatusChoiceBox.getItems().clear();
        academicStatusChoiceBox.getItems().addAll("Freshman", "Sophomore", "Junior", "Senior", "Graduate");
        //use the selection model and event listner to update the academic status value when selection is made
        academicStatusChoiceBox.getSelectionModel()
                .selectedItemProperty()
                .addListener((observable, oldValue, newValue) -> {
                    if (newValue != oldValue) {
                        academicStatus = newValue;
                        System.out.println("Academic Status is " + academicStatus);
                    }
                });

        //Populate the preferred profession choice box
        professionChoiceBox.getItems().clear();
        professionChoiceBox.getItems().addAll("Front-End", "Back-End", "Full-Stack",
                "Data", "Other");

        //use a event listener to update the value when selection is made
        professionChoiceBox.getSelectionModel()
                .selectedItemProperty()
                .addListener((observable, oldValue, newValue) -> {
                    if (newValue != oldValue) {
                        preferredRole = newValue;
                        System.out.println("Preferred Role is " + preferredRole);
                    }
                });

        //add the radio buttons for job status to a toggle box to make them mutually exclusive
        jobStatus = new ToggleGroup();
        employed.setToggleGroup(jobStatus);
        unemployed.setToggleGroup(jobStatus);

        //job details is initially disabled
        jobDetailsTextField.setDisable(true);

        //enable the job detail text field if employed is selected and disable it if unemployed
        employed.setOnAction(event -> {
            jobDetailsTextField.setDisable(false);
        });
        unemployed.setOnAction(event -> {
            jobDetailsTextField.setDisable(true);
        });

        //clear just to make sure that there are no duplicates
        programmingLanguages.getItems().clear();
        languages = new ArrayList<>();
        //get the languages from the programming Language page and populate the values in the list
        for(String lang : ProgrammingLanguage.getLanguage() ){
            //checkbox is added to list so user can select multiple items from the list
            CheckBox languageChoiceBox = new CheckBox(lang);
            languages.add(languageChoiceBox);
        }
        //add the checkboxes to the ListView of ProgrammingLanguages
        programmingLanguages.getItems().addAll(languages);

        //similar code as above for databases except the options hard-coded
        databaseList.getItems().clear();
        database = new ArrayList<>();
        for (String db : new String[]{"MySQL", "Postgres", "MongoDB"} ) {
            CheckBox databaseChoiceBox = new CheckBox(db);
            database.add(databaseChoiceBox);
        }
        databaseList.getItems().addAll(database);

        //use an action event detection to make the whitelist and blacklist checkbox mutually exclusive
        whitelist.setOnAction(event -> {
            if(whitelist.isSelected())
            {blacklist.setSelected(false);}
        });
        blacklist.setOnAction(event -> {
            if(blacklist.isSelected()){
                whitelist.setSelected(false);
            }
        });


    }
    @FXML
    private void onAddStudent()
    {
        //studentName,academicStatus,employmentStatus,jobDetails,
        // programmingLang[],databaseKnown[],preferredRole,
        // studentEvaluation flags

        //get the student name from the textField
        if(!nameTextField.getText().isEmpty()) {
            this.studentName = nameTextField.getText();
            this.studentName = studentName.trim();  //trim to remove leading and trailing whitespaces
        }

        //academicStatus is added on initialize method and updates automatically with every selection

        //get the employmentStatus and set the value based on which option was selected
        if(jobStatus.getSelectedToggle() != null) {
            if (jobStatus.getSelectedToggle().equals(employed)) {
                employmentStatus = "Employed";
            } else if (jobStatus.getSelectedToggle().equals(unemployed)) {
                employmentStatus = "Unemployed";
            }
        }

        //get the job details from its textField
        this.jobDetails = jobDetailsTextField.getText();

        //get the student evaluation from its textArea
        this.studentEvaluation = studentEvaluationArea.getText();

        //clear the programming language to reflect the latest selection
        programmingLang.clear();
        //parse through the checkboxes in the ListView and check whether they are selected or not
        for (CheckBox language : languages) {
            if (language.isSelected()) {
                //if they are selected add them to the programmingLang list
                programmingLang.add(language.getText());
                System.out.println("Programming Language is " + programmingLang.toString());
            }
        }

        //do the same for the databases
        databaseKnown.clear();
        for (CheckBox db : database) {
            if (db.isSelected()) {
                databaseKnown.add(db.getText());
                System.out.println("Database is " + databaseKnown.toString());
            }
        }

        //preferred role is added on initialize method and updates automatically with every selection

        //set the value based on which flag is selected
        if(whitelist.isSelected())
            flag = "Whitelist";
        else if(blacklist.isSelected())
            flag = "Blacklist";
        else
            flag = "None";

        //check to make sure that user has entered all the required values
        if(studentName == null || studentName.isEmpty())
            showAlert("Missing Name", "Please enter the student's name");

        if(academicStatus == null || academicStatus.isEmpty())
            showAlert("Missing Academic Status", "Please select the academic status");

        if(Objects.equals(employmentStatus, "Employed") && jobDetails == "N/A")
            showAlert("Missing Job Details", "Please enter the job details.");

        if(programmingLang.isEmpty())
            showAlert("Missing Programming Language", "Select known Programming Languages for the student.");

        if(databaseKnown.isEmpty()){
            showAlert("Missing Database", "Select known Database for the student.");
        }

        if(preferredRole == null || preferredRole.isEmpty()) {
            showAlert("Missing Preferred Role", "Please select the preferred role.");
        }

        //once user has added all the required values then add the data to the respective files

        try {
            if( !studentName.isEmpty() && !academicStatus.isEmpty() && !employmentStatus.isEmpty()
                    && !programmingLang.isEmpty() && !databaseKnown.isEmpty() && !preferredRole.isEmpty()) {
                if (!Faculty.matchName(studentName.trim())) {
                    Faculty.setStudentProfile(studentName, academicStatus, employmentStatus, jobDetails);
                    Faculty.setStudentSkills(programmingLang.toArray(new String[0]), databaseKnown.toArray(new String[0]), preferredRole);
                    Faculty.setStudentEval(studentEvaluation);
                    //need to update the code for faculty to change flags
                    Faculty.setStudentFlag(flag);
                } else
                    showAlert("Duplicate Data", "Student Name Already Exists");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    @FXML
    private void onNameEntered(){
//        this.studentName = nameTextField.getText();
//        this.studentName = studentName.trim();
//        System.out.println("Student name is " + studentName);
    }

    @FXML
    protected void onJobDetailsEntered(){
//        this.jobDetails = jobDetailsTextField.getText();
//        System.out.println("Job status is " + employmentStatus);
//        System.out.println("Job details is " + jobDetails);
    }

    @FXML
    private void onAddCommentButton(){
//        this.studentEvaluation = studentEvaluationArea.getText();
//        System.out.println("Student evaluation is " + studentEvaluation);

        //Faculty needs to be able to add commments later on as well
    }


    @FXML
    protected void onHomeButtonClick(ActionEvent event) {
        changeScene(event, "hello-view.fxml");
    }

    private void showAlert(String title, String message){
        //shows a alert to user using javafx Alert class
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle(title);
        alert.setHeaderText("");
        alert.setContentText(message);
        alert.showAndWait();
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
