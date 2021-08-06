import java.util.ArrayList;
import java.util.List;

public class ProdFib {

    public static long[] productFib(long prod) {

        List<Long> fibSeq = new ArrayList<>();
        fibSeq.add(0L);
        fibSeq.add(1L);
        int index = 1;
        while(fibSeq.get(index)*fibSeq.get(index-1)<prod){
            fibSeq.add(fibSeq.get(index)+fibSeq.get(index-1));
            index++;
        }
        return new long[]{fibSeq.get(index-1), fibSeq.get(index), fibSeq.get(index)*fibSeq.get(index-1)==prod?1L:0L};
    }
}
