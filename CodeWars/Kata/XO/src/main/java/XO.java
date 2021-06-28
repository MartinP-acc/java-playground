import java.util.Locale;

public class XO {

    public static boolean getXO (String str) {
        int x=0;
        int o=0;
        for (int i=0; i<str.length() ;i++){
            char c=str.charAt(i);
            if (c=='x' || c=='X') x++;
            if (c=='o' || c=='O') o++;
        }
        return x==o;
    }
}
