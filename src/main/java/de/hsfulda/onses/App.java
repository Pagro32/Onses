package de.hsfulda.onses;

import de.hsfulda.onses.controllers.AppController;
import de.hsfulda.onses.models.Game;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        final AppController appController = new AppController(new Game());

        stage.setTitle("Onses - Uno Game");
        stage.setScene(new Scene(appController.render()));
        stage.show();
    }
}
