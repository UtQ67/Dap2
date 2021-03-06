import java.security.InvalidParameterException;

public class Aufgabe01
{
    public static final String INFO_STRING ="Es wird 1 Parameter benötigt \n"+
                                            "\t1.Parameter: Laenge des zu sortierenden Arrays.";
    public static void main(String[] args) {
        int n = 0;  //größe des zu sortiernden arrays
        try{
            // Man brauucht genau 1 Parameter
            if(args.length != 1)
                throw new InvalidParameterException();
            // Parameter einlesen
            n = Integer.parseInt(args[0]);
            // Zahl darf nicht nativ sein
            if(n<0)
                throw new InvalidParameterException();
        }catch(Exception e)
        {
            System.out.println(INFO_STRING);
            System.exit(1);
        }

        // array initialisieren und füllen
        int[] arr = new int[n];
        fillArrayRandom(arr);  

        // Zeit messen und sortieren
        long tEnd = 0, tStart = 0, msecs;
        tStart = System.currentTimeMillis();
        Quicksort.quicksort(arr);
        tEnd = System.currentTimeMillis();

        msecs = tEnd - tStart;

        System.out.println("Zeit benoetigt: " + msecs + "ms");
        
    }

    private static void fillArrayRandom(int[] array)
    {
        java.util.Random numberGenerator = new java.util.Random();
        //Zufälliges auffüllen des Array
        for(int i = 0; i < array.length; i++)
        {
            array[i] = numberGenerator.nextInt(2*array.length);
        }
    }
}