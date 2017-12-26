package hereis.opencv.demo;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

public class ZoomingEffect {
	public static void main( String[] args ) {
		   
	      try {
	         int zoomingFactor = 1;
	         System.loadLibrary( Core.NATIVE_LIBRARY_NAME );
	         
	         Mat source = Imgcodecs.imread("G:\\124339.jpg", Imgcodecs.CV_LOAD_IMAGE_GRAYSCALE);
	         Mat destination = new Mat(source.rows() * zoomingFactor, source.cols()*  zoomingFactor,source.type());  
	         
	         Imgproc.resize(source, destination, destination.size(),  zoomingFactor,zoomingFactor,Imgproc.INTER_NEAREST);
	         Imgcodecs.imwrite("G:\\zoomed.jpg", destination);
	         
	      } catch (Exception e) {
	         System.out.println("Error: "+e.getMessage());
	      }
	   }


}
