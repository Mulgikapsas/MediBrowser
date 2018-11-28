package com.qa.ui.editor;

import com.qa.dao.Category;
import com.qa.holder.CategoryHolder;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import org.apache.commons.lang3.StringUtils;

public class CategoryEditorPane extends GridPane {

    @FXML
    private TextField categoryTextField;
    @FXML
    private Button addCategoryButton;
    @FXML
    private ListView<Category> categoryList;
    @FXML
    private Button removeCategoryButton;
    @FXML
    private Button updateCategoryButton;

    @FXML
    public void initialize() {
        categoryList.setItems(CategoryHolder.getCategoryList());

        addCategoryButton.setOnAction(event -> {
            final String newCategoryName = categoryTextField.getText();
            final Category newCategory = new Category().setName(newCategoryName);

            if (StringUtils.isBlank(newCategoryName) || CategoryHolder.getCategoryList().contains(newCategory)) {
                return;
            }
            categoryTextField.setText(null);
            CategoryHolder.getCategoryList().add(newCategory);
        });

        removeCategoryButton.setOnAction(event ->
                CategoryHolder.getCategoryList().remove(categoryList.getSelectionModel().getSelectedItem())
        );

        //Update selected category
        updateCategoryButton.setOnAction(event -> {
            final Category newCategory = new Category().setName(categoryTextField.getText());
            final Category selectedCategory = categoryList.getSelectionModel().getSelectedItem();
            if (selectedCategory != null && !StringUtils.isBlank(newCategory.getName()) && !CategoryHolder.getCategoryList().contains(newCategory)) {
                final int selectedCategoryIndex = CategoryHolder.getCategoryList().indexOf(selectedCategory);
                CategoryHolder.getCategoryList().set(selectedCategoryIndex, newCategory);
            }
        });
    }
}
