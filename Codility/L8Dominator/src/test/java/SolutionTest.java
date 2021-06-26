import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SolutionTest {

    Solution solution;

    @BeforeEach
    void initSolution(){
        solution = new Solution();
    }

    @Test
    void solutionShouldReturn0WithExampleArray(){
        //when
        int[] a = new int[]{3,4,3,2,3,-1,3,3};
        //given
        int result = solution.solution(a);
        //then
        assertEquals(0,result);
    }

    @Test
    void solutionShouldReturnNegativeIfNoDominator(){
        //when
        int[] a = new int[]{1,2,3,4,5,6,7,8};
        //given
        int result = solution.solution(a);
        //then
        assertEquals(-1,result);
    }

    @Test
    void solutionShouldReturn3(){
        //when
        int[] a = new int[]{2,2,2,3,3,3,3};
        //given
        int result = solution.solution(a);
        //then
        assertEquals(3,result);
    }

}