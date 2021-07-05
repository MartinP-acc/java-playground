import java.util.Arrays;
import java.util.Collections;
import java.util.Objects;
import java.util.stream.Collectors;

public class Kata {

    public static long nextSmaller(long n)
    {
        Long[] lng = Arrays.stream(Long.toString(n).split("")).mapToLong(Long::parseLong).boxed().toArray(Long[]::new);
        int lastInd = lng.length-1;
        for (int i=lastInd-1; i>=0; i--){
            if (lng[i]>lng[i+1]){
                int minInd=i+1;
                for (int k=minInd; k<=lastInd; k++){
                    if (lng[k]<lng[i] && lng[k]>lng[minInd]){
                        if (lng[k]==0 && i==0) continue;
                        minInd=k;
                    }
                }
                if (i==0){
                    if (lng[minInd]==0) return -1;
                }
                long swap = lng[i];
                lng[i] = lng[minInd];
                lng[minInd] = swap;

                Arrays.sort(lng,i+1,lastInd+1, Collections.reverseOrder());
                return Long.parseLong(Arrays.stream(lng).map(Objects::toString).collect(Collectors.joining()));
            }
        }
        return -1;
    }
}
