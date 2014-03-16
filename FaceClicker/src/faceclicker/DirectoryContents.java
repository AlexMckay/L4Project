package faceclicker;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;

import javax.imageio.ImageIO;

/**
 * Stores data relating to files being used by the program. It
 * has lists containing the images to be annotated, and coordinate data for
 * previously annotated images. It also contains an iterator to store the user's
 * current position in the directory (ie which picture they are currently
 * annotating).
 *
 * @author Alex McKay
 */
public class DirectoryContents {

    /* Arraylists to hold the paths to every image and file with coordinate data respectively */
    private ArrayList<String> imageList, pointList;

    /* Stores the directory which contains files with coordinate data */
    private File pointsDirectory;

    /* Stores the user's current position in the directory */
    private int iterator;

    /**
     * Constructor method. Initialises the file lists and sets the iterator to
     * 0.
     *
     * @param dirPath a File object set to the path of the directory of
     * images you wish to annotate.
     */
    public DirectoryContents(File dirPath) {
        iterator = 0;

        //retrieve all files from the images directory, then filter out only the images
        ArrayList<String> allFiles = listFiles(dirPath);
        imageList = filterImages(allFiles);

        //set the points path to be the folder "FaceClicker_Output inside the images directory
        String pointsPathString = dirPath.getAbsolutePath() + "\\FaceClicker_Output";
        pointsDirectory = new File(pointsPathString);

        if (!pointsDirectory.exists())
            pointsDirectory.mkdir();

        //retrieve all files from the points directory, then filter out only the points files
        ArrayList<String> pointsFiles = listFiles(pointsDirectory);
        pointList = filterPoints(pointsFiles);
    }

    /**
     * Constructor method, allowing specification of current location.
     *
     * @param dirPath a File object set to the path of the directory of
     * images you wish to annotate.
     * @param i the position the iterator should be set to.
     */
    public DirectoryContents(File dirPath, int i) {
        super();
        iterator = i;
    }

    /**
     * Retrieves all files in the current directory and returns them as an 
     * ArrayList. 
     * @param directory a File object associated with the directory you want to 
     * inspect.
     * @return
     */
    public ArrayList<String> listFiles(File directory) {
        ArrayList<String> fileNames = new ArrayList<>();

        //get all the files from a directory
        File[] fList = directory.listFiles();
        
        for (File file : fList)
            if (file.isFile())
                fileNames.add(file.getAbsolutePath());
        
        return fileNames;
    }

    /**
     * Looks through a list of files and returns a list of all the displayable 
     * images from within the original list.
     * 
     * @param files The list of files to be examined.
     * @return
     */
    public ArrayList<String> filterImages(ArrayList<String> files) {
        ArrayList<String> images = new ArrayList<>();
        
        for (String s : files)
            if (isImage(s))
                images.add(s);
        
        return images;
    }

    /**
     * Looks through a list of files and returns a list of all the text files 
     * from within the original list. As coordinate data for each image is 
     * stored in text files this function is used to retrieve lists of files 
     * associated with coordinate data.
     * 
     * @param files The list of files to be examined.
     * @return
     */
    public ArrayList<String> filterPoints(ArrayList<String> files) {
        ArrayList<String> x = new ArrayList<>();
        
        for (String s : files) {
            String fileExt = s.substring(s.lastIndexOf(".") + 1);
            if (fileExt.equals("txt"))
                x.add(s);
        }
        
        return x;
    }

    /**
     * Looks through a list of file paths and returns a list of file names based 
     * on the original list.
     *
     * @param files The list of files to be examined.
     * @return
     */
    public ArrayList<String> extractFileNames(ArrayList<String> files) {
        ArrayList<String> fileNames = new ArrayList<>();
        
        for (String s : files) {
            String fileName = s.substring(s.lastIndexOf("\\") + 1);
            fileNames.add(fileName);
        }
        
        return fileNames;
    }

    /**
     * Refreshes the list of files with coordinate data. Used to ensure new 
     * annotations the user has made are viewable within the same session.
     */
    public void refreshPointlist() {
        ArrayList<String> files = listFiles(pointsDirectory);
        pointList = filterPoints(files);
    }

    /**
     * Check that a file corresponds to a displayable image. This works by 
     * checking the extension of the file.
     *
     * @param fileName The file to be checked.
     * @return True if image is displayable.
     */
    public boolean isImage(String fileName) {
        
        //parse the file extension from the name
        String fileExt = fileName.substring(fileName.lastIndexOf(".") + 1);
        
        //if i contains anything at all, a reader exists to display this image type
        Iterator i = ImageIO.getImageReadersBySuffix(fileExt);
        return i.hasNext();
    }

    /**
     * Checks that the user has not reached the end of the directory of images.
     *
     * @return True if iterator is not at the end of the directory.
     */
    public boolean hasNext() {
        return (iterator + 1) < imageList.size();
    }

    /**
     * Retrieve an image from a certain point in the directory. The iterator is 
     * also reset to the position of this image.
     *
     * @param location The position of the image in the ArrayList.
     * @return A string corresponding to the path to the image.
     */
    public String getImage(int location) {
        iterator = location;
        return imageList.get(location);
    }

    /**
     * Retrieve the next image in the directory. Increases the iterator by 1.
     *
     * @return A string corresponding to the path to the image.
     */
    public String getNextImage() {
        return imageList.get(++iterator);
    }

    /**
     * Retrieve the previous image in the directory. Decreases the iterator by 1.
     *
     * @return A string corresponding to the path to the image.
     */
    public String getPrevImage() {
        return imageList.get(--iterator);
    }

    /**
     * Increase the iterator by 1.
     */
    public void iterate() {
        iterator++;
    }

    /**
     * Get current position of iterator within the directory.
     *
     * @return Position of the iterator.
     */
    public int getIterator() {
        return iterator;
    }

    /**
     * Change the value of the iterator within the directory.
     *
     * @param i The value to set the iterator to.
     */
    public void setIterator(int i) {
        iterator = i;
    }

    /**
     * Retrieve the list of images.
     *
     * @return An ArrayList of Strings containing paths to each image.
     */
    public ArrayList<String> getImageList() {
        return imageList;
    }

    /**
     * Retrieve the list of files containing coordinate data.
     *
     * @return An ArrayList of Strings containing paths to each file.
     */
    public ArrayList<String> getPointsList() {
        return pointList;
    }

    /** 
     * Retrieve a numbered list of image filenames based on the current list of 
     * image paths. Will append a number to the start of the filename equivalent
     * to the position of that image within the list. For use in the drop-down 
     * menu available to the user to select a new image.
     *
     * @return An ArrayList of Strings containing image filenames. 
     */
    public ArrayList<String> getImageNamesList() {

        ArrayList<String> names = extractFileNames(imageList);
        ArrayList<String> returnList = new ArrayList<>();

        for (int i = 0; i < names.size(); i++) {
            String s = String.format("%d. %s", i + 1, names.get(i));
            returnList.add(s);
        }

        return returnList;
    }

    /**
     * Retrieve the path to the directory containing the files with coordinate 
     * data. 
     *
     * @return A string containing the path to the directory.
     */
    public String getPointsPathString() {
        return pointsDirectory.getAbsolutePath();
    }

    /**
     * Takes in a filename and returns the path to that file's corresponding 
     * coordinate data file. 
     *
     * @param fileName The name of the image file.
     * @return A string containing the path to the new file.
     */
    public String getPointsFilePath(String fileName) {
        return getPointsPathString() + "\\" + fileName + ".txt";
    }
}
