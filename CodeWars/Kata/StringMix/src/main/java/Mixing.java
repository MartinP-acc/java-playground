import java.util.*;

public class Mixing {

    public static void countChars(String s, Map<Character,Integer> map){
        for (int i=0; i<s.length(); i++){
            char c = s.charAt(i);
            if (c>='a' && c<='z'){
                map.put(c,map.getOrDefault(c,0)+1);
            }
        }
    }

    public static String mix(String s1, String s2) {

        Map<Character,Integer> s1Chars = new HashMap<>(35);
        Map<Character,Integer> s2Chars = new HashMap<>(35);
        List<String> result = new ArrayList<>();

        countChars(s1, s1Chars);
        countChars(s2, s2Chars);

        for (Map.Entry<Character,Integer> m1 : s1Chars.entrySet()){
            String part;
            int m2Value = s2Chars.getOrDefault(m1.getKey(),0);

            if (m1.getValue() > m2Value){
                part = "1:"+m1.getKey().toString().repeat(m1.getValue());
            }else if (m1.getValue() < m2Value){
                part = "2:"+m1.getKey().toString().repeat(m2Value);
            }else {
                part = "=:"+m1.getKey().toString().repeat(m1.getValue());
            }
            s2Chars.remove(m1.getKey());
            if (part.length()>3)result.add(part);
        }

        if (s2Chars.size()>0){
            for (Map.Entry<Character,Integer> m2 : s2Chars.entrySet()){
                if (m2.getValue()>1) result.add("2:"+m2.getKey().toString().repeat(m2.getValue()));
            }
        }

        result.sort(Comparator.comparingInt(String::length).reversed().thenComparing(String::compareTo));

        return String.join("/",result);
    }
}
