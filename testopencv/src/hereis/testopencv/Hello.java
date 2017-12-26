package hereis.testopencv;

import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.awt.image.MemoryImageSource;
import java.io.File;
import java.io.IOException;

import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.Rect;
import org.opencv.core.Size;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

import javax.imageio.ImageIO;

public class Hello {
	public static void main( String[] args )
	{
	      System.loadLibrary( Core.NATIVE_LIBRARY_NAME );
	      
//			Mat mat = Mat.eye( 3, 3, CvType.CV_8UC1 );
//		    System.out.println( "mat = " + mat.dump() );
	      
	      Hello hello = new Hello();
	      
	      //读取图片
	      Mat source = hello.readImageToMat("G://image//1.jpg");
	      Imgcodecs.imwrite("G://image//after//8_0.jpg",source);
	      
	      //灰度处理
	      Mat dest = hello.toGray(source);	      
	      Imgcodecs.imwrite("G://image//after//8_1.jpg",dest);
	      
	      //除噪处理
	      Mat gau=new Mat();	   
	      Imgproc.GaussianBlur(dest, gau, new Size(10,10),0,0);
	      Imgcodecs.imwrite("G://image//after//8_2.jpg",gau);
	      
	      //二值化处理
	      Mat bit = hello.toBlackWhite(gau);
	      Imgcodecs.imwrite("G://image//after//8_3.jpg",bit);
	}
	
	/**	 
	   * @功能描述: 读取图片
	   * @设定文件：@return 
	   * @返回类型：BufferedImage 
	   * @throws ：	   
	 */
	public BufferedImage readImageToBuffered(String imageFilePath){	    
	    File input = new File(imageFilePath);
	    BufferedImage image = null;
	    try {
			image = ImageIO.read(input);
			
		} catch (IOException e) {			
			e.printStackTrace();
		}
	    return image;
	}
	
	/**
	   * @功能描述:读取图片 
	   * @设定文件：@param imageFilePath
	   * @设定文件：@return 
	   * @返回类型：Mat 
	   * @throws ：	   
	 */
	public Mat readImageToMat(String imageFilePath){
		Mat src = Imgcodecs.imread(imageFilePath);
		if(src.empty()) 
			return null;
	
		return src;
	}
	
	/**
	   * @功能描述: 将mat转换成BufferedImage
	   * @设定文件：@param grayMat 
	   * @返回类型：void 
	   * @throws ：	   
	 */
	public BufferedImage matToBufferedImage(Mat grayMat){		
        byte[] data1 = new byte[grayMat.rows() * grayMat.cols() * (int)(grayMat.elemSize())];
        grayMat.get(0, 0, data1);
        BufferedImage image = new BufferedImage(grayMat.cols(),
        grayMat.rows(),BufferedImage.TYPE_BYTE_GRAY);
        image.getRaster().setDataElements(0, 0, grayMat.cols(), grayMat.rows(), data1);   
        return image;
	}
	
	/**
	   * @功能描述: 将BufferedImage转换成Mat
	   * @设定文件： 
	   * @返回类型：void 
	   * @throws ：
	 */
	public Mat bufferedImageToMat(File input){
		BufferedImage src=null;
		try {
			src = ImageIO.read(input);
		} catch (IOException e) {			
			e.printStackTrace();
		}
	    Mat srcMat = new Mat(src.getHeight(), src.getWidth(), CvType.CV_8UC3);
	    return srcMat;
	}
	
	 public Image getImg(int width, int height, int[] pixels) {
		 MemoryImageSource source;
		 Image image = null;
		 source = new MemoryImageSource(width, height, pixels, 0, width);
		 image = Toolkit.getDefaultToolkit().createImage(source);
		 return image;
	 }
	
	//图片灰度化,即转为黑白
	public Mat toGray(Mat srcMat){
		Mat destMat=new Mat();
		Imgproc.cvtColor(srcMat, destMat, Imgproc.COLOR_RGB2GRAY);
		return destMat;
	}
	
	//二值化，即只保留黑白
	public Mat toBlackWhite(Mat grayMat){
		Mat binaryMat = new Mat(grayMat.height(),grayMat.width(),CvType.CV_8UC1);
	
//	    Imgproc.threshold(grayMat, binaryMat, max1, max2, Imgproc.THRESH_BINARY);		
		
		 Imgproc.threshold(grayMat, binaryMat, 100, 250, Imgproc.THRESH_OTSU);	
	
		//Imgproc.adaptiveThreshold(grayMat,binaryMat,200,Imgproc.ADAPTIVE_THRESH_GAUSSIAN_C,Imgproc.THRESH_BINARY,7,8);
		
		return binaryMat;
	}	
	
	/**	 
	   * @功能描述:使用3*3的图片去腐蚀 腐蚀后变得更加宽,粗.便于识别 
	   * @设定文件：@param srcMat
	   * @设定文件：@param destMat 
	   * @返回类型：void 
	   * @throws ：	   
	 */
	public void to3(Mat srcMat,Mat destMat){
		Mat element = Imgproc.getStructuringElement(Imgproc.MORPH_RECT, new Size(3, 3));
        Imgproc.erode(srcMat,destMat,element);
	}
	
	//遍历,获取和修改像素值
	public void to4(Mat imgSrc) {
//		for (int y = 0; y < imgSrc.height(); y++) {
//			for (int x = 0; x < imgSrc.width(); x++) {
//				//得到该行像素点的值
//				double[] data = imgSrc.get(y, x);
//				for (int i1 = 0; i1 < data.length; i1++) {
//					data[i1] = 255;// 像素点都改为白色
//				}
//				imgSrc.put(i, j, data);
//			}
//		}
	}
	
	/*过滤和切割要找到一个开始行或列,结束行或列
	 * 该算法从第一行开始遍历,统计每一行的像素点值符合阈值的个数,再根据个数判断该点是否为边界.
	 * 找到后过滤和切割自然就很简单了,直接创建个该范围的新的Mat对象.
	 */
	public void to5(Mat imgSrc){
		for (int y = 0; y < imgSrc.height(); y++)
        {
            int count = 0;
            for (int x = 0; x < imgSrc.width(); x++)
            {
                //得到该行像素点的值
                byte[] data = new byte[1];
                imgSrc.get(y, x, data);
                if (data[0] == 0)
                    count = count + 1;
            }
//            if (state == 0)//还未到有效行
//            {
//                if (count >= 150)//找到了有效行
//                {   
//                	//有效行允许十个像素点的噪声
//                    a = y;
//                    state = 1;
//                }
//            }
//            else if (state == 1)
//            {
//                if (count <= 150)//找到了有效行
//                {//有效行允许十个像素点的噪声
//                    b = y;
//                    state = 2;
//                }
//            }
        }
//        System.out.println("过滤下界"+Integer.toString(a));
//        System.out.println("过滤上界"+Integer.toString(b));
        
	    //截取
	    //参数,坐标X,坐标Y,截图宽度,截图长度
//	    Rect roi = new Rect(0, 0, imgSrc.width(), b - a);
//	    Mat res = new Mat(new Size(roi.width, roi.height),CvType.CV_8UC1);
	}
	

}
