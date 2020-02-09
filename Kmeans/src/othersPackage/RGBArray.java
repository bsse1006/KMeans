package othersPackage;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class RGBArray
{
    private ArrayList<Color> rgbList = new ArrayList<Color>();
    private BufferedImage image;

    public RGBArray(BufferedImage image) {
        this.image=image;
        fillRGBArray();
    }

    private void fillRGBArray ()
    {
        for (int x = 0; x < image.getWidth(); x++) {
            for (int y = 0; y < image.getHeight(); y++) {
                rgbList.add(new Color(image.getRGB(x,y)));
            }
        }
    }

    public ArrayList<Color> getRGBList() {
        return rgbList;
    }
}
