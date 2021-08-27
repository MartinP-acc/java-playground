import java.util.Arrays;

public class Heaps {

    public static void start(int[] array){
        int k = array.length;
        permutation(k,array);
    }

    private static void swap(int[] array,int a,int b){
        int temp = array[a];
        array[a] = array[b];
        array[b] = temp;
    }

    private static void permutation(int k,int[] array){
        if (k==1){
            Arrays.stream(array).forEach(i -> System.out.print(i+" "));
            System.out.println();
        }else {
            for (int i=0; i<k; i++){
                permutation(k -1, array);
                if (k%2==0) {
                    swap(array, i,k-1);
                } else {
                    swap(array, 0, k-1);
                }
            }
        }
    }
}
