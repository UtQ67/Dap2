public class Quicksort
{

    public static void quicksort(int[] array)
    {
        quicksort(array, 0, array.length-1);
        assert isSorted(array) : "Das array wurde falsch sortiert";
    }

    //Implementierung vom Pseudo-Code
    public static void quicksort(int[] array, int l, int r)
    {
        if(l<r)
        {
            int i = l;
            int j = r;
            int pivot = array[(l+r)/2];
            // Invariante: array[j+1...array.length] > pivot
            //             array[0.. i-1 ] < pivot
            while( i<= j)
            {
                // invariante überprüfen
                assert invarianteIsRightFromJBiggerPivot(j+1, pivot, array): "Invariante ist nicht erfüllt";
                assert invarianteIsLeftFromISmallerPivot(i-1, pivot, array): "Invariante ist nicht erfüllt";
                
                // Invariante array[0.. i-1 ] < pivot
                while(array[i] < pivot)
                {
                    // Invariante überprüfen
                    assert invarianteIsLeftFromISmallerPivot(i-1, pivot, array): "Invariante ist nicht erfüllt";
                    i++;
                }
                // Invariante array[j+1...array.length] > pivot
                while(array[j] > pivot)
                {
                    // Invariate überprüfen
                    assert invarianteIsRightFromJBiggerPivot(j+1, pivot, array): "Invariante ist nicht erfüllt";
                    j--;
                }

                // array[i] < pivot und array[j] > pivot   =>    array[i] und array[j] tauschen
                if(i <= j)
                {
                    int tmp = array[i];
                    array[i] = array[j];
                    array[j] = tmp;

                    i++;
                    j--;
                }
            }

            quicksort(array,l,j);
            quicksort(array,i, r);
        }
    }

    // Überprüfen von invariante dass array[j..n] < pivot
    private static boolean invarianteIsRightFromJBiggerPivot(int j, int pivot, int[] array)
    {
        for(int i = j ; i < array.length; i++)
        {
            if(array[i] < pivot)
                return false;
        }
        return true;
    }
    // überprüfe von invariante array[0..i] > pivot
    private static boolean invarianteIsLeftFromISmallerPivot(int i, int pivot, int[] array)
    {
        for(int j = i; j >= 0; j--)
        {
            if(array[j] > pivot)
                return false;
        }
        return true;
    }

    // Überprüft ob das array sortiert ist
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