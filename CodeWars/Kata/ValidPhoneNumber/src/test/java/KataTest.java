import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class KataTest {

    @Test
    public void tests() {
        assertTrue(Kata.validPhoneNumber("(123) 456-7890"));
        assertFalse(Kata.validPhoneNumber("(1111)555 2345"));
        assertFalse(Kata.validPhoneNumber("(098) 123 4567"));
    }
}