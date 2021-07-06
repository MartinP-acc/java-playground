import java.util.*;

public class Solution {

    static Scanner scanner;
    static int B, H;
    static boolean flag;

    static{
        try{
            scanner = new Scanner(System.in);
            B = scanner.nextInt();
            H = scanner.nextInt();
            scanner.close();
            if (B>0 && H>0) flag=true;
            else throw new Exception();
        }catch(Exception e){
            System.out.println(e+": Breadth and height must be positive");
        }
    }

    public static void main(String[] args){
        if(flag){
            int area=B*H;
            System.out.print(area);
        }

    }//end of main

}//end of class


