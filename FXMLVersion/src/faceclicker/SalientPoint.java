package faceclicker;

public class SalientPoint {

	private double x, y;
	private String name;
	
	public SalientPoint(String name){
		x=0;
		y=0;
		this.name = name;
	}
	
	public void setX(double xx){
		x=xx;
	}
	
	public void setY(double yy){
		y=yy;
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
	
	//outputs the salient point's x and y values to std output
	public void printValues(){
    	System.out.printf("%s: x = %.0f y = %.0f \n", name, x, y);
	}
        
        public String toString(){
            return String.format("%s: x = %.0f y = %.0f", name, x, y);
        }
}