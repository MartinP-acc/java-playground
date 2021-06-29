import java.util.List;
public class BinaryArrayToNumber {

    public static int ConvertBinaryArrayToInt(List<Integer> binary) {
        int number=0;
        for (int i = 0; i<binary.size(); i++){
            number+=binary.get(i)*Math.pow(2,binary.size()-1-i);
        }
        return number;
    }
}
