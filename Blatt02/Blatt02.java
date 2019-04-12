
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
            size = Integer.parseInt(args[0]);
            if(args.length >= 2)
                sortType = args[1];
            if(args.length >= 3)
                arrayFill = args[2];

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
        long tEnd, tStart, msecs;

        tStart = System.currentTimeMillis();//start timer

        //auswahl des Algos zum sortieren
        if(sortType.equals("insert"))
        {
            Sortierung.insertionSort(array);
        }
        else
        {
            Sortierung.mergeSort(array);
        }
        tEnd = System.currentTimeMillis();//end timer

        msecs = tEnd-tStart;//Berechnung der benötigten Zeit
        
        //Ausgabe des Array wenn maximal 100 Einträge vorhanden sind
        if(array.length <= 100)
            print(array);

        if(Sortierung.isSorted(array))
            System.out.println("Feld sortiert!");
        else
            System.out.println("Feld NICHT sortiert!");

        System.out.println("Zeit benötigt: " + msecs + "ms");
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