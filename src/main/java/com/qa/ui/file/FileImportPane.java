package com.qa.ui.file;

import com.qa.dao.FileName;
import com.qa.dao.FilePath;
import com.qa.dao.MediaFile;
import com.qa.dao.MediaFileType;
import com.qa.ui.playlist.PlaylistTab;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import org.controlsfx.control.Notifications;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.text.MessageFormat;

import static com.qa.helper.FXMLHelper.getTranslatedString;

public class FileImportPane extends GridPane {
    private static final Logger LOGGER = LoggerFactory.getLogger(FileImportPane.class);

    @FXML
    private Button importButton;
    @FXML
    private TextField pathField;
    @FXML
    private CheckBox filterMP3CheckBox;
    @FXML
    private CheckBox filterAACCheckBox;
    @FXML
    private CheckBox filterWAVCheckBox;
    @FXML
    private CheckBox filterMP4CheckBox;
    @FXML
    private CheckBox filterAVICheckBox;

    private PlaylistTab selectedItem;

    public void setSelectedTab(final PlaylistTab selectedItem) {
        this.selectedItem = selectedItem;
    }

    /**
     * This method tries to import any files which are specified in {@code pathField}
     *
     * @param actionEvent - {@link ActionEvent}
     */
    @FXML
    private void importFiles(final ActionEvent actionEvent) {
        final File file = new File(pathField.getText());
        final boolean mp3CheckBoxSelected = filterMP3CheckBox.isSelected();
        final boolean aacCheckBoxSelected = filterAACCheckBox.isSelected();
        final boolean wavCheckBoxSelected = filterWAVCheckBox.isSelected();
        final boolean mp4CheckBoxSelected = filterMP4CheckBox.isSelected();
        final boolean aviCheckBoxSelected = filterAVICheckBox.isSelected();

        if (file.isDirectory()) {
            //Multiple file import
            final File[] files = file.listFiles(pathname -> {
                final String path = pathname.getPath();
                return isValidFilePath(mp3CheckBoxSelected, aacCheckBoxSelected,
                        wavCheckBoxSelected, mp4CheckBoxSelected,
                        aviCheckBoxSelected, path);

            });
            if (files != null) {
                importFiles(files);
            } else {
                Notifications.create()
                        .title("Warning")
                        .text("Could not find any relevant files in specified path.")
                        .showWarning();
            }
        } else if (file.exists()) {
            //Single file import
            final boolean validFilePath = isValidFilePath(mp3CheckBoxSelected, aacCheckBoxSelected,
                    wavCheckBoxSelected, mp4CheckBoxSelected,
                    aviCheckBoxSelected, file.getPath());
            if (validFilePath) {
                importFiles(new File[]{file});
            } else {
                Notifications.create()
                        .title(getTranslatedString("notification.warning"))
                        .text(getTranslatedString("notification.file.import.warning"))
                        .showWarning();
            }
        } else {
            //Import error
            Notifications.create()
                    .title(getTranslatedString("notification.warning"))
                    .text(getTranslatedString("notification.wrong.file.path.warning"))
                    .showWarning();
        }
    }

    private static boolean isValidFilePath(final boolean mp3CheckBoxSelected, final boolean aacCheckBoxSelected, final boolean wavCheckBoxSelected, final boolean mp4CheckBoxSelected, final boolean aviCheckBoxSelected, final String path) {
        return mp3CheckBoxSelected && MediaFileType.MP3.matches(path)
                || aacCheckBoxSelected && MediaFileType.AAC.matches(path)
                || wavCheckBoxSelected && MediaFileType.WAV.matches(path)
                || mp4CheckBoxSelected && MediaFileType.MP4.matches(path)
                || aviCheckBoxSelected && MediaFileType.AVI.matches(path);
    }

    /**
     * This method imports all {@param files} into {@code selectedItem}
     *
     * @param files - {@link File[]}
     */
    private void importFiles(final File[] files) {
        for (final File file : files) {
            final String path = file.getPath();
            final MediaFile mediaFile = new MediaFile()
                    .setFileName(new FileName().setName(file.getName()))
                    .setFilePath(new FilePath().setPath(path))
                    .setMediaFileType(MediaFileType.getByPath(path));
            selectedItem.addMediaFile(mediaFile);
        }
        final int fileCount = files.length;
        Notifications.create()
                .title(getTranslatedString("notification.info"))
                .text(MessageFormat.format(getTranslatedString("notification.file.import.info"), fileCount))
                .showInformation();
    }

}
