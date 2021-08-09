public class Preloaded {

    public static final String ALPHABET = "ABCDEFGHIJKLMNOPQRSTUVWXYZ ?!@#&()|<>.:=-+*/0123456789";

    public static String[] prettyPrint(String[] a) {
        System.out.print("\033[0;33m");
        for (String line : a){
            for (int i=0; i<line.length(); i++){
                System.out.print(line.charAt(i)+" ");
            }
            System.out.println();
        }
        System.out.print("\033[0m");
        return a;
    }
}
