package faceclicker;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBoxBuilder;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;

import javax.imageio.ImageIO;

public class MainController {
	
    @FXML private Text nextclickmessage;
    @FXML private Pane pane1;
    @FXML private ImageView imageBox;
    @FXML private Label imageName, dirCount;
    @FXML private ComboBox imageCombo;
    private SalientPointCollection points;
    private boolean activated = false; //activated is to check an image has been loaded
    private ArrayList<Circle> circleList = new ArrayList<Circle>();
    private String filePath;
    private ArrayList<String> imageList;
    private int dirIterator;
    
    //function to load new images when the button is pressed
    @FXML protected void handleLoadButtonAction(ActionEvent event) {
    	
    	
        FileChooser fileChooser = new FileChooser();
    	points  = new SalientPointCollection();
        
        for (Circle cc : circleList){
            pane1.getChildren().remove(cc);
        }      
        circleList.removeAll(circleList);
        
        //set extension filters
        FileChooser.ExtensionFilter extFilterJPG = new FileChooser.ExtensionFilter("JPG files (*.jpg, *.jpeg)", "*.JPG", "*.JPEG", "*.jpg", "*,jpeg");
        FileChooser.ExtensionFilter extFilterPNG = new FileChooser.ExtensionFilter("PNG files (*.png)", "*.PNG", "*.png");
        fileChooser.getExtensionFilters().addAll(extFilterJPG, extFilterPNG);
          
        //show open file dialog
        File file = fileChooser.showOpenDialog(null);
        filePath = file.getName();
                   
        try {
            //read in selected file
            BufferedImage bufferedImage = ImageIO.read(file);
            Image image = SwingFXUtils.toFXImage(bufferedImage, null);
            activated = true;
            
            //display image
            imageBox.setImage(image);
        } catch (IOException ex) {
            Logger.getLogger(MainStage.class.getName()).log(Level.SEVERE, null, ex);
        }
            
        //display first salient point to click
        nextclickmessage.setText(points.getCurrent().getName());
    }
    
    @FXML protected void handleLoadButtonAction2(ActionEvent event) throws InterruptedException {
    	
		Stage primaryStage = (Stage) imageBox.getScene().getWindow();
    	points  = new SalientPointCollection();
    	dirIterator = 0;
  
        DirectoryChooser chooser = new DirectoryChooser();
        chooser.setTitle("Choose Directory");
        File defaultDirectory = new File("c:/");
        chooser.setInitialDirectory(defaultDirectory);
        File dir = chooser.showDialog(primaryStage);
        imageList = listFiles(dir);
        imageCombo.setItems(FXCollections.observableArrayList(imageList));
        
	    displayImage(imageList.get(dirIterator));
		    
		    
    }

    @FXML protected void handleNextImage(ActionEvent event) {
    	if((dirIterator+1)<imageList.size()){
    		displayImage(imageList.get(++dirIterator));
    	}else{
    		alert("Directory emptied");
    	}
    }
    
    //function that handles mouse clicks: stores x+y data in current SP and moves on to next one
    @FXML protected SalientPoint grabCoords(MouseEvent me){
    	
    	if (activated){    		
                SalientPoint sp = points.getCurrent();
	        	sp.setX(me.getX());
	        	sp.setY(me.getY());
	        	//sp.printValues();
	        	
	        	Circle c = new Circle();
	        	c.setRadius(2);
	        	c.setFill(Color.RED);
	        	c.setCenterX(sp.getX());
	        	c.setCenterY(sp.getY());                 
                circleList.add(c);                      
	        	pane1.getChildren().add(c);
	        	
	        	if (points.hasNext()){
	        		points.iterate();
	        		//displays the name of the next point to click
	        		nextclickmessage.setText(points.getCurrent().getName());
	        	}else{
	        		nextclickmessage.setText("Image finished");
                    try {
                        points.writePoints(filePath);
                    } catch (FileNotFoundException ex) {
                        Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (UnsupportedEncodingException ex) {
                        Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
                    }
		    		activated = false;
	        	}
    	}
    	return null;
    }
    
    public ArrayList<String> listFiles(File directory) {
        ArrayList<String> fileNames = new ArrayList<>();

        //get all the files from a directory
        File[] fList = directory.listFiles();
        for (File file : fList) {
            if (file.isFile()) {
                fileNames.add(file.getAbsolutePath());
            }
        }
        return fileNames;
    }
    
    public void displayImage(String imageName){
    	
    	String fileExt = imageName.substring(imageName.lastIndexOf('.') + 1);
    	
    	if (canReadExtension(fileExt)) {
	    	points = new SalientPointCollection();
	    	wipeCircles();
	    	nextclickmessage.setText(points.getCurrent().getName());	//display first salient point to click    	

		    try {
		    	File file = new File(imageName);
		    	filePath = file.getAbsolutePath();						//change to getName once allowing output dir choice
		        BufferedImage bufferedImage = ImageIO.read(file);
		        Image image = SwingFXUtils.toFXImage(bufferedImage, null);
		        activated = true;
		        imageBox.setImage(image);
		        setImageName(file.getName());
		        setDirCount(dirIterator, imageList.size());
		    } catch (IOException ex) {
		        Logger.getLogger(MainStage.class.getName()).log(Level.SEVERE, null, ex);
		    }
    	}else{
    		if((dirIterator+1)<imageList.size()){
    			displayImage(imageList.get(++dirIterator));
    		}else{
        		alert("Directory emptied");
        	}
    	}
    }

    public void wipeCircles(){
        for (Circle cc : circleList){
            pane1.getChildren().remove(cc);
        }      
        circleList.removeAll(circleList);
    }

    public boolean canReadExtension(String fileExt) {
        Iterator i = ImageIO.getImageReadersBySuffix(fileExt);
        return i.hasNext();
}

    public void alert(String s){
    	nextclickmessage.setText("Directory emptied");
    	Stage dialogStage = new Stage();
    	dialogStage.initModality(Modality.WINDOW_MODAL);
    	dialogStage.setScene(new Scene(VBoxBuilder.create().
    	    children(new Text(s)).
    	    alignment(Pos.CENTER).padding(new Insets(5)).build()));
    	dialogStage.show();
    }

    public void setImageName(String name){
    	imageName.setText(name);
    }
    
    public void setDirCount(int number, int total){
    	dirCount.setText(String.format("%d/%d", number, total));
    }
}