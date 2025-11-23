package cs151.application.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class ReportController {
    private Stage stage;

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

    @FXML
    protected void onHomeButtonClick(ActionEvent event) {
        changeScene(event, "/cs151/application/view/hello-view.fxml");
    }

    @FXML
    public void onReportsButtonClick(ActionEvent event) {
        changeScene(event, "/cs151/application/view/reportMainPage.fxml");
    }

    @FXML
    public void onWhitelistReportClick(ActionEvent event) {
        changeScene(event, "/cs151/application/view/whitelisted.fxml");
    }

    @FXML
    public void onBlacklistReportClick(ActionEvent event) {
        changeScene(event, "/cs151/application/view/blacklisted.fxml");
    }
}
