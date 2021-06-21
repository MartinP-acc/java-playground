import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SolutionTest {

    Solution solution = new Solution();
    String s;

    @Test
    void ifStringCorrectShouldReturn1(){

        //when
        s = "{[()()]}";
        //then
        assertEquals(solution.solution(s),1);
    }

    @Test
    void ifStringIsEmptyShouldReturn1(){

        //when
        s = "";
        //then
        assertEquals(solution.solution(s),1);
    }

    @Test
    void ifOpenBracketWithoutPairShouldReturn0(){

        //when
        s = ")(";
        //then
        assertEquals(solution.solution(s),0);
    }

    @Test
    void ifStringContainsWrongLengthReturn0(){

        //when
        s = "{[(})]}";
        //then
        assertEquals(solution.solution(s),0);
    }

}