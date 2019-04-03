public class Sortierung{
    public static void insertionSort(int[] array)
    {
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

        assert isSorted(array);
    }


    public static void mergeSort(int[] array) 
    { 
        int[] tmpArray = new int[array.length];
        mergeSort(array, tmpArray, 0, array.length-1);
        assert isSorted(array);
    }
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
        // init counter variables. i > left array. j > right array. tmpI > temp Array
        int i = left;
        int j = middle + 1;
        int tmpI = 0;

        // merge left and right array in tmpArray
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

        while(i < middle + 1)
        {
            tmpArray[tmpI] = array[i];
            i++;
            tmpI++;
        }
        while(j<= right)
        {
            tmpArray[tmpI] = array[j];
            j++;
            tmpI++;
        }

        // copy tmpArray data to array
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