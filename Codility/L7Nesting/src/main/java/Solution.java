public class Solution {

    public int solution(String s){

        int length = s.length();

        if (length==0) return 1;
        if (length%2>0) return 0;

        int counter =0;

        for (int i=0; i<length; i++){
            if (s.charAt(i)=='('){
                counter++;
            }else{
                counter--;
            }

            if (counter<0) return 0;
        }

        return counter==0 ? 1 : 0;
    }
}
