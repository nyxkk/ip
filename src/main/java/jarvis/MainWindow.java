package jarvis;

import javafx.animation.PauseTransition;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

/** Controls the main Jarvis chat window. */
public class MainWindow extends AnchorPane {
    @FXML
    private ScrollPane scrollPane;

    @FXML
    private VBox dialogContainer;

    @FXML
    private TextField userInput;

    private Jarvis jarvis;

    /** Creates the controller that JavaFX connects to the main-window FXML. */
    public MainWindow() {
    }

    /** Keeps the newest dialog visible as messages are added. */
    @FXML
    public void initialize() {
        dialogContainer.heightProperty().addListener(
                observable -> scrollPane.setVvalue(1.0));
    }

    /**
     * Supplies the Jarvis instance that handles commands and displays its greeting.
     *
     * @param jarvis the task assistant backing this window
     */
    public void setJarvis(Jarvis jarvis) {
        this.jarvis = jarvis;
        dialogContainer.getChildren().add(
                DialogBox.getJarvisDialog(jarvis.getWelcomeMessage()));
    }

    /** Adds the user's command and Jarvis' response to the conversation. */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText().trim();
        if (input.isEmpty()) {
            return;
        }

        String response = jarvis.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input),
                DialogBox.getJarvisDialog(response));
        userInput.clear();

        if (input.equals("bye")) {
            PauseTransition delay = new PauseTransition(Duration.seconds(1));
            delay.setOnFinished(event -> Platform.exit());
            delay.play();
        }
    }
}
