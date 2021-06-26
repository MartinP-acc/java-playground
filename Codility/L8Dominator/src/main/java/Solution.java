public class Solution {

    public int solution(int[] a){

        for (int i=0; i<a.length; i++){
            int actual = a[i];
            int counter = 0;

            for (int l : a) {
                if (actual == l) counter++;
            }
            if (counter>(a.length/2)) return i;
        }
        return -1;
    }
}
