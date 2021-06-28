import java.util.Locale;

public class JadenCase {

    public String toJadenCase(String phrase){

        if (phrase==null || phrase.equals("")) return null;

        String phrase2="";
        String[] array = phrase.split(" ");
        for (int i=0; i<array.length; i++){
            String f = array[i].toUpperCase();
            array[i] = f.charAt(0)+array[i].substring(1);
            phrase2 += i==0? array[i] : " "+array[i];
        }

    return phrase2;
    }
}
