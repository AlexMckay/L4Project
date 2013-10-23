import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.application.Application;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import javax.imageio.ImageIO;

public class UserInterface extends Application {
	
    int clickCount=0;
    SalientPoint c = new SalientPoint("lol","l");
    
    
    public static void main(String[] args) {
        launch(args);
    }
    
    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Co-ord clicker");
        
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.TOP_LEFT);
        grid.setHgap(5);
        grid.setVgap(2);
        grid.setPadding(new Insets(5, 5, 5, 5));
        
        //grid.setGridLinesVisible(true); 
        
//        final Text scenetitle = new Text("Welcome");
//        scenetitle.setId("welcome-text");
//        grid.add(scenetitle, 0, 0, 1, 1);
        
//        final TextField tbox = new TextField();
//        grid.add(tbox, 1, 1);
        
        Button btn = new Button("show last click position");
        btn.setOnAction(btnShowCoordsListener);
        HBox hbBtn = new HBox(10);
        hbBtn.getChildren().add(btn);
        grid.add(hbBtn, 0, 0);
        
        Button btn2 = new Button("load image");
        btn2.setOnAction(btnLoadImageListener);
        HBox hbBtn2 = new HBox(10);
        hbBtn2.getChildren().add(btn2);
        grid.add(hbBtn2, 1, 0);
    
        //grid.add(imageBox, 0, 0);     

        Scene scene = new Scene(grid, 250, 30);
        //Image image = new Image(".jpg");
        //scene.setCursor(new ImageCursor(image, image.getWidth() / 2, image.getHeight() /2));
        primaryStage.setScene(scene);
        primaryStage.setX(50);
        primaryStage.setY(50);
        scene.getStylesheets().add("style.css");
        primaryStage.show();
    }
    
    EventHandler<ActionEvent> btnLoadImageListener
    = new EventHandler<ActionEvent>(){
 
        @Override
        public void handle(ActionEvent t) {
            FileChooser fileChooser = new FileChooser();
             
            //Set extension filter
            FileChooser.ExtensionFilter extFilterJPG = new FileChooser.ExtensionFilter("JPG files (*.jpg)", "*.JPG");
            FileChooser.ExtensionFilter extFilterPNG = new FileChooser.ExtensionFilter("PNG files (*.png)", "*.PNG");
            fileChooser.getExtensionFilters().addAll(extFilterJPG, extFilterPNG);
              
            //Show open file dialog
            File file = fileChooser.showOpenDialog(null);
                       
            try {
                BufferedImage bufferedImage = ImageIO.read(file);
                Image image = SwingFXUtils.toFXImage(bufferedImage, null);

                StackPane secondaryLayout = new StackPane();
                secondaryLayout.getChildren().add(imageBox);
                
                secondaryLayout.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
                	@Override
                	public void handle(MouseEvent me) {
                    	c.setX(me.getSceneX());
                    	c.setY(me.getSceneY());  
                	}
                });
                
                Scene secondScene = new Scene(secondaryLayout, bufferedImage.getWidth(), bufferedImage.getHeight());

                Stage secondStage = new Stage();
                secondStage.setTitle("Image View");
                secondStage.setScene(secondScene);
                 
                //Set position of second window, related to primary window.
                //secondStage.setX(primaryStage.getX() + 250);
                //secondStage.setY(primaryStage.getY() + 100);

                secondStage.show();
                
                imageBox.setImage(image);
            } catch (IOException ex) {
                Logger.getLogger(UserInterface.class.getName()).log(Level.SEVERE, null, ex);
            }
 
        }
    };
    
    EventHandler<ActionEvent> btnShowCoordsListener
    = new EventHandler<ActionEvent>(){
 
    	@Override
        public void handle(ActionEvent e) {
        	String s = String.format("x = %.0f y = %.0f \n", c.getX(), c.getY());
            c.printValues();
        }
    };
}