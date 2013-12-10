/**
 * 
 */
package dip.driver;

import dip.Image;
import dip.ImageTool;

/**
 * @author ravi
 * 
 */
public class IpToolTester {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ImageTool imageTool = new ImageTool();
		String srcPath = "lena_384.pgm";

		Image srcImg = new Image(srcPath);

		Image tgtImg = new Image();
		tgtImg.init(srcImg.getNumRows(), srcImg.getNumCols());

		imageTool.copyImg(srcImg, tgtImg);
		tgtImg.save("lena_write.pgm");

		System.out.println("Done");

	}

}
