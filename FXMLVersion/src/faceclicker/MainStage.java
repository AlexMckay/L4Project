package faceclicker;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class MainStage extends Application {
	
    SalientPointCollection c = new SalientPointCollection();
    
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
        		if (c.hasNext()){
        			SalientPoint sp = c.getCurrent();
                	sp.setX(me.getX());
                	sp.setY(me.getY()); 
                	System.out.printf("x = %.0f y = %.0f \n", sp.getX(), sp.getY());
                	c.iterate();
        		}
        	}
        });
        
        primaryStage.setX(50);
        primaryStage.setY(50);
        primaryStage.show();
    }
}
