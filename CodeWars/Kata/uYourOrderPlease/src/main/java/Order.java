public class Order {
    public static String order(String words) {
        if (words.isEmpty()) return words;
        String[] before = words.split(" ");
        String[] after = new String[before.length];
        for (String w : before) after[Integer.parseInt(w.replaceAll("\\D*(\\d)\\D*", "$1"))-1] = w;
        return String.join(" ",after);
    }
}