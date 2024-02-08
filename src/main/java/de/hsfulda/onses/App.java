package de.hsfulda.onses;

import de.hsfulda.onses.controllers.AppController;
import de.hsfulda.onses.controllers.Controller;
import de.hsfulda.onses.services.GameService;
import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;

public class App extends Application {

    private Stage stage;
    private Controller controller;
    @Override
    public void start(Stage stage) {
        this.stage = stage;
        final AppController appController = new AppController(this, new GameService());

        stage.setScene(new Scene(new Label("Loading...")));
        stage.setOnCloseRequest(e -> controller.destroy());

        show(appController);
        stage.show();
    }

    public void show(Controller controller) {
        try {
            final Parent parent = controller.render();
            stage.getScene().setRoot(parent);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        if(this.controller != null) {
            this.controller.destroy();
        }
        this.controller = controller;
        stage.setTitle(controller.getTitle());
    }
}
