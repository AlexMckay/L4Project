package analysis;

import faceclicker.DirectoryContents;
import faceclicker.SalientPoint;
import faceclicker.SalientPointCollection;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;

public class Executor {

    public static void main(String[] args) throws FileNotFoundException {
        Calculator calc = new Calculator();
        String inputDir = "C:\\Users\\xen\\Pictures\\ProjectFaces";
        DirectoryContents dirContents = new DirectoryContents(new File(inputDir));
        String outputDir = inputDir + "\\Calculator_Output";
        ArrayList<String> pointsList = dirContents.getPointsList();

        double max = 0;
        double min = 99999;
        double temp;
        String maxName = "";
        String minName = "";
        int i = 0;
        String currentFileLoc, fileName;
        SalientPointCollection points;

        while (!pointsList.isEmpty()) {
            currentFileLoc = pointsList.remove(0);
            fileName = currentFileLoc.substring(currentFileLoc.lastIndexOf('\\') + 1, currentFileLoc.lastIndexOf('.'));
            points = calc.loadPoints(currentFileLoc);

            temp = calc.performCalculations(points);
            if (temp > max) {
                max = temp;
                maxName = fileName;
            }
            if (temp < min) {
                min = temp;
                minName = fileName;
            }           
            System.out.println(i++);
        }
        System.out.printf("Max: %s \nMin: %s\n", maxName, minName);
        System.out.println(max);
        System.out.println(min);
        
    }

}
