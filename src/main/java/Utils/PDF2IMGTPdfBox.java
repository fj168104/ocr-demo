package Utils;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.rendering.PDFRenderer;

public class PDF2IMGTPdfBox {

	public static void main(String[] args) {
		File file = new File("/home/fengjiang/Documents/P020171222593212170499.pdf");
		try {
			PDDocument doc = PDDocument.load(file);
			PDFRenderer renderer = new PDFRenderer(doc);
			int pageCount = doc.getNumberOfPages();
			for (int i = 0; i < pageCount; i++) {
				// 方式1,第二个参数是设置缩放比(即像素)
				BufferedImage image = renderer.renderImageWithDPI(i, 296);
				// 方式2,第二个参数是设置缩放比(即像素)
				// BufferedImage image = renderer.renderImage(i, 2.5f);
				ImageIO.write(image, "PNG", new File(String.format("/home/fengjiang/Documents/pdfbox_image_%d.png",i)));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}