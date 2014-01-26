package faceclicker;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class SalientPoint {

	private double x, y;
	private String name;
	private Circle c;
	
	public SalientPoint(String name){
		x=0;
		y=0;
		this.name = name;
		c = new Circle();
    	c.setRadius(2);
    	c.setFill(Color.RED);
	}
	
	public void setX(double x){
		this.x=x;
		c.setCenterX(x);
	}
	
	public void setY(double y){
		this.y=y;
		c.setCenterY(y);
	}
	
	public double getX(){
		return x;
	}
	
	public double getY(){
		return y;
	}
	
	public String getName(){
		return name;
	}
	
	public Circle getCircle(){
		return c;
	}
	
	//outputs the salient point's x and y values to std output
	public void printValues(){
    	System.out.printf("%s: x = %.0f y = %.0f \n", name, x, y);
	}
        
        public String toString(){
            return String.format("%s: x = %.0f y = %.0f", name, x, y);
        }
}