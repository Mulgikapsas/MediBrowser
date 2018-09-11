package com.qa.helper;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.Window;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;

import static com.qa.MediaBrowser.PROPERTIES;


/**
 * FXMLHelper
 * Utility class for FXML loaders and resource bundle
 *
 * @author Juri Duval
 */
public enum FXMLHelper {
    ;
    private static final Logger LOGGER = LoggerFactory.getLogger(FXMLHelper.class);
    public static final ResourceBundle DEFAULT_RESOURCE_BUNDLE = ResourceBundle.getBundle("resourceBundle_en", Locale.ENGLISH);

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

    /**
     * This method loads a new window with specified parameters
     *
     * @param fxml      - {@link String} fxml file name
     * @param width     - int width in pixels
     * @param height    - int height in pixels
     * @param modality  - {@link Modality}
     * @param ownerNode - {@link Window}
     * @param clazz     - {@link Class} to be used for loadin fxml
     * @param resizable - specifies if new window should be resizable or not
     * @param title     - {@link Stage title}
     * @return T representing a controller
     */
    public static <T> T loadNewWindow(final String fxml,
                                      final int width, final int height,
                                      final Modality modality,
                                      final Window ownerNode,
                                      final Class clazz, final boolean resizable, final String title) {
        T controller = null;
        try {
            final FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(clazz.getClassLoader().getResource(fxml));
            fxmlLoader.setResources(DEFAULT_RESOURCE_BUNDLE);
            final Scene scene = new Scene(fxmlLoader.load(), width, height);
            final Stage stage = new Stage();
            stage.initModality(modality);
            controller = fxmlLoader.getController();
            stage.initOwner(ownerNode);
            stage.setScene(scene);
            stage.setResizable(resizable);
            stage.setTitle(title);
            stage.getIcons().add(new Image(PROPERTIES.getProperty("APP_ICON")));
            stage.show();
        } catch (final IOException e) {
            LOGGER.error("Failed to load new window.", e);
        }

        return controller;
    }

    /**
     * This method load {@param fxml} for specified {@param controller}
     *
     * @param fxml       - {@link String} fxml file name
     * @param controller - {@link Object}
     */
    public static void loadFXML(final String fxml, final Object controller) {
        final FXMLLoader fxmlLoader = new FXMLLoader(controller.getClass().getClassLoader().getResource(fxml), DEFAULT_RESOURCE_BUNDLE);
        fxmlLoader.setController(controller);
        try {
            fxmlLoader.load();
        } catch (final IOException e) {
            LOGGER.error("Failed to load fxml.", e);
        }
    }

}
