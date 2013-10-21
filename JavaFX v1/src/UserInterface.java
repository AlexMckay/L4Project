import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.ImageCursor;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class UserInterface extends Application {
    public static void main(String[] args) {
        launch(args);
    }
    
    int clickCount=0;
    Coord c = new Coord();
    
    
    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("JavaFX Welcome");
        
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(5);
        grid.setVgap(2);
        grid.setPadding(new Insets(25, 25, 25, 25));
        
        //grid.setGridLinesVisible(true); 
        
        final Text scenetitle = new Text("Welcome");
        scenetitle.setId("welcome-text");
        grid.add(scenetitle, 0, 0, 1, 1);
        
//        final TextField tbox = new TextField();
//        grid.add(tbox, 1, 1);
        
        Button btn = new Button("show last click position");
        HBox hbBtn = new HBox(10);
        hbBtn.getChildren().add(btn);
        grid.add(hbBtn, 0, 2);
        
        btn.setOnAction(new EventHandler<ActionEvent>() {
        	 
            @Override
            public void handle(ActionEvent e) {
            	String s = String.format("x = %.0f y = %.0f \n", c.getX(), c.getY());
            	scenetitle.setText(s);
                c.printValues();
            }
        });
        
        grid.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
        	@Override
        	public void handle(MouseEvent me) {
            	c.setX(me.getSceneX());
            	c.setY(me.getSceneY());  
        	}
        });
     

        Scene scene = new Scene(grid, 500, 404);
        //Image image = new Image(".jpg");
        //scene.setCursor(new ImageCursor(image, image.getWidth() / 2, image.getHeight() /2));
        primaryStage.setScene(scene);
        scene.getStylesheets().add("style.css");
       // scene.getStylesheets().add(HelloWorld.class.getResource("style.css").toExternalForm());
        primaryStage.show();
    }
}