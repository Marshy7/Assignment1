package imageprocessing;


import java.awt.Color;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import edu.princeton.cs.introcs.Picture;


public class ConnectedComponentImage {

	private Picture picture, pictureBinary;
	private int[][] pixelArray;
	private int labelNo, intLumValue;
	private Boolean blnWhiteBackground;
	private Set<Integer> keySet;
		

	
	public ConnectedComponentImage(String fileLocation, Boolean blnBackground, int intLuminance) {
		picture = new Picture(fileLocation);
		pixelArray = new int[picture.width()][picture.height()];
		blnWhiteBackground = blnBackground;
		intLumValue = intLuminance;
			
	}

	
	public int countComponents() {
		
		labelNo = 1;
		QuickUnion QULabels = new QuickUnion();
		
		
		for( int y = 0; y < pictureBinary.height(); y++){
			for( int x = 0; x < pictureBinary.width(); x++){
				if(pictureBinary.get(x, y).equals(Color.WHITE)){
					
					int[] neighbours = new int[2];
					if(x != 0 ){
						neighbours[0] = pixelArray[x-1][y];
					}
					else{
						neighbours[0] = 0;
					}
					if(y!= 0 ){
						neighbours[1] = pixelArray[x][y-1];
					}
					else{
						neighbours[1] = 0;
					}
					
					if(neighbours[0] > 0 && neighbours[1] > 0){
						pixelArray[x][y] = neighbours[0];
						QULabels.union(neighbours[0], neighbours[1]);
					}
					else if (neighbours[0] > 0 && neighbours[1] == 0){
						pixelArray[x][y] = neighbours[0];
					}
					else if (neighbours[0] == 0 && neighbours[1] > 0){
						pixelArray[x][y] = neighbours[1];
					}
					else{
						pixelArray[x][y] = labelNo++;
						QULabels.addLabel();
					}
					
					
				}
				else{
					pixelArray[x][y] = 0;
				}
				
			}
		}
		
		for( int y = 0; y < pictureBinary.height(); y++){
			for( int x = 0; x < pictureBinary.width(); x++){
				pixelArray[x][y] = QULabels.root(pixelArray[x][y]);
			}
		}
		
		
		
		return QULabels.labelCount();
	}

	
	public Picture identifyComponentImage() {
		
		Picture pictureHighlighted = new Picture(pictureBinary);
				
		for(int i: keySet){
			
			int[] highlight = {pictureBinary.width(),pictureBinary.height(),
				0,0};
						
			for( int y = 0; y < pictureBinary.height(); y++){
				for( int x = 0; x < pictureBinary.width(); x++){
					if(pixelArray[x][y] == i && x < highlight[0]){
						highlight[0] = x;
					}
					if(pixelArray[x][y] == i && x > highlight[2]){
						highlight[2] = x;
					}
				}
				
			}
			
			for( int x = 0; x < pictureBinary.width(); x++){
				for( int y = 0; y < pictureBinary.height(); y++){
					if(pixelArray[x][y] == i && y < highlight[1]){
						highlight[1] = y;
					}
					if(pixelArray[x][y] == i && y > highlight[3]){
						highlight[3] = y;
					}
				}
				
			}
			
			for(int x = highlight[0]; x <= highlight[2]; x++){
				pictureHighlighted.set(x, highlight[1], Color.RED);
				pictureHighlighted.set(x, highlight[3], Color.RED);
			}
			
			for(int y = highlight[1]; y <= highlight[3]; y++){
				pictureHighlighted.set(highlight[0], y, Color.RED);
				pictureHighlighted.set(highlight[2], y, Color.RED);
			}
				
			
		}

		return pictureHighlighted;

	}

	
	public Picture colourComponentImage() {
		
		Map<Integer, Color> mColors = new HashMap<Integer, Color>();
		Random rand = new Random();
		
		Picture pictureColoured = new Picture(pictureBinary);
		for(int y = 0; y < pictureColoured.height(); y++){
			for(int x = 0; x< pictureColoured.width(); x++){
				if(pixelArray[x][y] != 0){
					if(!mColors.containsKey(pixelArray[x][y])){
						mColors.put(pixelArray[x][y], new Color(rand.nextInt(0xFFFFFF)));
					}
									
						
					pictureColoured.set(x, y, mColors.get(pixelArray[x][y]));
				}
			}
		}
		
		keySet = mColors.keySet();

		return pictureColoured;

	}

	public Picture getPicture() {
		
		return picture;
	}

	
	public Picture binaryComponentImage() {
				
		pictureBinary = new Picture(picture);
		
		for(int y = 0; y < pictureBinary.height(); y++){
			for(int x = 0; x< pictureBinary.width(); x++){
				if(blnWhiteBackground){
					if(Luminance.lum(pictureBinary.get(x, y)) > intLumValue){
						pictureBinary.set(x, y, Color.BLACK);
					}
					else{
						pictureBinary.set(x, y, Color.WHITE);
					}
				}
				else{
					if(Luminance.lum(pictureBinary.get(x, y)) < intLumValue){
						pictureBinary.set(x, y, Color.BLACK);
					}
					else{
						pictureBinary.set(x, y, Color.WHITE);
					}
				}			
				
			}
		}
			

		return pictureBinary;
	}

}
