import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SolutionTest {

    Solution test;

    @BeforeEach
    void setUp() {
        test = new Solution();
    }

    @Test
    void solutionTestWithExampleArray() {
        //when
        int[] a = new int[]{3,2,-6,4,0};
        //given
        int result = test.solution(a);
        //then
        assertEquals(5,result);
    }

    @Test
    void solutionTestWithMaxSliceSumAfterNegative() {
        //when
        int[] a = new int[]{3,2,-6,4,8};
        //given
        int result = test.solution(a);
        //then
        assertEquals(12,result);
    }

    @Test
    void solutionTestWithOnlyNegativeNumbers() {
        //when
        int[] a = new int[]{-4,-2,-6,-4,-8};
        //given
        int result = test.solution(a);
        //then
        assertEquals(-2,result);
    }
}