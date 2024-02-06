package de.hsfulda.onses;

import de.hsfulda.onses.controllers.AppController;
import de.hsfulda.onses.services.GameService;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        final AppController appController = new AppController(new GameService(), stage);

        stage.setTitle("Onses - Uno");
        stage.setScene(new Scene(appController.render()));
        stage.show();
    }
}
