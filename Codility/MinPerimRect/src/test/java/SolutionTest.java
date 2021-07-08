import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SolutionTest {

    @Test
    void solutionTest(){
        assertEquals(8,new Solution().solution(4));
        assertEquals(22,new Solution().solution(30));
        assertEquals(170,new Solution().solution(166));
    }

}