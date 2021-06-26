import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SolutionTest {

    Solution test;

    @BeforeEach
    void initSolution(){
        //when
        test = new Solution();
    }

    @Test
    void solutionShouldReturn7GivenArray(){
        //when
        int[] h = new int[]{8,8,5,7,9,8,7,4,8};
        //given
        int result = test.solution(h);
        //then
        assertEquals(7,result);
    }

    @Test
    void solutionShouldReturn1GivenOneElementArray(){
        //when
        int[] h = new int[]{2};
        //given
        int result = test.solution(h);
        //then
        assertEquals(1,result);
    }

    @Test
    void solutionShouldReturn1IfArrayConsistSameNumber(){
        //when
        int[] h = new int[]{3,3,3,3,3,3,3,3,3,3};
        //given
        int result = test.solution(h);
        //then
        assertEquals(1,result);
    }

    @Test
    void solutionShouldReturn6WithSpecificArray(){
        //when
        int[] h = new int[]{9,1,9,1,9,1,9,1,9,1};
        //given
        int result = test.solution(h);
        //then
        assertEquals(6,result);
    }

}