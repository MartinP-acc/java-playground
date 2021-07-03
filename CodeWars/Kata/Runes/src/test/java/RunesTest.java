import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RunesTest {

    @Test
    public void testSample() {
        assertEquals( 2 , Runes.solveExpression("1+1=?") );
        assertEquals( 6 , Runes.solveExpression("123*45?=5?088") );
        assertEquals( 0 , Runes.solveExpression("-5?*-1=5?") );
        assertEquals( -1 , Runes.solveExpression("19--45=5?") );
        assertEquals( 5 , Runes.solveExpression("??*??=302?") );
        assertEquals( 2 , Runes.solveExpression("?*11=??") );
        assertEquals( 2 , Runes.solveExpression("??*1=??") );
        assertEquals( -1 , Runes.solveExpression("??+??=??") );
    }
}