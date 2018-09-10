package com.qa.dao;

import java.util.List;

public class MediaFile {
    private FileName fileName;
    private FilePath filePath;
    private MediaFileType mediaFileType;
    private String comment;
    private List<Category> categories;

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
    public List<Category> getCategories() {
        return categories;
    }

    /**
     * Sets the categories and returns a reference to this for chaining.
     **/
    public MediaFile setCategories(final List<Category> categories) {
        this.categories = categories;
        return this;
    }
}
