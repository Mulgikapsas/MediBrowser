package com.qa.ui.playlist;

import com.qa.dao.MediaFile;
import com.qa.dao.Playlist;
import com.qa.ui.editor.MediaFileEditorPane;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseButton;
import javafx.stage.Modality;
import javafx.stage.Window;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.qa.helper.FXMLHelper.getTranslatedString;
import static com.qa.helper.FXMLHelper.loadFXML;
import static com.qa.helper.FXMLHelper.loadNewWindow;

public class PlaylistTab extends Tab {
    private static final Logger LOGGER = LoggerFactory.getLogger(PlaylistTab.class);

    private Playlist playlist;
    private Label playlistName = new Label(); //Tab header value
    private final ContextMenu playlistItemContextMenu = new ContextMenu(); //Shows options for each table item

    @FXML
    private TableView<MediaFile> playlistTableView;
    @FXML
    private TableColumn<MediaFile, String> fileNameColumn;
    @FXML
    private TableColumn<MediaFile, String> filePathColumn;
    @FXML
    private TableColumn<MediaFile, String> mediaFileTypeColumn;
    @FXML
    private TableColumn<MediaFile, String> mediaTypeColumn;

    public PlaylistTab(final Playlist playlist) {
        super();
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("Initializing playlist tab. Name: " + playlist.getName());
        }
        this.playlist = playlist;
        loadFXML("playlistTab.fxml", this);
    }

    @FXML
    public void initialize() {
        //Initialize context menu
        final MenuItem editMenuItem = new MenuItem(getTranslatedString("playlist.edit.menu.item"));
        final MenuItem viewMetaDataMenuItem = new MenuItem(getTranslatedString("playlist.view.meta.data.menu.item"));
        final MenuItem removeMenuItem = new MenuItem(getTranslatedString("playlist.remove.menu.item"));
        playlistItemContextMenu.getItems().setAll(editMenuItem, viewMetaDataMenuItem, removeMenuItem);

        editMenuItem.setOnAction(event -> showMediaFileEditorWindow(playlistTableView.getSelectionModel().getSelectedItem(), false));
        removeMenuItem.setOnAction(event -> playlist.getMediaFiles().remove(playlistTableView.getSelectionModel().getSelectedItem()));
        viewMetaDataMenuItem.setOnAction(event -> showMediaFileEditorWindow(playlistTableView.getSelectionModel().getSelectedItem(), true));

        //Initialize table view
        fileNameColumn.setCellValueFactory(param -> new SimpleStringProperty(param.getValue().getFileName().getName()));
        filePathColumn.setCellValueFactory(param -> new SimpleStringProperty(param.getValue().getFilePath().getPath()));
        mediaFileTypeColumn.setCellValueFactory(param -> new SimpleStringProperty(param.getValue().getMediaFileType().toString()));
        mediaTypeColumn.setCellValueFactory(param -> new SimpleStringProperty(param.getValue().getMediaFileType().getContentType().toString()));
        playlistTableView.setItems(playlist.getMediaFiles());

        //Show context menu when we right click a row
        playlistTableView.setRowFactory(param -> {
            final TableRow<MediaFile> tableRow = new TableRow<>();
            tableRow.setOnMouseClicked(event -> {
                if (event.getButton() == MouseButton.SECONDARY) {
                    final Window owner = getTabPane().getScene().getWindow();
                    playlistItemContextMenu.show(owner, event.getScreenX(), event.getScreenY());
                }
            });
            return tableRow;
        });


        //Set initial values
        playlistName.setText(playlist.getName());
        setGraphic(playlistName);
        setContent(playlistTableView);

        final TextField textField = new TextField();
        textField.setMaxWidth(60);

        //Start edit on double click
        playlistName.setOnMouseClicked(event -> {
            if (event.getClickCount() == 2) {
                textField.setText(playlistName.getText());
                setGraphic(textField);
                textField.selectAll();
                textField.requestFocus();
            }
        });

        //Update value on focus out if it is not blank
        textField.focusedProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue) {
                final String newText = textField.getText();
                if (StringUtils.isBlank(newText)) {
                    playlistName.setText(playlist.getName());
                } else {
                    playlistName.setText(newText);
                    playlist.setName(playlistName.getText());
                }
                setGraphic(playlistName);
            }
        });
    }

    /**
     * This method shows new media file editor window
     *
     * @param selectedMediaFile - last selected {@link MediaFile}
     */
    private void showMediaFileEditorWindow(final MediaFile selectedMediaFile, final boolean readOnly) {
        final MediaFileEditorPane mediaFileEditorPane = loadNewWindow("mediaFileEditorPane.fxml",
                500, 300,
                Modality.WINDOW_MODAL,
                getTabPane().getScene().getWindow(),
                getClass(), false);
        mediaFileEditorPane.setMediaFile(selectedMediaFile);
        mediaFileEditorPane.setReadOnly(readOnly);
    }

}
