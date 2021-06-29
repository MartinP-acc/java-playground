import java.util.Arrays;
import java.util.stream.Collectors;

public class Dubstep {

    public static String SongDecoder (String song){
        return Arrays.stream(song.split("WUB"))
                .filter(s -> !s.equals(""))
                .collect(Collectors.joining(" "));
    }
}
