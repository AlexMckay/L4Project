/**
 * 
 */
package dip;

/**
 * @author ravi
 * 
 */
public class ImageTool {

	public void convertToGrayscale(Image srcImg, Image tgtImg) {
		int srcDataRed[][] = srcImg.getDataRed();
		int tgtData[][] = tgtImg.getDataRed();
		for (int i = 0; i < tgtImg.getNumRows(); i++) {
			for (int j = 0; j < tgtImg.getNumCols(); j++) {

				tgtData[i][j] = srcDataRed[i][j];

			}
		}
		tgtImg.setDataRed(tgtData);
	}

	public void copyImg(Image srcImg, Image tgtImg) {
		int srcDataRed[][] = srcImg.getDataRed();
		int tgtDataRed[][] = tgtImg.getDataRed();
		int srcDataGreen[][] = srcImg.getDataGreen();
		int tgtDataGreen[][] = tgtImg.getDataGreen();
		int srcDataBlue[][] = srcImg.getDataBlue();
		int tgtDataBlue[][] = tgtImg.getDataBlue();

		for (int i = 0; i < tgtImg.getNumRows(); i++) {
			for (int j = 0; j < tgtImg.getNumCols(); j++) {

				tgtDataRed[i][j] = srcDataRed[i][j];
				if (srcImg.isColor()) {
					tgtDataGreen[i][j] = srcDataGreen[i][j];
					tgtDataBlue[i][j] = srcDataBlue[i][j];
				}

			}
		}
		tgtImg.setDataRed(tgtDataRed);
		if (srcImg.isColor()) {
			tgtImg.setDataGreen(tgtDataGreen);
			tgtImg.setDataBlue(tgtDataBlue);
		}
	}

	public void binarize(Image srcImg, Image tgtImg, int threshold) {
		int srcDataRed[][] = srcImg.getDataRed();
		int tgtData[][] = tgtImg.getDataRed();
		for (int i = 0; i < tgtImg.getNumRows(); i++) {
			for (int j = 0; j < tgtImg.getNumCols(); j++) {
				if (srcDataRed[i][j] > threshold)
					tgtData[i][j] = 255;
				else
					tgtData[i][j] = 0;
			}
		}
		tgtImg.setDataRed(tgtData);
	}

	/**
	 * This method scales down the srcImg to 25%. Every 4 px to 1px
	 * 
	 * @param srcImg
	 * @param tgtImg
	 * @param threshold
	 */
	public void scale4to1(Image srcImg, Image tgtImg) {

		int srcDataRed[][] = srcImg.getDataRed();
		int tgtData[][] = tgtImg.getDataRed();
		int neighbourX = 0, neighbourY = 0;

		for (int i = 0; i < tgtImg.getNumRows(); i++) {
			for (int j = 0; j < tgtImg.getNumCols(); j++) {
				int tgtDataVal = 0;
				neighbourX = i * 2;
				neighbourY = j * 2;
				// top left
				tgtDataVal = tgtDataVal + getTgtDataVal(srcDataRed, neighbourX, neighbourY);

				// top right
				neighbourY++;
				tgtDataVal = tgtDataVal + getTgtDataVal(srcDataRed, neighbourX, neighbourY);

				// bottom right
				neighbourX++;
				tgtDataVal = tgtDataVal + getTgtDataVal(srcDataRed, neighbourX, neighbourY);

				// bottom left
				neighbourY--;
				tgtDataVal = tgtDataVal + getTgtDataVal(srcDataRed, neighbourX, neighbourY);

				tgtData[i][j] = tgtDataVal / 4;

			}
		}
		tgtImg.setDataRed(tgtData);
	}

	private int getTgtDataVal(int[][] srcImgData, int neighbourX, int neighbourY) {
		int tgtDataVal = 0;
		try {
			tgtDataVal = srcImgData[neighbourX][neighbourY];
		} catch (ArrayIndexOutOfBoundsException e) {
			System.out.println("expection at " + neighbourX + ' ' + neighbourY);
		}
		return tgtDataVal;
	}
}
