import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DecodeTest {

    private static void testing_decode(String r, String exp) {
        String actual = Decode.decode(r);
        assertEquals(exp, actual);
    }

    @Test
    public void shouldReturnDecodedString() {
        testing_decode("6015ekx", "mer");
        testing_decode("1273409kuqhkoynvvknsdwljantzkpnmfgf", "uogbucwnddunktsjfanzlurnyxmx");
        testing_decode("1544749cdcizljymhdmvvypyjamowl", "mfmwhbpoudfujjozopaugcb");
        testing_decode("1122305vvkhrrcsyfkvejxjfvafzwpsdqgp", "rrsxppowmjsrclfljrajtybwviqb");

    }

    @Test
    public void shouldReturnMessageIfDecodeIsImpossible() {
        testing_decode("1877138eieaqgumigywmicwgcgg", "Impossible to decode");
    }

}