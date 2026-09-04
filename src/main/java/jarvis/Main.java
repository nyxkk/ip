package jarvis;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/** Displays Jarvis in a JavaFX window defined by FXML. */
public class Main extends Application {
    private final Jarvis jarvis = new Jarvis();

    /** Creates the JavaFX application. */
    public Main() {
    }

    /**
     * Creates and displays the primary Jarvis window.
     *
     * @param stage the primary window supplied by JavaFX
     */
    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader loader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane root = loader.load();
            loader.<MainWindow>getController().setJarvis(jarvis);

            stage.setTitle("Jarvis");
            stage.setMinWidth(420);
            stage.setMinHeight(600);
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException exception) {
            throw new RuntimeException("Unable to load the Jarvis window.", exception);
        }
    }
}
