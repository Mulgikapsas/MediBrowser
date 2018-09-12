package com.qa.holder;

import com.qa.dao.Category;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.List;

/**
 * CategoryHolder
 * This class is for storing all category information
 *
 * @author Juri Duval
 */
public enum CategoryHolder {
    ;
    private static ObservableList<Category> CATEGORY_LIST = FXCollections.observableArrayList();

    /**
     * Remove {@param category} from {@code CATEGORY_LIST}
     *
     * @param category - {@link Category}
     */
    public static void removeCategory(final Category category) {
        CATEGORY_LIST.remove(category);
    }

    /**
     * Add {@param category} to {@code CATEGORY_LIST}
     *
     * @param category - {@link Category}
     */
    public static void addCategory(final Category category) {
        CATEGORY_LIST.add(category);
    }

    /**
     * Set {@param categories} to {@code CATEGORY_LIST}
     *
     * @param categories - {@link List<Category>}
     */
    public static void setCategories(final List<Category> categories) {
        CATEGORY_LIST.setAll(categories);
    }

    /**
     * Get CATEGORY_LIST.
     **/
    public static ObservableList<Category> getCategoryList() {
        return CATEGORY_LIST;
    }
}
