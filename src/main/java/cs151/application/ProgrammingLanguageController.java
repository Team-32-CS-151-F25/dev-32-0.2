package cs151.application;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.scene.*;

import java.io.IOException;

public class ProgrammingLanguageController{

    @FXML
    private TextField languageNameInput;

    @FXML
    private TextArea languageList;

    private ProgrammingLanguage programLang = new ProgrammingLanguage();

    @FXML
    public void onAddLanguageClick() throws IOException {
        String langName = languageNameInput.getText();

        if (!langName.isEmpty()){
            programLang.setLanguage(langName);
            languageList.appendText(langName + "\n");
        }

        languageNameInput.clear();
    }
}
