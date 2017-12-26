package hereis.opencv.demo;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

public class ImagePixel {
	BufferedImage image;
	int width;
	int height;

	public ImagePixel() {
		try {
			File input = new File("G:\\124339.jpg");
			image = ImageIO.read(input);
			width = image.getWidth();
			height = image.getHeight();

			int count = 0;

			for (int i = 0; i < height; i++) {

				for (int j = 0; j < width; j++) {

					count++;
					Color c = new Color(image.getRGB(j, i));
					System.out.println("S.No: " + count + " Red: " + c.getRed()
							+ "  Green: " + c.getGreen() + " Blue: "
							+ c.getBlue());
				}
			}

		} catch (Exception e) {
		}
	}

	static public void main(String args[]) throws Exception {
		ImagePixel obj = new ImagePixel();
	}


}
