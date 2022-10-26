public class Kata {

    public static boolean validSpacing(String s) {
        if (s.isEmpty()) return true;
        return s.matches("\\S+\\s?\\S+");
    }
}
