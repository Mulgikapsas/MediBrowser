package com.qa.ui.playlist;

import com.qa.dao.Playlist;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TabPane;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

import static com.qa.helper.FXMLHelper.getResourceBundle;

public class PlaylistTabPane extends TabPane {
    private static final Logger LOGGER = LoggerFactory.getLogger(PlaylistTabPane.class);

    public PlaylistTabPane() {
        super();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getClassLoader().getResource("playlistTabPane.fxml"), getResourceBundle());
        fxmlLoader.setController(this);
        try {
            fxmlLoader.load();
        } catch (final IOException e) {
            LOGGER.error("Failed to load fxml.", e);
        }
    }

    @FXML
    public void initialize() {
        //Adding 1 tab by default
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("Adding initial tab to tab pane.");
        }

        getTabs().add(new PlaylistTab(new Playlist()));
    }
}
