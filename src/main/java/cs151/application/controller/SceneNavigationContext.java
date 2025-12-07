package cs151.application.controller;

import javafx.event.ActionEvent;

public class SceneNavigationContext{

    private SceneNavigable navigator;

    public SceneNavigationContext(SceneNavigable navigator) {
        this.navigator = navigator;
    }

    public void onHomeButtonClick(ActionEvent event) {
        navigator.changeScene(event, "/cs151/application/view/hello-view.fxml");
    }

}
