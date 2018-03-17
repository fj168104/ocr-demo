package Utils;

import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.icepdf.core.exceptions.PDFException;
import org.icepdf.core.exceptions.PDFSecurityException;
import org.icepdf.core.pobjects.Document;
import org.icepdf.core.util.GraphicsRenderingHints;

/**
 * pdf文件转图片(icepdf技术) 不常用
 * @author songjinzhou
 * @day 2016-11-03
 */
public class PDF2IMGIcePdf {
    public static void main(String[] args) {
        String filePath = "/home/fengjiang/Documents/P020171222593212170499.pdf";
        Document document = new Document();

        try {
            document.setFile(filePath);
            float scale = 1.1f;// 缩放比例（大图）
            // float scale = 0.2f;// 缩放比例（小图）
            float rotation = 90f;// 旋转角度
            for (int i = 0; i < document.getNumberOfPages(); i++) {
                BufferedImage image = (BufferedImage) document.getPageImage(i,
                        GraphicsRenderingHints.SCREEN,
                        org.icepdf.core.pobjects.Page.BOUNDARY_CROPBOX,
                        rotation, scale);
                RenderedImage rendImage = image;
                try {
                    File file = new File("/home/fengjiang/Documents/icepdf_a" + i + ".jpg");
                    // 这里png作用是：格式是jpg但有png清晰度
                    ImageIO.write(rendImage, "jpg", file);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                image.flush();
            }
            document.dispose();
        } catch (PDFException e1) {
            e1.printStackTrace();
        } catch (PDFSecurityException e1) {
            e1.printStackTrace();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        System.out.println("======================完成============================");
    }
}