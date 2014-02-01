package faceclicker;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;

import javax.imageio.ImageIO;

public class DirectoryContents {
	
    private ArrayList<String> imageList, pointList;
    private File path;
    private int iterator;

    public DirectoryContents(File dirPath){
            path=dirPath;
            ArrayList<String> allFiles = listFiles(dirPath);
            imageList = parseImages(allFiles);
            pointList = parsePoints(allFiles);
            iterator=0;
    }
    
    public DirectoryContents(File dirPath, int i){
        super();
        iterator=i;
    }
	
    public ArrayList<String> listFiles(File directory) {
        ArrayList<String> fileNames = new ArrayList<>();

        //get all the files from a directory
        File[] fList = directory.listFiles();
        for (File file : fList) {
            if (file.isFile()) {
                fileNames.add(file.getAbsolutePath());
            }
        }
        return fileNames;
    }

    public ArrayList<String> parseImages(ArrayList<String> files){
    	ArrayList<String> images = new ArrayList<>();
    	for (String s : files){
    		String fileExt = s.substring(s.lastIndexOf(".") + 1);
    		if (canReadExtension(fileExt)) {
    			images.add(s);
    		}
    	}
    	return images;
    }

    public ArrayList<String> parsePoints(ArrayList<String> files){
    	ArrayList<String> x = new ArrayList<>();
    	for (String s : files){
    		String fileExt = s.substring(s.lastIndexOf(".") + 1);
    		if (fileExt.equals("txt")) {
    			x.add(s);
    		}
    	}
    	return x;
    }
    
    public ArrayList<String> extractFileNames(ArrayList<String> files){
    	ArrayList<String> fileNames = new ArrayList<>();
    	for (String s : files){
    		String fileName = s.substring(s.lastIndexOf("\\") + 1);
    		fileNames.add(fileName);
    	}
    	return fileNames;
    }
    
    public void refreshPointlist(){
        ArrayList<String> allFiles = listFiles(path);
            pointList = parsePoints(allFiles);
    }
    
    public boolean canReadExtension(String fileExt) {
        Iterator i = ImageIO.getImageReadersBySuffix(fileExt);
        return i.hasNext();
}
    
    public boolean hasNext(){
        return (iterator+1) < imageList.size();
    }
    
    public String getImage(int location){
    	iterator=location;
    	return imageList.get(location);
    }
    
    public String getNextImage(){ //put in a check here
    	iterator++;
    	return imageList.get(iterator);
    }
    
    public void iterate(){
    	iterator++;
    }

    public int getIterator(){
        return iterator;
    }
    
    public void setIterator(int i){
    	iterator = i;
    }

    public ArrayList<String> getImageList(){
        return imageList;
    }
    
    public ArrayList<String> getPointsList(){
        return pointList;
    }
    
    public ArrayList<String> getImageNamesList(){
        
        ArrayList<String> names = extractFileNames(imageList);
        ArrayList<String> returnList = new ArrayList<>();
        
        for(int i=0;i<names.size();i++){
            String s = String.format("%d. %s", i+1, names.get(i));
            returnList.add(s);
        }
        
        return returnList;
    }
}
