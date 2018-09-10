package com.qa.ui.playlist;

import com.qa.dao.Playlist;
import javafx.fxml.FXML;
import javafx.scene.control.TabPane;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.qa.helper.FXMLHelper.loadFXML;

public class PlaylistTabPane extends TabPane {
    private static final Logger LOGGER = LoggerFactory.getLogger(PlaylistTabPane.class);

    public PlaylistTabPane() {
        super();
        loadFXML("playlistTabPane.fxml", this);
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
