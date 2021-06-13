public class Solution {

    public int solution(int[] a){

        int east = 0; // cars traveling east, n=0
        int west = 0; // cars traveling west, n=1
        int carPairs = 0;

        for (int n : a){
            if (n==0) east++;
            else west++;
        }

        for (int i : a) {

            if (carPairs > 1000000000) return -1;

            if (east == 0 || west == 0) return carPairs;

            if (i == 0) {
                east--;
                carPairs += west;
            } else {
                west--;
            }
        }
        return carPairs;
    }
}
