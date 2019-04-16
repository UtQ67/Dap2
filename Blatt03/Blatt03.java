import java.security.InvalidParameterException;

public class Blatt03
{
    public static final String paramsDescription = "Add description";
    public static double tolerance = 0.1;

    public static void main(String[] args) {
        double wantedTime = 0;
        try{
            wantedTime = Double.parseDouble(args[0]);
            if(wantedTime < 0)
                throw new InvalidParameterException();
        }catch(Exception e)
        {
            System.out.println(paramsDescription);
            System.exit(1);
        }
       

        double secs = -1;

        // n=500, da in der schleife n verdoppelt wird => start ist n = 1000
        int n = 500;
        
        while(!(wantedTime < secs))
        {
            // n verdoppeln
            n *=2;

            secs = calculateTime(n);

            

        }

        System.out.println("Binäre suche wird gestartet");

        // binäre suche
        int nLeft = n/2;   // n/2 ist die höchste bekannte anzahl an elementen die schneller sortiert wird als gesucht ist
        int nRight = n;
        
        while(!(wantedTime - tolerance < secs && wantedTime + tolerance > secs))
        {
            int nMiddle = (nRight + nLeft)/2;

            secs = calculateTime(nMiddle);

            if(secs < wantedTime)
            {
                nLeft = nMiddle;
            }else
            {
                nRight = nMiddle;
            }

            n = nMiddle;
        }

        System.out.println("Für " + n + " wird ungefähr " + wantedTime + " Sekunden benötigt");
    }

    public static double calculateTime(int n)
    {
        long tStart, tEnd;
        // Array initialisieren
        int[] array = new int[n];
        fillArrayDesc(array);

        System.gc();

        // Zeit messen
        tStart = System.currentTimeMillis();
        bubbleSort(array);
        tEnd = System.currentTimeMillis();
        
        double secs = (double)(tEnd - tStart) / (double) 1000;
        System.out.println(n + " Elementen\t " + secs + " Sekunden");
        // gemessene Zeit aktualisieren
        return secs;
    }

    // Implementierung des BubbleSort-Peudocode
    // Messung für 50000 Elemente: 1.8s
    public static void bubbleSort(int[] array)
    {
        int n = array.length;
        for(int i = 0; i < n; i++)
        {
            for(int j = n-1; j > i+1; j--)
            {
                if(array[j-1] > array[j])
                {
                    int tmp = array[j];
                    array[j] = array[j-1];
                    array[j-1] = tmp;
                }
            }
        }
    }

    public static void fillArrayDesc(int[] array)
    {
        for(int i = 0; i<array.length; i++)
        {
            array[i] = array.length - i;
        }
    }
}