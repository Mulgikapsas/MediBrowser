package com.qa.dao;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.apache.commons.lang3.StringUtils;

import java.util.List;

import static com.qa.helper.FXMLHelper.getTranslatedString;

public class Playlist {

    private String name;
    private ObservableList<MediaFile> mediaFiles = FXCollections.observableArrayList();

    /**
     * Get name.
     **/
    public String getName() {
        return StringUtils.isBlank(name) ? getTranslatedString("default.tab.header") : name;
    }

    /**
     * Sets the name and returns a reference to this for chaining.
     **/
    public Playlist setName(final String name) {
        this.name = name;
        return this;
    }

    /**
     * Get mediaFiles.
     **/
    public ObservableList<MediaFile> getMediaFiles() {
        return mediaFiles;
    }

    /**
     * Sets the mediaFiles and returns a reference to this for chaining.
     **/
    public Playlist setMediaFiles(final List<MediaFile> mediaFiles) {
        this.mediaFiles.setAll(mediaFiles);
        return this;
    }

    @Override
    public String toString() {
        return "Playlist{" +
                "name='" + name + '\'' +
                ", mediaFiles=" + mediaFiles +
                '}';
    }
}
