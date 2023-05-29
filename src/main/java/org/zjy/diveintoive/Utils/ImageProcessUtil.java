package org.zjy.diveintoive.Utils;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ImageProcessUtil {

    /**
     * merge two wallpapers
     * the upper half is dale,the lower half is cheez
     * after merging, rotate the image(optional)
     */
    public void mergeWallPaper(){
        BufferedImage daeul_image = null;
        BufferedImage cheez_image = null;
        File daeul_f = null;
        File cheez_f = null;
        File output = new File("/Users/zjy/Desktop/modified.JPEG");

        try {
            daeul_f = new File(
                    "/Users/zjy/Desktop/daeul.JPEG");
            cheez_f = new File("/Users/zjy/Desktop/cheez.JPEG");
            daeul_image = ImageIO.read(daeul_f);
            cheez_image = ImageIO.read(cheez_f);
        }
        catch (IOException e) {
            System.out.println(e);
            return;
        }

        int twidth = daeul_image.getWidth();
        int theight = daeul_image.getHeight();
        int hwidth = twidth;
        int hheight = theight/2;
        //System.out.println(width+" "+height);
        for (int i = 0;i<hheight;i++){
            for (int j = 0;j<hwidth;j++){
                daeul_image.setRGB(j,i,cheez_image.getRGB(twidth-j-1,theight-i-1));
            }
        }
        try {
            ImageIO.write(daeul_image,"jpeg",output);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        // sips -r 180 modified.JPEG -s format jpeg --out rotated.JPEG
        // for rotating the image
    }
}
