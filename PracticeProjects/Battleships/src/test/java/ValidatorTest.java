import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ValidatorTest {

    @Test
    void shouldReturnTrueWithMiddleCase(){
        assertTrue(Validator.isValidLine("a3"));
        assertTrue(Validator.isValidLine("b6"));
        assertTrue(Validator.isValidLine("c9"));
        assertTrue(Validator.isValidLine("d2"));
        assertTrue(Validator.isValidLine("e4"));
        assertTrue(Validator.isValidLine("g6"));
        assertTrue(Validator.isValidLine("j7"));
    }

    @Test
    void shouldReturnTrueLastLineCase(){
        assertTrue(Validator.isValidLine("a10"));
        assertTrue(Validator.isValidLine("b10"));
        assertTrue(Validator.isValidLine("c10"));
        assertTrue(Validator.isValidLine("d10"));
        assertTrue(Validator.isValidLine("e10"));
        assertTrue(Validator.isValidLine("g10"));
        assertTrue(Validator.isValidLine("j10"));
    }

    @Test
    void shouldReturnFalseIfXNotValid(){
        assertFalse(Validator.isValidLine("x3"));
        assertFalse(Validator.isValidLine("u4"));
        assertFalse(Validator.isValidLine("%6"));
        assertFalse(Validator.isValidLine("$7"));
        assertFalse(Validator.isValidLine("A9"));
        assertFalse(Validator.isValidLine("R8"));
        assertFalse(Validator.isValidLine("p2"));
    }

    @Test
    void shouldReturnFalseIfYNotValid(){
        assertFalse(Validator.isValidLine("a0"));
        assertFalse(Validator.isValidLine("b11"));
        assertFalse(Validator.isValidLine("d20"));
        assertFalse(Validator.isValidLine("e100"));
        assertFalse(Validator.isValidLine("h-1"));
        assertFalse(Validator.isValidLine("f222"));
        assertFalse(Validator.isValidLine("c40"));
    }

}