
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

        // Den Punkt mit dder kleinsten x-koordinate finden. -> Teil der Konvexen Hülle
        int smallestPointIndex = 0;
        for(int i = 1; i < p.length; i++)
        {
            if(p[i].get(0)<p[smallestPointIndex].get(0))
                smallestPointIndex = i;
        }
        convexHull.add(p[smallestPointIndex]);

        // simple convex aus der Vorlesung
        for(int i = 0; i < p.length; i++)
        {
            Point lastPoint = convexHull.peekLast();
            // die betrachtetetn Punkte dürfen nicht gleich sein
            if(lastPoint == p[i])
                continue;

            // Überprüft ob alle Punkte rechts von der gerade lastPoint -> p[i] ist.
            // Wenn ja ist valid == true
            // Sonts valid == false
            boolean valid = true;
            for(int j = 0; j < p.length; j++)
            {
                if(isLeft(lastPoint, p[i], p[j]))
                    valid = false;
            }
            // liegen alle Punkte rechts ist der Punkt Teil der konvxen hülle
            if(valid)
                convexHull.add(p[i]);
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