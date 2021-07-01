import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ScrambliesTest {

    @Test
    void TrueTest(){
        assertTrue(Scramblies.scramble("rkqodlw","world"));
        assertTrue(Scramblies.scramble("cedewaraaossoqqyt","codewars"));
        assertTrue(Scramblies.scramble("scriptingjava","javascript"));
        assertTrue(Scramblies.scramble("scriptsjava","javascripts"));
        assertTrue(Scramblies.scramble("aabbcamaomsccdd","commas"));
        assertTrue(Scramblies.scramble("sammoc","commas"));
    }

    @Test
    void FalseTest(){
        assertFalse(Scramblies.scramble("katas","steak"));
        assertFalse(Scramblies.scramble("javscripts","javascript"));
        assertFalse(Scramblies.scramble("scriptjavx","javascript"));
    }

}