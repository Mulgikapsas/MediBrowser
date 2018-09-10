package com.qa.ui.editor;

import com.qa.dao.Category;
import com.qa.dao.Image;
import com.qa.dao.MediaFile;
import javafx.collections.ObservableList;
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
    private TextField imageNameTextField;
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
    public void initialize() {
        //Add listeners
        addCategoryButton.setOnAction(event -> {
            final String textFieldText = categoryTextField.getText();
            final ObservableList<Category> categories = mediaFile.getCategories();

            if (StringUtils.isBlank(textFieldText) || categories.contains(new Category().setName(textFieldText))) {
                return;
            }

            final Category category = new Category().setName(textFieldText);
            categories.add(category);
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
        imageNameTextField.setText(image == null ? null : image.getFileName().getName());
        imagePathTextField.setText(image == null ? null : image.getFilePath().getPath());
        categoryList.setItems(mediaFile.getCategories());
    }
}
