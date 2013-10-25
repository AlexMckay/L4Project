package faceclicker;

import java.util.ArrayList;
import java.util.List;


public class SalientPointCollection {
	
	//values for the various salient points are set here
	String[] spNames = {"left eye", "right eye", "left nostril", "right nostril", "mouth (left corner)", "mouth (right corner)", "mouth (upper lip)", "mouth (lower lip)", "chin", "left ear", "right ear"};
	
	List<SalientPoint> spArray;
	//currentPoint is used to iterate through the list by storing the index value of the current salient point
	int currentPoint;
	
	public SalientPointCollection(){
		spArray  = new ArrayList<SalientPoint>();
		currentPoint = 0;
		
		//loops through the array of names and creates a salient point for each one
		for(String name : spNames){
			spArray.add(new SalientPoint(name));
		}
	}
	
	//returns the current salient point
	public SalientPoint getCurrent(){
		if(currentPoint<spArray.size()){
			return spArray.get(currentPoint);
		}else{return null;}
	}
	
	//checks if the list has any more salient points after the current
	public boolean hasNext(){
		if(currentPoint==(spArray.size()-1)){
			return false;
		}else{return true;}
	}
	
	//moves to the next salient point in the list
	public void iterate(){
		currentPoint++;
	}
	
	//outputs the complete list of x and y values for all the salient points to std output
	public void printPoints(){
		for (SalientPoint sp : spArray){
			sp.printValues();
		}
	}

}
