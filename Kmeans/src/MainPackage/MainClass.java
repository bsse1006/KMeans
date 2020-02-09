package MainPackage;

import othersPackage.Clustering;
import othersPackage.OutputImage;
import othersPackage.RGBArray;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class MainClass
{
    public static void main (String [] args)
    {
        Scanner cin = new Scanner (System.in);
        System.out.println("Enter image path:");
        String imagePath = cin.nextLine();
        System.out.println("Enter K:");
        int k = cin.nextInt();
        BufferedImage image = null;
        try {
            image = ImageIO.read(new File(imagePath));
        } catch (IOException e) {
            e.printStackTrace();
        }

        RGBArray rgbArrayObject = new RGBArray(image);

        Clustering clustering = new Clustering(rgbArrayObject.getRGBList(),k);

        OutputImage output = new OutputImage(image,clustering.getClusters(),clustering.getCentrePoints());

        /*Color kutkut = averageColor(image,0,0,image.getWidth(),image.getHeight());

        for (int x = 0; x < image.getWidth(); x++) {
            for (int y = 0; y < image.getHeight(); y++) {
                image.setRGB(x,y, kutkut.getRGB());
            }
        }

        try {
            ImageIO.write(image, "jpg", new File("Photos/output.jpg"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Color averageColor(BufferedImage bi, int x0, int y0, int w,
                                     int h) {
        int x1 = x0 + w;
        int y1 = y0 + h;
        long sumr = 0, sumg = 0, sumb = 0;
        for (int x = x0; x < x1; x++) {
            for (int y = y0; y < y1; y++) {
                Color pixel = new Color(bi.getRGB(x, y));
                sumr += pixel.getRed();
                sumg += pixel.getGreen();
                sumb += pixel.getBlue();
            }
        }
        int num = w * h;
        sumr = sumr/num;
        sumg = sumg/num;
        sumb = sumb/num;
        return new Color((int)sumr,(int)sumg,(int)sumb);*/
    }
}