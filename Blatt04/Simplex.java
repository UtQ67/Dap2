
public abstract class Simplex {

    int d;
    Point[] points;

    public Simplex(int dim, Point... pnts){
        if (dim+1 != pnts.length)
            throw new IllegalArgumentException();
        d = dim;
        points = pnts;
    }

    public Point get(int i) {
        return points[i];
    }

    public double perimeter(){
        double sum = 0;
        for (int i = 0; i < points.length-1; i++) {
            for (int j = i+1; j < points.length; j++) {
                sum += points[i].distance(points[j]);
            }
        }
        return sum;
    }

    public abstract boolean validate();
}
