import java.util.Arrays;
import java.util.stream.Collectors;

public class MorseCodeDecoder {

    public static String decode(String morseCode) {

       return Arrays.stream(morseCode.split(" "))
               .map(s -> s.equals("")?" ":"A")  //"A" there should be MorseCode.get(s)
               .collect(Collectors.joining())
               .replace("  "," ")
               .stripLeading();
    }
}
