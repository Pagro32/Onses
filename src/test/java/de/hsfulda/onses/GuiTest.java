package de.hsfulda.onses;

import javafx.stage.Stage;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.testfx.api.FxAssert;
import org.testfx.framework.junit5.ApplicationTest;
import static org.junit.jupiter.api.Assertions.*;
import static org.testfx.matcher.control.LabeledMatchers.*;

public class GuiTest extends ApplicationTest {
    private Stage stage;
    private App app;

    @Override
    public void start(Stage stage) throws Exception {
        this.stage = stage;
        app = new App();
        app.start(stage);
    }

    @Test
    @DisplayName("Check Window Title")
    void checkWindowsTitle() {
        assertEquals("Onses - Uno", stage.getTitle());
    }

    @Test
    @DisplayName("Check if window Switch is working")
    void checkSceneSwitch() {
        clickOn("#startGameBtn");
        assertEquals("Onses - Uno Game", stage.getTitle());
    }
}
