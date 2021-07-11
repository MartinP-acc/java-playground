import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SolutionTest {

    @Test
    void countFactors(){
        assertEquals(4,new Solution().solution(485));
        assertEquals(8,new Solution().solution(24));
        assertEquals(12,new Solution().solution(72));
        assertEquals(24,new Solution().solution(28756));
    }

}