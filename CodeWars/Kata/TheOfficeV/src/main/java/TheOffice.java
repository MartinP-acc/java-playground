import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class TheOffice {

    public static Object meeting(Room[] x, int need) {
        List<Integer> chairsTaken = new ArrayList<>();
        if (need<1) return "Game On";
        for (Room r : x){
            int freeChairs = Math.max(r.chairs - r.occupants.length(), 0);
            if (need-freeChairs>=0){
                need = need-freeChairs;
                chairsTaken.add(freeChairs);
            } else {
                chairsTaken.add(need);
                need=0;
            }
            if (need==0) return chairsTaken.stream().flatMapToInt(IntStream::of).toArray();
        }
        return "Not enough!";
    }
}
