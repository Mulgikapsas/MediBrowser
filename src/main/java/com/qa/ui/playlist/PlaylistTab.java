package com.qa.ui.playlist;

import com.qa.dao.MediaFile;
import com.qa.dao.Playlist;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

import static com.qa.helper.FXMLHelper.getResourceBundle;

public class PlaylistTab extends Tab {
    private static final Logger LOGGER = LoggerFactory.getLogger(PlaylistTab.class);

    private Playlist playlist;
    private Label playlistName = new Label();

    @FXML
    private TableView<MediaFile> playlistTableView;
    @FXML
    private TableColumn<MediaFile, String> fileNameColumn;
    @FXML
    private TableColumn<MediaFile, String> filePathColumn;
    @FXML
    private TableColumn<MediaFile, String> mediaFileTypeColumn;
    @FXML
    private TableColumn<MediaFile, String> mediaTypeColumn;

    public PlaylistTab(final Playlist playlist) {
        super();
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("Initializing playlist tab. Name: " + playlist.getName());
        }
        this.playlist = playlist;

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getClassLoader().getResource("playlistTab.fxml"), getResourceBundle());
        fxmlLoader.setController(this);
        try {
            fxmlLoader.load();
        } catch (final IOException e) {
            LOGGER.error("Failed to load fxml.", e);
        }
    }

    @FXML
    public void initialize() {
        //Initialize table view
        fileNameColumn.setCellValueFactory(param -> new SimpleStringProperty(param.getValue().getFileName().getName()));
        filePathColumn.setCellValueFactory(param -> new SimpleStringProperty(param.getValue().getFilePath().getPath()));
        mediaFileTypeColumn.setCellValueFactory(param -> new SimpleStringProperty(param.getValue().getMediaFileType().toString()));
        mediaTypeColumn.setCellValueFactory(param -> new SimpleStringProperty(param.getValue().getMediaFileType().getContentType().toString()));
        playlistTableView.setItems(playlist.getMediaFiles());

        //Set initial values
        playlistName.setText(playlist.getName());
        setGraphic(playlistName);
        setContent(playlistTableView);

        final TextField textField = new TextField();
        textField.setMaxWidth(60);

        //Start edit on double click
        playlistName.setOnMouseClicked(event -> {
            if (event.getClickCount() == 2) {
                textField.setText(playlistName.getText());
                setGraphic(textField);
                textField.selectAll();
                textField.requestFocus();
            }
        });

        //Update value on focus out if it is not blank
        textField.focusedProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue) {
                final String newText = textField.getText();
                if (StringUtils.isBlank(newText)) {
                    playlistName.setText(playlist.getName());
                } else {
                    playlistName.setText(newText);
                    playlist.setName(playlistName.getText());
                }
                setGraphic(playlistName);
            }
        });
    }
}
