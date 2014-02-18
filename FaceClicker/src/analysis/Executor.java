package analysis;

import faceclicker.DirectoryContents;
import faceclicker.SalientPoint;
import faceclicker.SalientPointCollection;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;

public class Executor {

    public static void main(String[] args) throws FileNotFoundException {
        Calculator calc;
        String inputDir = "C:\\Users\\xen\\Pictures\\ProjectFaces";
        DirectoryContents dirContents = new DirectoryContents(new File(inputDir));
        String outputDir = inputDir + "\\Calculator_Output";
        ArrayList<String> pointsList = dirContents.getPointsList();
        ArrayList<String> namesList = new ArrayList<>();
        ArrayList<Double> widthToHeight = new ArrayList<>();
        String currentFileLoc, fileName;
        int i=0;

        while (!pointsList.isEmpty()) {
            currentFileLoc = pointsList.remove(0);
            calc = new Calculator(currentFileLoc);
            fileName = currentFileLoc.substring(currentFileLoc.lastIndexOf('\\') + 1, currentFileLoc.lastIndexOf('.'));
            namesList.add(fileName);
            
            widthToHeight.add(calc.ratioWidthToHeight());
       
            System.out.println(i);
        }
        
        double maxWidthToHeight = Collections.max(widthToHeight);
        double minWidthToHeight = Collections.min(widthToHeight);
        String maxWidthToHeightName = namesList.get(widthToHeight.indexOf(maxWidthToHeight));
        String minWidthToHeightName = namesList.get(widthToHeight.indexOf(minWidthToHeight));
        
        System.out.printf("-Width to Height\nMax: %s \nMin: %s\n\n", maxWidthToHeightName, minWidthToHeightName);
        
    }

}
