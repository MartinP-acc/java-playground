public class Kata {

    public static char findMissingLetter(char[] array)
    {
        char missing = array[0];
        for (char c : array){
            if (missing==c) missing++;
            else return missing;
        }
        return ' ';
    }
}
