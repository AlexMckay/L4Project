package faceclicker;

import java.net.URL;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class MainStage extends Application {

    public static void main(String[] args) {
        Application.launch(MainStage.class, args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        //setup to access controller
        FXMLLoader fxmlLoader = new FXMLLoader();
        URL location = getClass().getResource("View.fxml");
        fxmlLoader.setLocation(location);
        fxmlLoader.setBuilderFactory(new JavaFXBuilderFactory());
        Parent root = (Parent) fxmlLoader.load(location.openStream());
        final MainController controller = (MainController) fxmlLoader.getController();

        final KeyCombination undoCombo = new KeyCodeCombination(KeyCode.Z, KeyCombination.CONTROL_DOWN);
        final KeyCombination nextCombo = new KeyCodeCombination(KeyCode.D);
        final KeyCombination prevCombo = new KeyCodeCombination(KeyCode.A);

        primaryStage.setTitle("Face Clicker");
        primaryStage.setScene(new Scene(root, 662, 700));

        //listen for mouse clicks
        primaryStage.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent me) {
                SalientPoint sp = controller.grabCoords(me);
            }
        });

        primaryStage.addEventHandler(KeyEvent.KEY_PRESSED, new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent ke) {
                if (undoCombo.match(ke)) {
                    controller.undo();
                }else if (nextCombo.match(ke)){
                    controller.nextImage();
                }else if (prevCombo.match(ke)){
                    controller.prevImage();
                }
            }
        });

        primaryStage.setX(50);
        primaryStage.setY(50);
        primaryStage.show();
    }
}
