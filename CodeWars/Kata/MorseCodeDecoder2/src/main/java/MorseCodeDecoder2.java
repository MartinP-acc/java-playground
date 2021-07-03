import java.util.Arrays;
import java.util.stream.Collectors;

public class MorseCodeDecoder2 {

    public static String decodeBits(String bits) {

        bits = bits.replaceAll("111111","-").replaceAll("11",".")
                .replaceAll("000000", " ").replaceAll("00","")
                .replaceAll("  "," | ");

        return Arrays.stream(bits.split(" ")).map(s -> s.equals("|")?" ":decodeMorse(s)).collect(Collectors.joining())
                .stripLeading();
    }

    public static String decodeMorse(String morseCode) {
        return MorseCode.get(morseCode);
    }
}
