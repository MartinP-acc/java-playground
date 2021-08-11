public class Quicksort {

    public static int[] sort(int[] array){
        if (array.length!=0) qs(array,0,array.length-1);
        return array;
    }

    private static void qs(int[] array, int left, int right){

        int i = left;
        int j = right;

        int pivot = array[(left+right)/2];

        do {
            while (array[i]<pivot && i<right) i++;
            while (array[j]>pivot && j>left) j--;

            if (i<=j){
                int swap = array[i];
                array[i] = array[j];
                array[j] = swap;
                i++;
                j--;
            }
        }while (i <= j);

        if (left<j) qs(array,left,j);
        if (right>i) qs(array,i,right);
    }

}
