package com.qa.ui;

import com.qa.dao.Playlist;
import com.qa.ui.file.FileImportPane;
import com.qa.ui.playlist.PlaylistTab;
import com.qa.ui.playlist.PlaylistTabPane;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.layout.VBox;
import javafx.scene.media.MediaView;
import javafx.stage.Modality;

import static com.qa.helper.FXMLHelper.loadNewWindow;

public class MainMenu extends VBox {
    @FXML
    private MediaView mediaView;
    @FXML
    private PlaylistTabPane playlistTabPane;

    public MainMenu() {
        super();
    }

    @FXML
    public void initialize() {
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
}
