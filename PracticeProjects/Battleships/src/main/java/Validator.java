public class Validator {

    public static boolean isValidLine(String line){
        return line.matches("([a-j][1-9])|[a-j]10");
    }

    public static boolean isValidCoordinate(Coordinate coord){
        return coord.x>='a' && coord.x<='j' && coord.y>0 && coord.y<11;
    }
}
