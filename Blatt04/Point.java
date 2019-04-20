

public class Point {

    private int d;
    private double[] coords;

    public Point(int dim, double... values) {
        
        if (dim != values.length)
            throw new IllegalArgumentException("Dimension und Anzahl der Koordinaten stimmen nicht überein!");
        d = dim;
        coords = values;
    }

    public Point(double... values) {
    
        this(values.length, values);

    }

    public int dim() {
        return d;
    }

    public double get(int i) {
        return coords[i];
    }

    public double distance(Point p2){
        if (d != p2.d)
            throw new IllegalArgumentException();
        double[] squares = new double[d];
        for (int i = 0; i < d; i++) {
            squares[i] = Math.pow((coords[i]-p2.coords[i]), 2);
        }
        double result = 0;
        for (double sqr : squares) {
            result += sqr;
        }
        result = Math.sqrt(result);
        return result;
    }
}
