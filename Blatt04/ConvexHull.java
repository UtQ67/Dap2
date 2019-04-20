
import java.util.*;
class ConvexHull
{

    public static void main(String[] args) {
        Point[] p = {new Point(10d,10d), new Point(10d, 100d), new Point(100d,10d), new Point(getRandom(), getRandom())};
        ConvexHull c = new ConvexHull();
        List<Point> hull = c.simpleConvex(p);

        for (Point pw : hull) {
            System.out.println(pw.get(0) + " " + pw.get(1));
        }
    }
    public List<Point> simpleConvex(Point[] p)
    {
        LinkedList<Point> convexHull = new LinkedList<>();

        for(int i = 0; i < p.length; i++)
        {
            for(int j = 0; j < p.length; j++)
            {
                if(p[i] == p[j])
                    continue;

                boolean valid = true;
                for(int k = 0; k < p.length; k++)
                {
                    if(p[k] == p[i] || p[k] == p[j])
                        continue;
                    if(isLeft(p[i], p[j], p[k]))
                    {
                        valid = false;
                    }
                }

                if(valid)
                {
                    if(convexHull.isEmpty())
                        convexHull.add(p[i]);
                    convexHull.add(p[j]);
                }
            }
        }
        return convexHull;
    }

    private boolean isLeft(Point p1, Point p2, Point p)
    {
        return (p2.get(0) - p1.get(0)) * (p.get(1) - p1.get(1)) - (p2.get(1) - p1.get(1)) * (p.get(0) - p1.get(0)) > 0;
    }

    public static double getRandom(){

        //  Generator erzeugen
        java.util.Random generator = new java.util.Random();

        //  Grenzen festlegen
        int lower = -1000;
        int upper = 1000;

        //  Zufallszahl erzeugen und zurückgeben
        double random = lower + (upper+1 - lower) * generator.nextDouble();
        return random;
    }
}