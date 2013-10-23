package faceclicker;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainStage extends Application {
    
    public static void main(String[] args) {
        Application.launch(MainStage.class, args);
    }
    
    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("View.fxml"));
        
        primaryStage.setTitle("trollop");
        primaryStage.setScene(new Scene(root, 100, 100));
        primaryStage.setX(50);
        primaryStage.setY(50);
        primaryStage.show();
    }
}
