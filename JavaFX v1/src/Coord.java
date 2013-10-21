
public class Coord {

	double x, y;
	
	public Coord(){
		x=0;
		y=0;
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
	
	public void printValues(){
    	System.out.printf("x = %.0f y = %.0f \n", x, y);
	}
}
