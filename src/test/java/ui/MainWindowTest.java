package ui;

import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.testfx.api.FxRobot;
import org.testfx.api.FxToolkit;
import org.testfx.framework.junit5.ApplicationTest;

import java.io.IOException;

import static com.qa.helper.FXMLHelper.loadParent;

public class MainWindowTest extends FxRobot {

    public static class MediaBrowser extends Application {
        @Override
        public void start(Stage stage) throws IOException {
            final Parent root = loadParent(getClass());
            Scene scene = new Scene(root, 800, 600);
            stage.setScene(scene);
            stage.show();
            stage.setAlwaysOnTop(true);
        }
    }

    @BeforeEach
    void setup() throws Exception {
        ApplicationTest.launch(MediaBrowser.class);
    }

    @AfterEach
    void cleanup() throws Exception {
        FxToolkit.cleanupStages();
    }

    @Test
    void testTabAdd() {

    }
}
