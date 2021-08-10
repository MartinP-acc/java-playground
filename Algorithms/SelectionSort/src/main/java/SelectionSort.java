public class SelectionSort {

    public static int[] sort(int[] array){

        if (array==null) return null;

        for (int i=0; i< array.length; i++){
            int min = i;

            for (int j=i; j< array.length; j++){
                if (array[min]>array[j]) min=j;
            }
            if (min!=i){
                int swap = array[i];
                array[i] = array[min];
                array[min] = swap;
            }
        }
        return array;
    }
}
