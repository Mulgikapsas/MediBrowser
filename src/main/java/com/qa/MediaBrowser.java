package com.qa;

import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.qa.helper.FXMLHelper.getTranslatedString;
import static com.qa.helper.FXMLHelper.loadParent;

public class MediaBrowser extends Application {
    private static final Logger LOGGER = LoggerFactory.getLogger(MediaBrowser.class);

    @Override
    public void start(final Stage primaryStage) throws Exception {
        final Parent root = loadParent(getClass());
        primaryStage.setTitle(getTranslatedString("app.title"));
        primaryStage.setScene(new Scene(root, 600, 475));
        primaryStage.show();
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("Launching primary stage.");
        }

    }

    public static void main(String[] args) {
        launch(args);
    }
}
