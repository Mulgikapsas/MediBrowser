package com.qa.ui.player;

import com.qa.dao.FilePath;
import com.qa.dao.MediaFile;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;

import java.io.File;

public class MediaPlayerPane extends GridPane {

    @FXML
    private MediaView mediaView;
    @FXML
    private Button playButton;
    @FXML
    private Button pauseButton;

    private MediaFile mediaFile;
    private MediaPlayer player;

    public void setMediaFile(final MediaFile selectedMediaFile) {
        this.mediaFile = selectedMediaFile;
        final FilePath filePath = mediaFile.getFilePath();
        if (filePath != null) {
            final Media media = new Media(new File(filePath.getPath()).toURI().toString());
            player = new MediaPlayer(media);
            mediaView.setMediaPlayer(player);
        }
    }

    @FXML
    public void initialize() {
        playButton.setOnAction(event -> {
            if (player != null) {
                player.play();
            }
        });
        pauseButton.setOnAction(event -> {
            if (player != null) {
                player.pause();
                player.dispose();
            }
        });
    }


}
