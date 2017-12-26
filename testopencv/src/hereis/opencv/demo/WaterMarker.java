package hereis.opencv.demo;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.Point;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.imgcodecs.Imgcodecs;

public class WaterMarker {
	public static void main( String[] args ){
		WaterMarker waterMarker=new WaterMarker();
		waterMarker.test1();
	}
	
	//合成图片
	public void test1(){
		try{
	         System.loadLibrary( Core.NATIVE_LIBRARY_NAME );
	         
	         Mat source = Imgcodecs.imread("G:\\124339.jpg",  Imgcodecs.CV_LOAD_IMAGE_COLOR);
	         Mat waterMark = Imgcodecs.imread("G:\\3.png",  Imgcodecs.CV_LOAD_IMAGE_COLOR);
	         Rect ROI = new Rect(waterMark.rows() * 4,waterMark.cols(),  waterMark.cols(),waterMark.rows());
	         
	         Core.addWeighted(source.submat(ROI), 0.8, waterMark, 0.2, 1,  source.submat(ROI));
	         Imgcodecs.imwrite("G:\\watermarkedImage.jpg", source);
	         
	      } catch (Exception e) {
	         System.out.println("Error: " + e.getMessage());
	      }
	}
	
	//合成文字
	public void test(){
		try{
	         System.loadLibrary( Core.NATIVE_LIBRARY_NAME );
	         Mat source = Imgcodecs.imread("digital_image_processing.jpg",  Imgcodecs.CV_LOAD_IMAGE_COLOR);
	         Mat destination = new Mat(source.rows(),source.cols(), source.type());  
	         
	       
//	         Core.putText(source, "Tutorialspoint.com", new Point(source.rows()/2,source.cols()/2), Core.FONT_ITALIC,new Double(1),new  Scalar(255));

	         Imgcodecs.imwrite("watermarked.jpg", source);
	         
	      } catch (Exception e) {
	         System.out.println("Error: "+e.getMessage());
	      }

	}


	   


}
