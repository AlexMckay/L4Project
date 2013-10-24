package faceclicker;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import javax.imageio.ImageIO;
 
public class Controller {
    @FXML private Text loadedmessage;
    @FXML private ImageView imagebox;
    
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
//            
//            Scene secondScene = new Scene(secondaryLayout, bufferedImage.getWidth(), bufferedImage.getHeight());
//
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
            
            
        loadedmessage.setText("Image Loaded");
    }


}