
public class Application {

    public static String INFO = "Ein valider Aufruf geschieht entweder ohne Parameter oder besitzt 6 Parameter.\n"
            + "Diese 6 Parameter müssen rationale Zahlen zwischen -1000 und 1000 sein.\n";

    public static void main (String[] args){

        //  Initialisieren des Arrays für die Koordinaten der Eckpunkte
        double[] coords = new double[6];

        //  Wenn keine Parameter angegeben wurden, sollen als Koordinaten
        //  Zufallszahlen zwischen -1000 und 1000 gewählt werden.
        if (args.length <= 0){
            for (int i = 0; i < coords.length; i++) {
                coords[i] = getRandom();
            }
        }
        else{
            try{
                //  Wenn mehr oder weniger als 6 Parameter angegeben wurden,
                //  wird das Programm mit Fehlermeldung abgebrochen.
                if (args.length != 6)
                    throw new IllegalArgumentException();

                //  Wenn genau 6 Parameter angegeben wurden, sollen diese ausgelesen werden.
                for (int i = 0; i < args.length; i++) {
                    coords[i] = Double.parseDouble(args[i]);

                    //  Wenn die ausgelesene Zahl größer als 1000 oder kleiner als -1000 ist,
                    //  wird das Programm mit Fehlermeldung abgebrochen.
                    if (Math.abs(coords[i]) > 1000)
                        throw new IllegalArgumentException();
                }
            }
            catch (Exception e){
                //  Bei fehlerhafter Angabe der Parameter wird eine Fehermeldung ausgegeben
                //  und die Ausführung des Programms abgebrochen.
                System.err.print(INFO);
                System.exit(0);
            }
        }

        //  Erstellen der Eckunkte mit den vorher bestimmten Koordinaten.
        Point p1 = new Point( coords[0], coords[1]);
        Point p2 = new Point( coords[2], coords[3]);
        Point p3 = new Point( coords[4], coords[5]);

        //  Erstellen des Dreiecks
        Triangle tri = new Triangle(p1, p2, p3);

        //  Bestimmen des Umfangs des Dreiecks
        double perim = tri.perimeter();

        //  Ausgeben der Eckpunkte und des Umfangs des resultierenden Dreiecks
        System.out.println("Eckpunkte: ");
        System.out.println("( " + p1.get(0) + " | " + p1.get(1) + " ) , "
                    + "( " + p2.get(0) + " | " + p2.get(1) + " ) , "
                    + "( " + p3.get(0) + " | " + p3.get(1) + " )");
        System.out.println("Umfang: " + perim);
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
