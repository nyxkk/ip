package jarvis;

import java.io.IOException;
import java.util.Collections;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

/** Represents one user or Jarvis message in the conversation. */
public class DialogBox extends HBox {
    @FXML
    private Label avatar;

    @FXML
    private Label dialog;

    private DialogBox(String text, String avatarText) {
        try {
            FXMLLoader loader = new FXMLLoader(
                    DialogBox.class.getResource("/view/DialogBox.fxml"));
            loader.setController(this);
            loader.setRoot(this);
            loader.load();
        } catch (IOException exception) {
            throw new RuntimeException("Unable to load a dialog box.", exception);
        }

        avatar.setText(avatarText);
        dialog.setText(text);
    }

    /** Places the user's avatar on the right side of the message. */
    private void flip() {
        ObservableList<Node> children = FXCollections.observableArrayList(getChildren());
        Collections.reverse(children);
        getChildren().setAll(children);
        setAlignment(Pos.TOP_RIGHT);
    }

    /**
     * Creates a message spoken by the user.
     *
     * @param text the user's command
     * @return the styled user dialog
     */
    public static DialogBox getUserDialog(String text) {
        DialogBox dialogBox = new DialogBox(text, "You");
        dialogBox.getStyleClass().add("user-dialog");
        dialogBox.flip();
        return dialogBox;
    }

    /**
     * Creates a message spoken by Jarvis.
     *
     * @param text Jarvis' response
     * @return the styled Jarvis dialog
     */
    public static DialogBox getJarvisDialog(String text) {
        DialogBox dialogBox = new DialogBox(text, "J");
        dialogBox.getStyleClass().add("jarvis-dialog");
        return dialogBox;
    }
}
