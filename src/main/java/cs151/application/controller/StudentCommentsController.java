package cs151.application.controller;

import cs151.application.Student;
import cs151.application.Comment;
import cs151.application.CSVParser;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.event.ActionEvent;
import javafx.stage.Stage;
import javafx.scene.Node;

import java.io.IOException;
import java.time.LocalDate;
import java.util.*;

public class StudentCommentsController {

    private Stage stage;

    @FXML private Label studentNameLabel;
    @FXML private TableView<Comment> commentsTable;
    @FXML private TableColumn<Comment, String> commentColumn;
    @FXML private TableColumn<Comment, LocalDate> dateColumn;
    @FXML private TextArea commentInput;

    private Student student;
    private ObservableList<Comment> commentsList;

    private static final String COMMENT_FILE = "src/main/resources/data/comments.csv";

    public void setStudent(Student student) {
        this.student = student;
        studentNameLabel.setText("Comments for: " + student.getName());

        // Load comments from CSV
        commentsList = FXCollections.observableArrayList(loadComments(student.getName()));

        commentColumn.setCellValueFactory(c -> new javafx.beans.property.SimpleStringProperty(c.getValue().getText()));
        dateColumn.setCellValueFactory(c -> new javafx.beans.property.SimpleObjectProperty<>(c.getValue().getDate()));

        commentsTable.setItems(commentsList);
    }

    @FXML
    private void onSaveCommentClick() {
        String text = commentInput.getText().trim();
        if (text.isEmpty()) {
            showAlert("Comment cannot be empty.");
            return;
        }

        Comment newComment = new Comment(text, LocalDate.now());

        // Save comment to CSV
        saveComment(student.getName(), newComment);

        // Add to TableView
        commentsList.add(newComment);
        commentInput.clear();
    }

    private List<Comment> loadComments(String studentName) {
        List<Comment> list = new ArrayList<>();
        try {
            CSVParser parser = new CSVParser(COMMENT_FILE);
            String[] allData = parser.getData(); // flattened list of all CSV values
            for (int i = 0; i < allData.length; i += 3) {
                if (allData[i].equals(studentName)) {
                    String text = allData[i + 1];
                    String date = allData[i + 2];
                    list.add(new Comment(text, LocalDate.parse(date)));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    private void saveComment(String studentName, Comment comment) {
        try {
            CSVParser parser = new CSVParser(COMMENT_FILE);
            String line = studentName + "," + comment.getText() + "," + comment.getDate();
            parser.setData(line);
            parser.addNewLine();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void onBackClick(ActionEvent event) {

        changeScene(event, "/cs151/application/view/studentSearch.fxml");
    }
    //shows warning message if comment is empty
    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setContentText(message);
        alert.show();
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
