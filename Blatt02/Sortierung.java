public class Sortierung{

    public static void insertionSort(int[] array)
    {
        // Implementation vom Algorithmus in der Vorlesung
        for(int j = 1; j < array.length; j++)
        {
            int key = array[j];
            int i = j-1;
            while(i>=0 && array[i] > key)
            {
                array[i+1] = array[i];
                i--;
            }
            array[i+1] = key;
        }

        assert isSorted(array):"Array wurde nicht richtig sortiert.";
    }

    // Copy and paste aus der Aufgabenstellung
    public static void mergeSort(int[] array) 
    { 
        int[] tmpArray = new int[array.length];
        mergeSort(array, tmpArray, 0, array.length-1);
        assert isSorted(array):"Array wurde nicht richtig sortiert.";
    }
    // Merge Sort implementation aus dem Zusatzblatt
    public static void mergeSort(int[] array, int[] tmpArray, int left, int right)
    {
        if(left<right)
        {
            int middle = (left+right)/2;
            mergeSort(array, tmpArray, left, middle);
            mergeSort(array, tmpArray, middle + 1, right);
            merge(array, tmpArray, left, middle, right);
        }
    }
    public static void merge(int[] array, int[] tmpArray, int left, int middle, int right)
    {
        // Zähler Variablen
        int i = left;       //Linkes Array
        int j = middle + 1; //Rechtes Array
        int tmpI = 0;       //Temporäres Array

        //Sortieren vom linken und rechten Array im tmpArray, bis das linke oder reche Array leer ist
        while(i < middle + 1 && j <= right)
        {
            if(array[i] <= array[j])
            {
                tmpArray[tmpI] = array[i];
                tmpI++;
                i++;
            }else
            {
                tmpArray[tmpI] = array[j];
                tmpI++;
                j++;
            }
        }

        //Wenn das linke Array noch Elemente enthält wird alles in tmpArray kopiert
        while(i < middle + 1)
        {
            tmpArray[tmpI] = array[i];
            i++;
            tmpI++;
        }
        //Wenn das rechte Array noch Elemente enthält wird alles in tmpArray kopiert
        while(j<= right)
        {
            tmpArray[tmpI] = array[j];
            j++;
            tmpI++;
        }

        //tmpArray enthält das linke und rechte Array sortiert
        //array wird von index left bis right den daten von tmpArray aktualisiert
        for(tmpI = 0; tmpI<=(right - left); tmpI++)
        {
            array[left + tmpI] = tmpArray[tmpI];
        }
    }

    public static boolean isSorted(int[] array)
    {
        for(int i = 1; i < array.length; i++)
        {
            if(array[i-1] > array[i])
                return false;
        }
        return true;
    }
}