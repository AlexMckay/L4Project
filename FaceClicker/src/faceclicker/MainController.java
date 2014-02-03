package faceclicker;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.collections.FXCollections;
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
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import javafx.stage.DirectoryChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;

import javax.imageio.ImageIO;

public class MainController {

    @FXML
    private Text nextclickmessage;
    @FXML
    private Pane pane1;
    @FXML
    private ImageView imageBox;
    @FXML
    private Label dirCount;
    @FXML
    private ComboBox<String> imageCombo;

    private SalientPointCollection points;
    private boolean activated = false; //activated is to check an image has been loaded
    private final ArrayList<Circle> circleList = new ArrayList<>();
    private String filePath, fileName;
    private DirectoryContents dirContents;
    private double curWidth, curHeight;

    @FXML
    protected void handleLoadDirButton(ActionEvent event) throws InterruptedException, IOException {

        Stage primaryStage = (Stage) imageBox.getScene().getWindow();

        DirectoryChooser chooser = new DirectoryChooser();
        chooser.setTitle("Choose Directory");
        File defaultDirectory = new File("c:/");
        chooser.setInitialDirectory(defaultDirectory);
        File dirPath = chooser.showDialog(primaryStage);
        dirContents = new DirectoryContents(dirPath);

        imageCombo.setItems(FXCollections.observableArrayList(dirContents.getImageNamesList()));

        displayImage(dirContents.getImage(0));

    }

    @FXML
    protected void handleNextImageButton(ActionEvent event) throws IOException {
        nextImage();
    }

    @FXML
    protected void handleComboChange(ActionEvent event) throws IOException {
        int i = imageCombo.getSelectionModel().getSelectedIndex();
        if (i > (-1) && i != dirContents.getIterator()) {
            displayImage(dirContents.getImage(i));
        }
    }

    @FXML
    protected void handleUndoButton(ActionEvent event) {
        undo();
    }

    @FXML
    protected void handleResetButton(ActionEvent event) {
        points = new SalientPointCollection();
        wipeCircles();
        nextclickmessage.setText(points.getCurrent().getName());
        activated = true;
    }

    //function that handles mouse clicks: stores x+y data in current SP and moves on to next one
    @FXML
    protected SalientPoint grabCoords(MouseEvent me) {
        double mouseX = me.getX();
        double mouseY = me.getY();

        if (activated) {
            if (mouseX <= curWidth && mouseY <= curHeight) {
                SalientPoint sp = points.getCurrent();
                sp.setX(mouseX);
                sp.setY(mouseY);

                circleList.add(sp.getCircle());
                pane1.getChildren().add(sp.getCircle());

                try {
                    points.writePoints(dirContents.getPointsFilePath(fileName));
                } catch (FileNotFoundException | UnsupportedEncodingException ex) {
                    Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
                }

                if (points.hasNext()) {
                    points.iterate();
                    //displays the name of the next point to click
                    nextclickmessage.setText(points.getCurrent().getName());
                } else {
                    nextclickmessage.setText("Image finished");
                    activated = false;
                }
            }
        }
        return null;
    }

    public void displayImage(String imageName) throws IOException {

        String fileExt = imageName.substring(imageName.lastIndexOf('.') + 1);

        dirContents.refreshPointlist();

        if (dirContents.canReadExtension(fileExt)) {

            points = new SalientPointCollection();
            wipeCircles();

            File file = new File(imageName);
            filePath = file.getAbsolutePath();
            fileName = file.getName();
            String pointsPath = dirContents.getPointsFilePath(fileName);

            if (dirContents.getPointsList().contains(pointsPath)) {
                loadPoints(pointsPath);
            } else {
                nextclickmessage.setText(points.getCurrent().getName());	//display first salient point to click
            }
            BufferedImage bufferedImage = ImageIO.read(file);
            Image image = SwingFXUtils.toFXImage(bufferedImage, null);

            curWidth = image.getWidth();
            curHeight = image.getHeight();
            activated = true;
            imageBox.setImage(image);
            setDirCount(dirContents.getIterator(), dirContents.getImageList().size());

        } else {
            if (dirContents.hasNext()) {
                System.out.println("picture skipped");
                displayImage(dirContents.getNextImage());
            } else {
                alert("Directory emptied");
            }
        }

        imageCombo.getSelectionModel().select(dirContents.getIterator());
    }

    public void wipeCircles() {
        for (Circle cc : circleList) {
            pane1.getChildren().remove(cc);
        }
        circleList.removeAll(circleList);
    }

    public void alert(String s) {
        nextclickmessage.setText(s);
        Stage dialogStage = new Stage();
        dialogStage.initModality(Modality.WINDOW_MODAL);
        dialogStage.setScene(new Scene(VBoxBuilder.create().
                children(new Text(s)).
                alignment(Pos.CENTER).padding(new Insets(5)).build()));
        dialogStage.show();
    }

    public void setDirCount(int number, int total) {
        dirCount.setText(String.format("%d/%d", number + 1, total));
    }

    public void loadPoints(String pointsFile) throws FileNotFoundException {
        File file = new File(pointsFile);
        String temp;
        Scanner fileScanner = new Scanner(file);

        while (fileScanner.hasNextLine()) {
            SalientPoint sp = points.getCurrent();
            int x, y;
            String line = fileScanner.nextLine();
            String pointName = line.substring(0, line.lastIndexOf(':'));
            String values = line.substring(line.lastIndexOf(':') + 2);

            Scanner lineScanner = new Scanner(values);
            temp = lineScanner.next(); //x
            temp = lineScanner.next(); //=
            x = lineScanner.nextInt();
            temp = lineScanner.next(); //y
            temp = lineScanner.next(); //=
            y = lineScanner.nextInt();

            if (x == 0 && y == 0) {
                activated = true;
                nextclickmessage.setText(points.getCurrent().getName());
                break;
            }
            sp.setX(x);
            sp.setY(y);
            circleList.add(sp.getCircle());
            pane1.getChildren().add(sp.getCircle());
            points.iterate();
            nextclickmessage.setText("Points loaded from file");

            lineScanner.close();
        }
        fileScanner.close();
    }

    public void undo() {
        if (activated) {
            points.undo();
            if (!circleList.isEmpty()) {
                pane1.getChildren().remove(circleList.remove(circleList.size() - 1));
            }
            nextclickmessage.setText(points.getCurrent().getName());
        } else {
            activated = true;
            pane1.getChildren().remove(circleList.remove(circleList.size() - 1));
            nextclickmessage.setText(points.getCurrent().getName());
        }
    }

    public void nextImage() {
        if (dirContents.hasNext()) {
            try {
                displayImage(dirContents.getNextImage());
            } catch (IOException ex) {
                Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            alert("Directory emptied");
        }
    }

    public void prevImage() {
        if (dirContents.getIterator() != 0) {
            try {
                displayImage(dirContents.getPrevImage());
            } catch (IOException ex) {
                Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

}
