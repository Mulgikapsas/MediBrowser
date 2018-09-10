package com.qa.ui;

import com.qa.dao.Category;
import com.qa.dao.FileName;
import com.qa.dao.FilePath;
import com.qa.dao.MediaFile;
import com.qa.dao.MediaFileType;
import com.qa.dao.Playlist;
import com.qa.ui.playlist.PlaylistTab;
import com.qa.ui.playlist.PlaylistTabPane;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.layout.VBox;
import javafx.scene.media.MediaView;

import java.util.Collections;

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
        MediaFile mediaFile = new MediaFile()
                .setFileName(new FileName().setName("testVideoFileName"))
                .setFilePath(new FilePath().setPath("C:/file/1.mp4"))
                .setMediaFileType(MediaFileType.MP4)
                .setComment("testComment")
                .setCategories(Collections.singletonList(new Category().setName("RADIO")));
        final Playlist playlist = new Playlist();
        playlist.setName("TestPlaylist")
                .setMediaFiles(Collections.singletonList(mediaFile));
        playlistTabPane.getTabs().add(new PlaylistTab(playlist));
    }
}
