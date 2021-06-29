public class FindOutlier {

    static int find(int[] integers){

        int outlier = 0;
        for (int i=1; i<integers.length; i++){

            if (outlier!=0){
                if (Math.abs(integers[i]%2)!=Math.abs(integers[outlier]%2))
                    return integers[outlier];
                else
                    return integers[0];
            }

            if (Math.abs(integers[i]%2)!=Math.abs(integers[outlier]%2))
                outlier=i;
        }

        return integers[outlier];
    }
}
