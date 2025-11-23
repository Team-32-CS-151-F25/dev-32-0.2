package cs151.application.controller;

import cs151.application.Comment;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

import java.io.IOException;

public class ViewSpecificCommentController {

    private Stage stage;

    @FXML
    TextArea commentView;

    private Comment comment;

    public void setComment(Comment comment) {
        this.comment = comment;
        commentView.setText(comment.getText());
    }

    @FXML
    public void onReportsButtonClick(ActionEvent event) {
        changeScene(event, "/cs151/application/view/reportMainPage.fxml");
    }

    @FXML
    protected void onHomeButtonClick(ActionEvent event) {
        changeScene(event, "/cs151/application/view/hello-view.fxml");
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
