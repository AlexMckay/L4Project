package faceclicker;
   
import java.util.ArrayList;

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
    
    String outputDir;
    
    public Calculator(String dir){
        outputDir = dir + "\\Calculations";
    }
    
    public double performCalculations(SalientPointCollection s){
        double topToChin = lengthBetween(s.get(20), s.get(21));
        double pupilsToChin = (lengthBetween(s.get(7), s.get(20)) + lengthBetween(s.get(6), s.get(20)))/2;
        double topToNostril = (lengthBetween(s.get(10), s.get(21)) + lengthBetween(s.get(11), s.get(21)))/2;
        double topToPupils = (lengthBetween(s.get(7), s.get(21)) + lengthBetween(s.get(6), s.get(21)))/2;
        double nostrilsToChin = (lengthBetween(s.get(10), s.get(20)) + lengthBetween(s.get(11), s.get(20)))/2;
        double lipsToChin = (lengthBetween(s.get(16), s.get(21)) + lengthBetween(s.get(17), s.get(21)))/2;
        double earToEar = lengthBetween(s.get(12), s.get(13));
        
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
    
}
