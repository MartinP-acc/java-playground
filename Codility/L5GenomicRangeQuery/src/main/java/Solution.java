import java.util.*;

public class Solution {

    public int[] solution(String s, int[] p, int[] q ){

        int seqLenght = s.length();
        int[] sInt = new int[seqLenght];

        for (int i=0; i<seqLenght; i++){
            switch (s.charAt(i)){
                case 'A' :
                    sInt[i] =1;
                    break;
                case 'C' :
                    sInt[i] =2;
                    break;
                case 'G' :
                    sInt[i] =3;
                    break;
                default: sInt[i]=4;
            }
        }

        int[] result = new int[p.length];

        for (int i=0; i<p.length; i++){
            int[] subArray = Arrays.copyOfRange(sInt,p[i],q[i]+1);
            result[i] = Arrays.stream(subArray).min().getAsInt();
        }

        return result;
    }
}
