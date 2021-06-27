import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SolutionTest {

    Solution test;

    @BeforeEach
    void initSolution(){
        test = new Solution();
    }

    @Test
    void simpleTest(){
        //when
        int[] a = new int[]{4,3,4,4,4,2};
        //given
        int result = test.solution(a);
        //then
        assertEquals(2,result);
    }

    @Test
    void simpleTest2(){
        //when
        int[] a = new int[]{4, 4, 2, 5, 3, 4, 4, 4};
        //given
        int result = test.solution(a);
        //then
        assertEquals(3,result);
    }
}