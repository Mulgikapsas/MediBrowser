package com.qa.helper;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

import java.util.ResourceBundle;

public enum FXMLHelper {
    ;

    public static final ResourceBundle DEFAULT_RESOURCE_BUNDLE = ResourceBundle.getBundle("resourceBundle_en");

    /**
     * This method Loads {@link Parent} node from specified {@param clazz}
     *
     * @param clazz - {@link Class}
     * @return {@link Parent}
     * @throws java.io.IOException if resource is not found
     */
    @SuppressWarnings("ConstantConditions")
    public static Parent loadParent(final Class clazz) throws java.io.IOException {
        return FXMLLoader.load(clazz.getClassLoader().getResource("mainMenu.fxml"), DEFAULT_RESOURCE_BUNDLE);
    }

    /**
     * Get DEFAULT_RESOURCE_BUNDLE.
     **/
    public static ResourceBundle getResourceBundle() {
        return DEFAULT_RESOURCE_BUNDLE;
    }

    /**
     * This method returns translated {@param resourceBundleKey}
     *
     * @param resourceBundleKey - {@link String} in a resource bundle file
     * @return translated {@link String}
     */
    public static String getTranslatedString(final String resourceBundleKey) {
        return DEFAULT_RESOURCE_BUNDLE.getString(resourceBundleKey);
    }

}
