package hereis.opencv.demo;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

public class AddBorder {
	public static void main( String[] args ) {
		   
	      try {
	      
	         System.loadLibrary( Core.NATIVE_LIBRARY_NAME );
	         Mat source = Imgcodecs.imread("digital_image_processing.jpg",Imgcodecs.CV_LOAD_IMAGE_COLOR);
	         Mat destination = new Mat(source.rows(),source.cols(),source.type());
	         
	         int top, bottom, left, right;
	         int borderType;

	         /// Initialize arguments for the filter
	         top = (int) (0.05*source.rows()); 
	         bottom = (int) (0.05*source.rows());
	         left = (int) (0.05*source.cols()); 
	         right = (int) (0.05*source.cols());

	         destination = source;
	        
//	         Imgproc.copyMakeBorder(source, destination, top, bottom, left, right, Imgproc.BORDER_WRAP);
	         
	         Imgcodecs.imwrite("borderWrap.jpg", destination);
	         
	      }catch (Exception e) {
	         System.out.println("error: " + e.getMessage());
	      }
	   }


}
