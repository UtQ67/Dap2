
public class Blatt02
{

    public static void main(String[] args) 
    {
        // size of array to sort
        int size = 0;
        // how the starting array is going to be filled
        String arrayFill="rand";
        String sortType = "merge";
        try {
            size = Integer.parseInt(args[0]);
            if(args.length >= 2)
                sortType = args[1];
            if(args.length >= 3)
                arrayFill = args[2];

        } catch (Exception e) {
            System.out.println("Parameter: array_size [insert|merge [auf|ab|rand]] ");
            System.exit(0);
        }

        // creating and filling array dependent on the fill param
        int[] array = new int[size];
        if(arrayFill.equals("rand"))
            fillArrayRandon(array);
        if(arrayFill.equals("ab"))
            fillArrayAb(array);
        else if(arrayFill.equals("auf"))
            fillArrayAuf(array);

        // time
        long tEnd, tStart, msecs;

        tStart = System.currentTimeMillis();
        if(sortType.equals("insert"))
        {
            Sortierung.insertionSort(array);
        }
        else
        {
            Sortierung.mergeSort(array);
        }
        tEnd = System.currentTimeMillis();

        msecs = tEnd-tStart;

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
        for(int i = 0; i < array.length; i++)
        {
            array[i] = numberGenerator.nextInt();
        }
    }
    private static void fillArrayAb(int[] array)
    {
        for(int i = 0; i < array.length; i++)
        {
            array[i] = array.length - i;
        }
    }
    private static void fillArrayAuf(int[] array)
    {
        for(int i = 0; i < array.length; i++)
        {
            array[i] = i;
        }
    }

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