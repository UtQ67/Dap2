public class Quicksort
{

    public static void quicksort(int[] array)
    {
        quicksort(array, 0, array.length-1);
        assert isSorted(array) : "Das array wurde falsch sortiert";
    }

    public static void quicksort(int[] array, int l, int r)
    {
        if(l<r)
        {
            int i = l;
            int j = r;
            int pivot = array[(l+r)/2];
            while( i<= j)
            {
                while(array[i] < pivot)
                    i++;
                while(array[j] > pivot)
                    j--;
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