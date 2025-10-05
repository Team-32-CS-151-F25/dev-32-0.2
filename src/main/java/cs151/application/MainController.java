package cs151.application;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;

public class MainController {
    @FXML
    private Label welcomeText;

    @FXML
    private Label appName;

    @FXML
    private Button programmingLangButton;


    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }

    @FXML
    protected void onAddLanguageClick() {
        //
    }

    @FXML
    protected void onProgrammingLangButtonClick() throws IOException {
        FXMLLoader fxmlLoader1 = new FXMLLoader(Main.class.getResource("programmingLanguages-view.fxml"));
        Scene scene1 = new Scene(fxmlLoader1.load(), 600, 700);
        Stage stage1 = (Stage) programmingLangButton.getScene().getWindow();
        stage1.setScene(scene1);
        stage1.show();
    }
}