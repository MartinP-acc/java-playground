public class Anagram {

    static boolean isAnagram(String a, String b) {
        // Complete the function
        if(a.length()!=b.length()) return false;

        a=a.toLowerCase();
        b=b.toLowerCase();

        for(int i=0; i<a.length(); i++){
            if (b.contains(a.substring(i,i+1))) b=b.replaceFirst(a.substring(i,i+1),".");
            else return false;
            System.out.println(a+" "+b);
        }

        return true;
    }
}
