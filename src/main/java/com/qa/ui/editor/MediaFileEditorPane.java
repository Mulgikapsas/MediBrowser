package com.qa.ui.editor;

import com.qa.dao.Category;
import com.qa.dao.FileName;
import com.qa.dao.FilePath;
import com.qa.dao.Image;
import com.qa.dao.MediaFile;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import org.apache.commons.lang3.StringUtils;

public class MediaFileEditorPane extends GridPane {
    private MediaFile mediaFile;

    @FXML
    private TextField commentTextField;
    @FXML
    private TextField imageFileNameTextField;
    @FXML
    private TextField imagePathTextField;
    @FXML
    private TextField categoryTextField;
    @FXML
    private Button addCategoryButton;
    @FXML
    private Button removeCategoryButton;
    @FXML
    private ListView<Category> categoryList;
    @FXML
    private Button saveButton;

    private final ObservableList<Category> categoryObservableList = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        //Add listeners
        saveButton.setOnAction(event -> {
            final Image image = new Image()
                    .setFileName(new FileName().setName(imageFileNameTextField.getText()))
                    .setFilePath(new FilePath().setPath(imagePathTextField.getText()));

            mediaFile.setImage(image);
            mediaFile.setComment(commentTextField.getText())
                    .setCategories(categoryObservableList);

        });
        addCategoryButton.setOnAction(event -> {
            final String textFieldText = categoryTextField.getText();
            final Category newCategory = new Category().setName(textFieldText);

            if (StringUtils.isBlank(textFieldText) || categoryObservableList.contains(newCategory)) {
                return;
            }
            //Empty text field on add
            categoryTextField.setText(null);
            categoryList.getItems().add(newCategory);
        });
        removeCategoryButton.setOnAction(event ->
                mediaFile.getCategories().removeAll(categoryList.getSelectionModel().getSelectedItems())
        );
    }

    /**
     * Sets the mediaFile and returns a reference to this for chaining.
     **/
    public MediaFileEditorPane setMediaFile(final MediaFile mediaFile) {
        this.mediaFile = mediaFile;
        initializeFields(mediaFile);
        return this;
    }

    /**
     * This method sets initial values to all fields from {@param mediaFile}
     *
     * @param mediaFile - {@link MediaFile}
     */
    private void initializeFields(final MediaFile mediaFile) {
        //Set initial values for all fields
        final Image image = mediaFile.getImage();
        commentTextField.setText(mediaFile.getComment());
        imageFileNameTextField.setText(image == null ? null : image.getFileName().getName());
        imagePathTextField.setText(image == null ? null : image.getFilePath().getPath());
        categoryObservableList.setAll(mediaFile.getCategories());
        categoryList.setItems(categoryObservableList);
    }

    /**
     * This method sets read only view to all UI components of this class
     *
     * @param readOnly - boolean
     */
    public void setReadOnly(final boolean readOnly) {
        saveButton.setDisable(readOnly);
        addCategoryButton.setDisable(readOnly);
        removeCategoryButton.setDisable(readOnly);
        commentTextField.setEditable(!readOnly);
        imageFileNameTextField.setEditable(!readOnly);
        imagePathTextField.setEditable(!readOnly);
        categoryTextField.setEditable(!readOnly);

    }
}
