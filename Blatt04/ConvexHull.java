
import java.util.*;

class ConvexHull
{

    public static void main(String[] args) {

        ConvexHull c = new ConvexHull();
        Point[] sample = new Point[]{new Point(10d,10d), new Point(100d, 10d), new Point(10d,100d)};
        List<Point> searchedHull = c.simpleConvex(sample);

        Point[] p = new Point[1003];
        p[0] = new Point(10d,10d);
        p[1] = new Point(100d, 10d);
        p[2] =  new Point(10d,100d);

        Random rand = new Random();
        for(int i = 3; i < 1003; )
        {
            System.out.println("Searching " + i);

            // Punkte von der beispiel Hülle kopieren
            Point[] hullTest = new Point[sample.length + 1];
            for(int j = 0; j < sample.length; j++)
                hullTest[j] = sample[j];

            // Generieren des zufälligen Punktes welches in der Konvexen Hülle sein soll
            hullTest[hullTest.length - 1] = new Point(2, 11, 11);
            
            List<Point> hullP = c.simpleConvex(hullTest);

            // überprüfen on der Punkt in der Konvexen Hülle ist
            boolean isInsideSearched = true;
            for (Point var : hullP) {  
                System.out.println(var);
                if(!searchedHull.contains(var))
                    isInsideSearched = false;
            }
            if(isInsideSearched)
            {
                p[i] = hullTest[hullTest.length - 1];
                i++;
            }
        }

        for (Point pw : c.simpleConvex(p)) {
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
        
        // Wenn man nur einen Punkt hat, besteht die Konvexe Hülle nur aus dem Punkt
        if(p.length == 1)
            return convexHull;
     
        // last Point wird verwendet um von dort aus eine gerade zu ziehen, um zu pberprüfen ob die restlichen Punkte rechts der geraden liegen
        Point lastPoint = convexHull.peekLast();
        while(convexHull.size() == 1  || convexHull.peekFirst() != convexHull.peekLast())
        {
            // p[i] ist der 2. Punkt mit der die gerade konstruiert wird
            for(int i = 0; i < p.length; i++)
            {
                
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
                {
                    convexHull.addLast(p[i]);
                    lastPoint=p[i];
                    break;
                }
            }
        }

        return convexHull;
    }

    private boolean isLeft(Point p1, Point p2, Point p)
    {
        // z-Komponente vom Kreuzprodukt von P1P2 und P1P
        return (p2.get(0) - p1.get(0)) * (p.get(1) - p1.get(1)) - (p2.get(1) - p1.get(1)) * (p.get(0) - p1.get(0)) > 0;
    }
}