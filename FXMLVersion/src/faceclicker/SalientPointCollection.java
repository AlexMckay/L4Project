package faceclicker;

import java.util.ArrayList;
import java.util.List;


public class SalientPointCollection {
	
	List<SalientPoint> spArray;
	String[] spNames = {"eyes", "nose", "mouth"};
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
			System.out.println(spArray.get(currentPoint).getName());
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
