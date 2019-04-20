

public class Triangle extends Simplex{

    public Triangle(Point p1, Point p2, Point p3){
        super(2, p1, p2, p3);
    }

    public boolean validate() {
        boolean result = true;
        if (d+1 != 3)
            result = false;
        for (int i = 0; i < d+1; i++) {
            if (points[i].dim() != 2)
                result = false;
        }
        return result;
    }
}
