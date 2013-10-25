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
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;

import javax.imageio.ImageIO;
 
public class MainController {
    @FXML private Text nextclickmessage;
    @FXML private ImageView imagebox;
    SalientPointCollection points = new SalientPointCollection();
    
    @FXML protected void handleLoadButtonAction(ActionEvent event) {
    	
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

//            StackPane secondaryLayout = new StackPane();
//            secondaryLayout.getChildren().add(imageBox);            
//            Scene secondScene = new Scene(secondaryLayout, bufferedImage.getWidth(), bufferedImage.getHeight());
//            Stage secondStage = new Stage();
//            secondStage.setTitle("Image View");
//            secondStage.setScene(secondScene);            
            //Set position of second window, related to primary window.
            //secondStage.setX(primaryStage.getX() + 0);
            //secondStage.setY(primaryStage.getY() + 0);
//            secondStage.show();
            
            imagebox.setImage(image);
        } catch (IOException ex) {
            Logger.getLogger(MainStage.class.getName()).log(Level.SEVERE, null, ex);
        }
            
            
        nextclickmessage.setText(points.getCurrent().getName());
    }

    @FXML protected void grabCoords(MouseEvent me){
    	if (points.hasNext()){
			SalientPoint sp = points.getCurrent();
        	sp.setX(me.getX());
        	sp.setY(me.getY());
        	sp.printValues();
        	
        	Circle circle = new Circle();
        	circle.setCenterX(sp.getX());
        	circle.setCenterY(sp.getY());
        	circle.setRadius(50.0f);
        	
        	points.iterate();
        	nextclickmessage.setText(points.getCurrent().getName());
    	}
    }

}