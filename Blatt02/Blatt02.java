import java.security.InvalidParameterException;

public class Blatt02
{

    public static void main(String[] args) 
    {
        //Größe des Arrays das sortiert werden soll
        int size = 0;
        //Füllart des Arrays  (rand, auf, ab)
        String arrayFill="rand";
        //Der genutzte Algo zum sortieren (merge, insert)
        String sortType = "merge";

        //Auslesen von Parametern
        try {
            // Auslesen von pflicht Parameter
            size = Integer.parseInt(args[0]);
            // Auslesen vom optionalen Parameter 1 (welcher Sortier-Algorithmus verwendet werden soll)
            if(args.length >= 2)
            {
                sortType = args[1];
                if(!(sortType.equals("merge") || sortType.equals("insert")))
                    throw new InvalidParameterException();
            }
            // AUslesen vom optionalen Parameter 2 (wie das Array gemischt werden soll)
            if(args.length >= 3)
            {
                arrayFill = args[2];
                if(!(arrayFill.equals("rand")||arrayFill.equals("auf")||arrayFill.equals("ab")))
                    throw new InvalidParameterException();
            }
            if(args.length > 3)
                throw new InvalidParameterException();

        } catch (Exception e) {
            // Parameter wurden falsch eingegeben.
            System.out.println("Parameter: array_size [insert|merge [auf|ab|rand]] ");
            System.exit(0);
        }

        //Array initialisieren
        int[] array = new int[size];
        if(arrayFill.equals("rand"))
            fillArrayRandon(array);
        if(arrayFill.equals("ab"))
            fillArrayAb(array);
        else if(arrayFill.equals("auf"))
            fillArrayAuf(array);

        //Timer variablen initialisieren
        long tEnd = 0, tStart = 0, msecs;

        //auswahl des Algos zum sortieren
        if(sortType.equals("insert"))
        {
            tStart = System.currentTimeMillis();//start timer
            Sortierung.insertionSort(array);
            tEnd = System.currentTimeMillis();//end timer
        }
        else
        {
            tStart = System.currentTimeMillis();//start timer
            Sortierung.mergeSort(array);
            tEnd = System.currentTimeMillis();//end timer
        }

        msecs = tEnd-tStart;//Berechnung der benötigten Zeit
        
        //Ausgabe des Array wenn maximal 100 Einträge vorhanden sind
        if(array.length <= 100)
            print(array);

        if(Sortierung.isSorted(array))
            System.out.println("Feld sortiert!");
        else
            System.out.println("Feld NICHT sortiert!");

        System.out.println("Zeit ben\u00f6tigt: " + msecs + "ms");
    }

    private static void fillArrayRandon(int[] array)
    {
        java.util.Random numberGenerator = new java.util.Random();
        //Zufälliges auffüllen des Array
        for(int i = 0; i < array.length; i++)
        {
            array[i] = numberGenerator.nextInt();
        }
    }
    
    //Das Array wird absteigend mit Zahlen gefüllt
    private static void fillArrayAb(int[] array)
    {
        for(int i = 0; i < array.length; i++)
        {
            array[i] = array.length - i;
        }
    }
    //Das Array wird aufsteigend mit Zahlen gefüllt
    private static void fillArrayAuf(int[] array)
    {
        for(int i = 0; i < array.length; i++)
        {
            array[i] = i;
        }
    }

    //Ausgabe des Array
    public static void print(int[] array){
        System.out.print("Sorted Array: [");
        for (int i = 0; i < array.length; i++) {
            if(i != 0)
                System.out.print(",");
            System.out.print(array[i] );
        }
        System.out.println("]");
    }

}