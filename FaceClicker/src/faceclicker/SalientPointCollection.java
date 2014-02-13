package faceclicker;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

public class SalientPointCollection {

    ArrayList<String> spNames;	//values for the various salient points are set here from the config file
    ArrayList<SalientPoint> spArray;
    int currentPoint; //used to iterate through the list by storing the index value of the current salient point

    public SalientPointCollection() {
        currentPoint = 0;
        loadPoints();
        spArray = new ArrayList<>();

        //loops through the array of names and creates a salient point for each one
        for (String name : spNames) {
            spArray.add(new SalientPoint(name));
        }
    }

    //returns the current salient point
    public SalientPoint getCurrent() {
        if (currentPoint < spArray.size()) {
            return spArray.get(currentPoint);
        } else {
            return null;
        }
    }
    
    public SalientPoint get(int i){
        if (i < spArray.size()){
            return spArray.get(i);
        } else {
            return null;
        }
    }

    //checks if the list has any more salient points after the current
    public boolean hasNext() {
        return currentPoint != (spArray.size() - 1);
    }

    //moves to the next salient point in the list
    public void iterate() {
        currentPoint++;
    }

    public void undo() {
        if (currentPoint > 0) {
            currentPoint--;
        }
    }

    //outputs the list of x and y values for all the salient points to std output
    public void printPoints() {
        for (SalientPoint sp : spArray) {
            sp.printValues();
        }
    }

    //writes the list of x and y values for all the salient points to a file
    public void writePoints(String filename) throws FileNotFoundException, UnsupportedEncodingException {
        PrintWriter writer = new PrintWriter(filename, "UTF-8");
        for (SalientPoint sp : spArray) {
            writer.println(sp.toString());
        }
        writer.close();
    }

    //load salient points from config file
    public void loadPoints() {
        spNames = new ArrayList<>();

        try {
            FileInputStream fstream = new FileInputStream("config-points.txt");
            DataInputStream in = new DataInputStream(fstream);
            BufferedReader br = new BufferedReader(new InputStreamReader(in));
            String strLine;

            while ((strLine = br.readLine()) != null) {
                spNames.add(strLine);
            }
            in.close();

        } catch (IOException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }

}
