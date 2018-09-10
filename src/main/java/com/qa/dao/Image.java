package com.qa.dao;

public class Image {

    private FileName fileName;
    private FilePath filePath;

    /**
     * Get fileName.
     **/
    public FileName getFileName() {
        return fileName;
    }

    /**
     * Sets the fileName and returns a reference to this for chaining.
     **/
    public Image setFileName(final FileName fileName) {
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
    public Image setFilePath(final FilePath filePath) {
        this.filePath = filePath;
        return this;
    }

    @Override
    public String toString() {
        return "Image{" +
                "fileName=" + fileName +
                ", filePath=" + filePath +
                '}';
    }
}
