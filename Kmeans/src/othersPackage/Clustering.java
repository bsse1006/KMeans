package othersPackage;

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class Clustering
{
    private ArrayList<Color> rgbList;
    private int k;
    private ArrayList<ArrayList<Color>> clusters = new ArrayList<ArrayList<Color>>();
    private ArrayList<Color> meanPoints = new ArrayList<Color>();
    private ArrayList<Color> centrePoints = new ArrayList<>();
    private double eps = 0.001;

    public Clustering(ArrayList<Color> rgbList, int k) {
        this.rgbList = rgbList;
        this.k = k;
        for(int i=0; i<k; i++)
        {
            clusters.add(new ArrayList<Color>());
        }
        doClustering();
    }

    private void initializeMeans ()
    {
        for(int i=0; i<k; i++)
        {
            Random rand = new Random();
            meanPoints.add(rgbList.get(rand.nextInt(rgbList.size())));
        }
    }

    private double distance (Color c1, Color c2)
    {
        return Math.sqrt(Math.pow(c1.getBlue()-c2.getBlue(),2)+Math.pow(c1.getGreen()-c2.getGreen(),2)
                +Math.pow(c1.getRed()-c2.getRed(),2));
    }

    private void cluster ()
    {
        for(Color c: rgbList)
        {
            double distance = Double.MAX_VALUE;
            int index = Integer.MAX_VALUE;
            for(Color m: meanPoints)
            {
                if(distance(c,m)<distance)
                {
                    index = meanPoints.indexOf(m);
                    distance = distance(c,m);
                }
            }
            clusters.get(index).add(c);
        }
        calcCentrepoints();
    }

    private Color averageColor(ArrayList<Color> array) {
        long sumr = 0, sumg = 0, sumb = 0;
            for (Color pixel: array) {
                sumr += pixel.getRed();
                sumg += pixel.getGreen();
                sumb += pixel.getBlue();
            }
        int num = array.size();
        if(num!=0)
        {
            sumr = sumr/num;
            sumg = sumg/num;
            sumb = sumb/num;

            return new Color((int)sumr,(int)sumg,(int)sumb);
        }
        else
        {
            return new Color(0,0,0);
        }
    }

    private void calcCentrepoints ()
    {
        for(ArrayList<Color> array: clusters)
        {
            if(centrePoints.size()<clusters.indexOf(array)+1)
                centrePoints.add(clusters.indexOf(array),averageColor(array));
            else
                centrePoints.set(clusters.indexOf(array),averageColor(array));
        }
    }
    private boolean shouldStop ()
    {
        for(int i=0; i<k; i++)
        {
            if(distance(centrePoints.get(i),meanPoints.get(i))>eps)
                return false;
        }

        return true;
    }

    private void calcMeanPoints ()
    {
        for (int i=0; i<k; i++)
        {
            meanPoints.set(i,centrePoints.get(i));
        }
    }

    private void doClustering()
    {
        initializeMeans();

        while (true)
        {
            for(int i=0; i<k; i++)
            {
                clusters.get(i).clear();
            }
            cluster();
            if (shouldStop())
                break;
            calcMeanPoints();
        }
    }

    public ArrayList<ArrayList<Color>> getClusters() {
        return clusters;
    }

    public ArrayList<Color> getCentrePoints() {
        return centrePoints;
    }
}
