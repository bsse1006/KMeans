package othersPackage;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class OutputImage
{
    private BufferedImage image;

    private ArrayList<ArrayList<Color>> clusters;

    private ArrayList<Color> centrePoints;

    public OutputImage(BufferedImage image, ArrayList<ArrayList<Color>> clusters, ArrayList<Color> centrePoints) {
        this.image = image;
        this.clusters = clusters;
        this.centrePoints = centrePoints;
        compareCluster();
        outputImageFile();
    }
    private void compareCluster ()
    {
        for (int x = 0; x < image.getWidth(); x++) {
            for (int y = 0; y < image.getHeight(); y++) {
                Color c = new Color(image.getRGB(x,y));

                for(ArrayList<Color> cluster: clusters)
                {
                    if(cluster.contains(c))
                    {
                        image.setRGB(x,y,centrePoints.get(clusters.indexOf(cluster)).getRGB());
                    }
                }
            }
        }
    }

    private void outputImageFile ()
    {
        try {
            ImageIO.write(image, "jpg", new File("Photos/output.jpg"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}