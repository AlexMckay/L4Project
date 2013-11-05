package faceclicker;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;

import javax.imageio.ImageIO;
 
public class MainController {
    @FXML private Text nextclickmessage;
    @FXML private Pane pane1;
    @FXML private ImageView imagebox;
    SalientPointCollection points;
    //activated is to check an image has been loaded
    boolean activated = false;
    
    //function to load new images when the button is pressed
    @FXML protected void handleLoadButtonAction(ActionEvent event) {
    	
    	FileChooser fileChooser = new FileChooser();
    	points  = new SalientPointCollection();
        
        //set extension filters
        FileChooser.ExtensionFilter extFilterJPG = new FileChooser.ExtensionFilter("JPG files (*.jpg, *.jpeg)", "*.JPG", "*.JPEG");
        FileChooser.ExtensionFilter extFilterPNG = new FileChooser.ExtensionFilter("PNG files (*.png)", "*.PNG");
        fileChooser.getExtensionFilters().addAll(extFilterJPG, extFilterPNG);
          
        //show open file dialog
        File file = fileChooser.showOpenDialog(null);
                   
        try {
        	//read in selected file
            BufferedImage bufferedImage = ImageIO.read(file);
            Image image = SwingFXUtils.toFXImage(bufferedImage, null);
            activated = true;
            
            //display image
            imagebox.setImage(image);
        } catch (IOException ex) {
            Logger.getLogger(MainStage.class.getName()).log(Level.SEVERE, null, ex);
        }
            
        //display first salient point to click
        nextclickmessage.setText(points.getCurrent().getName());
    }

    //function that handles mouse clicks: stores x+y data in current SP and moves on to next one
    @FXML protected SalientPoint grabCoords(MouseEvent me){
    	
    	if (activated){    		
				SalientPoint sp = points.getCurrent();
	        	sp.setX(me.getX());
	        	sp.setY(me.getY());
	        	sp.printValues();
	        	
	        	Circle c = new Circle();
	        	c.setRadius(2);
	        	c.setFill(Color.RED);
	        	c.setCenterX(sp.getX());
	        	c.setCenterY(sp.getY());
	        	pane1.getChildren().add(c);
	        	
	        	if (points.hasNext()){
	        		points.iterate();
	        		//displays the name of the next point to click
	        		nextclickmessage.setText(points.getCurrent().getName());
	        	}else{
	        		nextclickmessage.setText("done!");
		    		System.out.println("\n\nall points clicked! values:");
		    		points.printPoints();
		    		activated = false;
	        	}
    	}
    	return null;
    }

}