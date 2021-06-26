import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

class SolutionTest {

    Solution test;

    @BeforeEach
    void initSolution(){
        //when
        test = new Solution();
    }

    @ParameterizedTest
    @ValueSource(strings = {"(()(())())","()","((()))","()()()()()()()()()()()()()()()()()"})
    void ifStringProperlyNestedSolutionShouldReturn1(String s){
        //given
        System.out.println("tested string : "+s);
        int result = test.solution(s);
        //then
        assertEquals(1,result);
    }

    @ParameterizedTest
    @ValueSource(strings = {""})
    void ifStringIsEmptyShouldReturn1(String s){
        //given
        System.out.println("tested empty string");
        int result = test.solution(s);
        //then
        assertEquals(1,result);
    }

    @ParameterizedTest
    @ValueSource(strings = {"(",")(","(())()(()(","(())(()()(())"})
    void ifStringIsNotProperlyNestedShouldReturn1(String s){
        //given
        System.out.println("tested string : "+s );
        int result = test.solution(s);
        //then
        assertEquals(0,result);
    }

}