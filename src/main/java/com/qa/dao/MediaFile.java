package com.qa.dao;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.List;

public class MediaFile {
    private FileName fileName;
    private FilePath filePath;
    private MediaFileType mediaFileType;
    private Image image;
    private String comment;
    private ObservableList<Category> categories = FXCollections.observableArrayList();

    /**
     * Get fileName.
     **/
    public FileName getFileName() {
        return fileName;
    }

    /**
     * Sets the fileName and returns a reference to this for chaining.
     **/
    public MediaFile setFileName(final FileName fileName) {
        this.fileName = fileName;
        return this;
    }

    /**
     * Get filePath.
     **/
    public FilePath getFilePath() {
        return filePath;
    }

    /**
     * Sets the filePath and returns a reference to this for chaining.
     **/
    public MediaFile setFilePath(final FilePath filePath) {
        this.filePath = filePath;
        return this;
    }

    /**
     * Get mediaFileType.
     **/
    public MediaFileType getMediaFileType() {
        return mediaFileType;
    }

    /**
     * Sets the mediaFileType and returns a reference to this for chaining.
     **/
    public MediaFile setMediaFileType(final MediaFileType mediaFileType) {
        this.mediaFileType = mediaFileType;
        return this;
    }

    /**
     * Get comment.
     **/
    public String getComment() {
        return comment;
    }

    /**
     * Sets the comment and returns a reference to this for chaining.
     **/
    public MediaFile setComment(final String comment) {
        this.comment = comment;
        return this;
    }

    /**
     * Get categories.
     **/
    public ObservableList<Category> getCategories() {
        return categories;
    }

    /**
     * Sets the categories and returns a reference to this for chaining.
     **/
    public MediaFile setCategories(final List<Category> categories) {
        this.categories.setAll(categories);
        return this;
    }

    /**
     * Get image.
     **/
    public Image getImage() {
        return image;
    }

    /**
     * Sets the image and returns a reference to this for chaining.
     **/
    public MediaFile setImage(final Image image) {
        this.image = image;
        return this;
    }

    @Override
    public String toString() {
        return "MediaFile{" +
                "fileName=" + fileName +
                ", filePath=" + filePath +
                ", mediaFileType=" + mediaFileType +
                ", image=" + image +
                ", comment='" + comment + '\'' +
                ", categories=" + categories +
                '}';
    }
}
