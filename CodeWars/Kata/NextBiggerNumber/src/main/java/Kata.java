import java.util.Arrays;
import java.util.stream.Collectors;

public class Kata {

    public static long nextBiggerNumber(long n)
    {
        long[] lng = Arrays.stream(Long.toString(n).split("")).mapToLong(Long::parseLong).toArray();
        int lastInd = lng.length-1;
        for (int i=lastInd-1; i>=0; i--){
            if (lng[i]<lng[i+1]){
                int minInd=i+1;
                for (int k=minInd; k<=lastInd; k++){
                    if (lng[k]>lng[i] && lng[k]<lng[minInd]){
                        minInd=k;
                    }
                }
                long swap = lng[i];
                lng[i] = lng[minInd];
                lng[minInd] = swap;

                Arrays.sort(lng,i+1,lastInd+1);
                return Long.parseLong(Arrays.stream(lng).mapToObj(String::valueOf).collect(Collectors.joining()));
            }
        }
        return -1;
    }
}
