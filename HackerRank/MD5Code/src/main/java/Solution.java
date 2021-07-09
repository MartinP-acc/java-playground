import javax.xml.datatype.DatatypeFactory;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;

public class Solution {

    public static void main(String[] args) {

        String str = new Scanner(System.in).nextLine();
        try {
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            md5.update(StandardCharsets.UTF_8.encode(str));
            System.out.println(String.format("%032x", new BigInteger(1, md5.digest())));
        } catch (NoSuchAlgorithmException e){
            System.out.println(e.toString());
        }
    }
}
