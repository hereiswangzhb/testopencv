package hereis.opencv.demo;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.imgcodecs.Imgcodecs;

public class EnhanceBright {
	static int width;
	static int height;
	static double alpha = 1;
	static double beta = 30;

	public static void main(String[] args) {

		try {
			System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
			Mat source = Imgcodecs.imread("G:\\124339.jpg",Imgcodecs.CV_LOAD_IMAGE_COLOR);
			Mat destination = new Mat(source.rows(), source.cols(),source.type());
			source.convertTo(destination, -1, alpha, beta);
			Imgcodecs.imwrite("G:\\5.jpg", destination);

		} catch (Exception e) {
			System.out.println("error: " + e.getMessage());
		}
	}

}
