package com.qa.ui;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.qa.dao.Playlist;
import com.qa.ui.file.FileImportPane;
import com.qa.ui.playlist.PlaylistTab;
import com.qa.ui.playlist.PlaylistTabPane;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Tab;
import javafx.scene.layout.VBox;
import javafx.scene.media.MediaView;
import javafx.stage.Modality;
import org.controlsfx.control.Notifications;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import static com.qa.helper.FXMLHelper.getTranslatedString;
import static com.qa.helper.FXMLHelper.loadNewWindow;

public class MainMenu extends VBox {
    private static final Logger LOGGER = LoggerFactory.getLogger(MainMenu.class);
    public static final String PLAYLIST_SAVE_FILE_PATH = "C:/MediaBrowser.json";

    @FXML
    private MediaView mediaView;
    @FXML
    private PlaylistTabPane playlistTabPane;

    public MainMenu() {
        super();
    }

    @FXML
    public void initialize() {
        restorePlaylists();
    }

    /**
     * This method restores application state from {@code PLAYLIST_SAVE_FILE_PATH} file.
     */
    private void restorePlaylists() {
        final File file = new File(PLAYLIST_SAVE_FILE_PATH);
        if (file.exists()) {
            try (final BufferedReader reader = new BufferedReader(new FileReader(file))) {
                final StringBuilder stringBuilder = new StringBuilder();
                String line = null;
                while ((line = reader.readLine()) != null) {
                    stringBuilder.append(line);
                }

                final List<Playlist> loadedPlaylists = new ObjectMapper().readValue(stringBuilder.toString(), new TypeReference<List<Playlist>>() {
                });
                final ObservableList<Tab> playlistTabs = playlistTabPane.getTabs();
                playlistTabs.clear();
                for (final Playlist loadedPlaylist : loadedPlaylists) {
                    playlistTabs.add(new PlaylistTab(loadedPlaylist));
                }
                if (LOGGER.isDebugEnabled()) {
                    LOGGER.debug("Loaded " + loadedPlaylists.size() + " playlist(s).");
                }

            } catch (final IOException e) {
                LOGGER.error("Failed to load playlist(s) from file.", e);
                Notifications.create()
                        .title(getTranslatedString("notification.warning"))
                        .text(getTranslatedString("notification.playlist.load.fail.warning"))
                        .showWarning();
            }
        }
    }

    @FXML
    private void addNewTab(final ActionEvent actionEvent) {
        playlistTabPane.getTabs().add(new PlaylistTab(new Playlist()));
    }

    @FXML
    private void showFileImportScreen(final ActionEvent actionEvent) {
        final FileImportPane fileImportPane = loadNewWindow("fileImportPane.fxml",
                250, 300,
                Modality.WINDOW_MODAL,
                playlistTabPane.getScene().getWindow(),
                getClass(), false);
        fileImportPane.setSelectedTab((PlaylistTab) playlistTabPane.getSelectionModel().getSelectedItem());
    }

    @FXML
    public void saveData(final ActionEvent actionEvent) {
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("Saving playlists.");
        }
        final ObjectMapper mapper = new ObjectMapper();
        final ObjectWriter writer = mapper.writer(new DefaultPrettyPrinter());
        final List<Playlist> playlists = playlistTabPane.getTabs().stream()
                .map(tab -> ((PlaylistTab) tab).getPlaylist())
                .collect(Collectors.toList());
        boolean hadException = false;
        try {
            writer.writeValue(new File(PLAYLIST_SAVE_FILE_PATH), playlists);
        } catch (final IOException e) {
            hadException = true;
            LOGGER.error("Failed to save playlists.", e);
            Notifications.create()
                    .title(getTranslatedString("notification.warning"))
                    .text(getTranslatedString("notification.playlist.save.warning"))
                    .showWarning();
        } finally {
            if (!hadException) {
                Notifications.create()
                        .title(getTranslatedString("notification.info"))
                        .text(getTranslatedString("notification.playlist.successful.save.info"))
                        .showConfirm();
            }
        }
    }
}
