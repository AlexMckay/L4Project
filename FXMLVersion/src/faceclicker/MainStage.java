package faceclicker;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class MainStage extends Application {
	
    SalientPoint c = new SalientPoint("lol","l");
    
    public static void main(String[] args) {
        Application.launch(MainStage.class, args);
    }
    
    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("View.fxml"));
        
        primaryStage.setTitle("trollop");
        primaryStage.setScene(new Scene(root, 600, 600));
        primaryStage.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
        	@Override
        	public void handle(MouseEvent me) {
            	c.setX(me.getX());
            	c.setY(me.getY()); 
            	System.out.printf("x = %.0f y = %.0f \n", c.getX(), c.getY());
        	}
        });
        primaryStage.setX(50);
        primaryStage.setY(50);
        primaryStage.show();
    }
}
