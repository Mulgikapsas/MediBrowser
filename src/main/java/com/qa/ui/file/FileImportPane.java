package com.qa.ui.file;

import com.qa.dao.Category;
import com.qa.dao.FileName;
import com.qa.dao.FilePath;
import com.qa.dao.Image;
import com.qa.dao.MediaFile;
import com.qa.dao.MediaFileType;
import com.qa.ui.playlist.PlaylistTab;
import javafx.scene.layout.GridPane;

import java.util.Collections;
import java.util.UUID;

public class FileImportPane extends GridPane {

    public void setSelectedTab(final PlaylistTab selectedItem) {

        MediaFile mediaFile = new MediaFile()
                .setFileName(new FileName().setName(UUID.randomUUID().toString()))
                .setFilePath(new FilePath().setPath("C:/" + UUID.randomUUID().toString()))
                .setMediaFileType(MediaFileType.MP4)
                .setImage(new Image().setFilePath(new FilePath().setPath(UUID.randomUUID().toString())).setFileName(new FileName().setName("dasdas")))
                .setComment("test" + UUID.randomUUID().toString())
                .setCategories(Collections.singletonList(new Category().setName("RADIO")));

        selectedItem.addMediaFile(mediaFile);
    }
}
