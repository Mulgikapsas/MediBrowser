package com.qa.dao;

import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.List;


public class MediaFile {
    private final SimpleObjectProperty<FileName> fileName = new SimpleObjectProperty<>();
    private final SimpleObjectProperty<FilePath> filePath = new SimpleObjectProperty<>();
    private final SimpleObjectProperty<MediaFileType> mediaFileType = new SimpleObjectProperty<>();
    private final SimpleObjectProperty<Image> image = new SimpleObjectProperty<>();
    private final SimpleStringProperty comment = new SimpleStringProperty();
    private final ObservableList<Category> categories = FXCollections.observableArrayList();

    /**
     * Getter
     *
     * @return {@link FileName}
     */
    public FileName getFileName() {
        return fileName.get();
    }

    /**
     * Property getter
     * Used via reflection in cells
     *
     * @return {@link SimpleObjectProperty<FileName>}
     */
    public SimpleObjectProperty<FileName> fileNameProperty() {
        return fileName;
    }

    /**
     * Set field and return this object for chaining
     *
     * @param fileName - {@link FileName}
     */
    public MediaFile setFileName(final FileName fileName) {
        this.fileName.set(fileName);
        return this;
    }

    /**
     * Getter
     *
     * @return {@link FilePath}
     */
    public FilePath getFilePath() {
        return filePath.get();
    }

    /**
     * Property getter
     * Used via reflection in cells
     *
     * @return {@link SimpleObjectProperty<FilePath>}
     */
    public SimpleObjectProperty<FilePath> filePathProperty() {
        return filePath;
    }

    /**
     * Set field and return this object for chaining
     *
     * @param filePath - {@link FilePath}
     */
    public MediaFile setFilePath(final FilePath filePath) {
        this.filePath.set(filePath);
        return this;
    }

    /**
     * Getter
     *
     * @return {@link MediaFileType}
     */
    public MediaFileType getMediaFileType() {
        return mediaFileType.get();
    }

    /**
     * Property getter
     * Used via reflection in cells
     *
     * @return {@link SimpleObjectProperty<MediaFileType>}
     */
    public SimpleObjectProperty<MediaFileType> mediaFileTypeProperty() {
        return mediaFileType;
    }

    /**
     * Set field and return this object for chaining
     *
     * @param mediaFileType - {@link MediaFileType}
     */
    public MediaFile setMediaFileType(final MediaFileType mediaFileType) {
        this.mediaFileType.set(mediaFileType);
        return this;
    }

    /**
     * Getter
     *
     * @return {@link Image}
     */
    public Image getImage() {
        return image.get();
    }

    /**
     * Property getter
     * Used via reflection in cells
     *
     * @return {@link SimpleObjectProperty<Image>}
     */
    public SimpleObjectProperty<Image> imageProperty() {
        return image;
    }

    /**
     * Set field and return this object for chaining
     *
     * @param image - {@link Image}
     */
    public MediaFile setImage(final Image image) {
        this.image.set(image);
        return this;
    }

    /**
     * Getter
     *
     * @return {@link String}
     */
    public String getComment() {
        return comment.get();
    }

    /**
     * Property getter
     * Used via reflection in cells
     *
     * @return {@link SimpleStringProperty}
     */
    public SimpleStringProperty commentProperty() {
        return comment;
    }

    /**
     * Set field and return this object for chaining
     *
     * @param comment - {@link String}
     */
    public MediaFile setComment(final String comment) {
        this.comment.set(comment);
        return this;
    }

    /**
     * Get categories.
     **/
    public ObservableList<Category> getCategories() {
        return categories;
    }

    /**
     * Set categories.
     **/
    public MediaFile setCategories(final List<Category> categories) {
        this.categories.setAll(categories);
        return this;
    }


}
