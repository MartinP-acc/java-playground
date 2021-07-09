import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;

public class Solution {

    public static void main(String[] args) {

        String str = new Scanner(System.in).nextLine();
        try{
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            md.update(str.getBytes());
            StringBuffer result = new StringBuffer();
            for (byte b : md.digest()) result.append(Integer.toString((b & 0xff) + 0x100, 16).substring(1));
            System.out.println(result);
        } catch(NoSuchAlgorithmException e){
            System.out.println(e.toString());
        }
    }
}
