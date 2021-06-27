import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SolutionTest {

    Solution test;

    @BeforeEach
    void setUp(){
        test = new Solution();
    }

    @Test
    void solutionTestWithExampleArray(){
        //when
        int[] a = new int[]{23171,21011,21123,21366,21013,21367};
        //given
        int result = test.solution(a);
        //then
        assertEquals(356,result);
    }

    @Test
    void solutionTestNegativeProfitShouldReturnZero(){
        //when
        int[] a = new int[]{23171,21011};
        //given
        int result = test.solution(a);
        //then
        assertEquals(0,result);
    }

    @Test
    void solutionTestMyArray(){
        //when
        int[] a = new int[]{3,10,2,11,13,29};
        //given
        int result = test.solution(a);
        //then
        assertEquals(27,result);
    }

}