package analysis;
   
import faceclicker.DirectoryContents;
import faceclicker.SalientPoint;
import faceclicker.SalientPointCollection;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/*
0 left eyebrow outer
1 left eyebrow inner
2 right eyebrow inner
3 right eyebrow outer
4 left eye outer
5 left eye center
6 left eye inner
7 right eye inner
8 right eye center
9 right eye outer
10 left nostril
11 right nostril
12 left ear
13 right ear
14 mouth left
15 mouth right
16 mouth top
17 mouth bottom
18 chin left
19 chin right
20 chin center
21 forehead top    
*/
public class Calculator {
    
    public double performCalculations(SalientPointCollection s){

        SalientPoint fore = s.get(21);
        SalientPoint eyeL = s.get(6);
        SalientPoint eyeR = s.get(7);
        SalientPoint noseL = s.get(10);
        SalientPoint noseR = s.get(11);
        SalientPoint mouthL = s.get(14);
        SalientPoint mouthR = s.get(15);
        SalientPoint mouthT = s.get(16);
        SalientPoint mouthB = s.get(17);
        SalientPoint earL = s.get(12);
        SalientPoint earR = s.get(13);
        SalientPoint chin = s.get(20);
        
        SalientPoint eyeMid = new SalientPoint("eyeMid",
                                ((eyeL.getX() + eyeR.getX())/2),
                                ((eyeL.getY() + eyeR.getY())/2));
        
        SalientPoint noseMid = new SalientPoint("noseMid",
                                ((noseL.getX() + noseR.getX())/2),
                                ((noseL.getY() + noseR.getY())/2));        
        
        double topToChin = lengthBetween(chin, fore);
        double eyesToChin = lengthBetween(eyeMid, chin);
        double topToNostril = lengthBetween(fore, noseMid);
        double topToPupils = lengthBetween(fore, eyeMid);
        double nostrilsToChin = lengthBetween(noseMid, chin);
        double lipsToChin = lengthBetween(mouthB, chin);
        double earToEar = lengthBetween(earL, earR);
        
        double ratioEyesToLength = ratio(topToChin, topToPupils);
        double ratioWidthToHeight = ratio(earToEar, topToChin);
        
        return ratioWidthToHeight;
        
    }
    
    private double lengthBetween(SalientPoint a, SalientPoint b){
        double i = (a.getX()-b.getX());
        double j = (a.getY()-b.getY());
        double k = (i*i)+(j*j);
        return Math.sqrt(k);
    }
    
    private double ratio(double a, double b){
        return a/b;
    }    
    
    public SalientPointCollection loadPoints(String pointsFile) throws FileNotFoundException {
        SalientPointCollection points = new SalientPointCollection();
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

            if ((x == 0 && y == 0)) {
                break;
            }
            sp.setX(x);
            sp.setY(y);
            points.iterate();

            lineScanner.close();
        }
        fileScanner.close();
        if (points.getCurrent() != null){
            //nextclickmessage.setText(points.getCurrent().getName());
        }
        
        return points;
    }
    
}
