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
    void solutionShouldReturn2WithGivenArrays(){
        //when
        int[] a = new int[]{4,3,2,1,5};
        int[] b = new int[]{0,1,0,0,0};
        //given
        int result = solution.solution(a,b);
        //then
        assertEquals(2,result);
    }

    @Test
    void solutionShouldReturn1WithOneFishUpstream(){
        //when
        int[] a = new int[]{4};
        int[] b = new int[]{0};
        //given
        int result = solution.solution(a,b);
        //then
        assertEquals(1,result);
    }

    @Test
    void solutionShouldReturn1WithOneFishDownstream(){
        //when
        int[] a = new int[]{4};
        int[] b = new int[]{1};
        //given
        int result = solution.solution(a,b);
        //then
        assertEquals(1,result);
    }

    @Test
    void solutionShouldReturn1WithTwoFishOppositeStreamAndUpstreamWin(){
        //when
        int[] a = new int[]{3,5};
        int[] b = new int[]{1,0};
        //given
        int result = solution.solution(a,b);
        //then
        assertEquals(1,result);
    }

    @Test
    void solutionShouldReturn1WithTwoFishOppositeStreamAndDownstreamWin(){
        //when
        int[] a = new int[]{5,3};
        int[] b = new int[]{1,0};
        //given
        int result = solution.solution(a,b);
        //then
        assertEquals(1,result);
    }

    @Test
    void solutionShouldReturn4WhereOneFishDefendOther(){
        //when
        int[] a = new int[]{1,2,3,5,4};
        int[] b = new int[]{1,1,1,1,0};
        //given
        int result = solution.solution(a,b);
        //then
        assertEquals(4,result);
    }

    @Test
    void solutionShouldReturn2WithTwoFishOppositeStream(){
        //when
        int[] a = new int[]{3,5};
        int[] b = new int[]{0,1};
        //given
        int result = solution.solution(a,b);
        //then
        assertEquals(2,result);
    }
}