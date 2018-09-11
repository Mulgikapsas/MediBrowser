package com.qa.ui.editor;

import com.qa.dao.Category;
import com.qa.dao.FileName;
import com.qa.dao.FilePath;
import com.qa.dao.Image;
import com.qa.dao.MediaFile;
import com.qa.holder.CategoryHolder;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
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
    private ComboBox<Category> categoryComboBox;
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
        //Populate category data
        categoryComboBox.setItems(CategoryHolder.getCategoryList());

        //Add listeners
        saveButton.setOnAction(event -> {
            final Image image = new Image()
                    .setFileName(new FileName().setName(imageFileNameTextField.getText()))
                    .setFilePath(new FilePath().setPath(imagePathTextField.getText()));

            //TODO Add image validation
            mediaFile.setImage(image);
            mediaFile.setComment(commentTextField.getText())
                    .setCategories(categoryObservableList);

        });

        addCategoryButton.setOnAction(event -> {
            final int selectedIndex = categoryComboBox.getSelectionModel().getSelectedIndex();
            final Category selectedCategory = categoryComboBox.getItems().get(selectedIndex);

            if (selectedCategory == null
                    || StringUtils.isBlank(selectedCategory.getName())
                    || categoryObservableList.contains(selectedCategory)) {
                return;
            }
            //Empty combo box on add
            categoryComboBox.getSelectionModel().clearSelection();
            categoryList.getItems().add(selectedCategory);
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
        categoryComboBox.setEditable(!readOnly);
    }
}
