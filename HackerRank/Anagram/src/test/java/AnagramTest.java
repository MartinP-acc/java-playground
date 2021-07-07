import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AnagramTest {

    @Test
    void shouldReturnFalseIfSecondStringIsNotAnagramOfFirstString(){
        assertFalse(Anagram.isAnagram("anagramm","marganaa"));
    }

    @Test
    void shouldReturnTrueIfSecondStringIsAnagramOfFirstString(){
        assertTrue(Anagram.isAnagram("anagram","margana"));
    }

}