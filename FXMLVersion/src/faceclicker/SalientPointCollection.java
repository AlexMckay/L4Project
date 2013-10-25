package faceclicker;

import java.util.ArrayList;
import java.util.List;


public class SalientPointCollection {
	
	
	String[] spNames = {"left eye", "right eye", "left nostril", "right nostril", "mouth (left corner)", "mouth (right corner)", "mouth (upper lip)", "mouth (lower lip)", "chin", "left ear", "right ear"};
	
	List<SalientPoint> spArray;
	int currentPoint;
	
	public SalientPointCollection(){
		spArray  = new ArrayList<SalientPoint>();
		currentPoint = 0;
		
		for(String name : spNames){
			spArray.add(new SalientPoint(name));
		}
	}
	
	public SalientPoint getCurrent(){
		if(currentPoint<spArray.size()){
			return spArray.get(currentPoint);
		}else{return null;}
	}
	
	public boolean hasNext(){
		if(currentPoint==spArray.size()){
			return false;
		}else{return true;}
	}
	
	public void iterate(){
		currentPoint++;
	}

}
