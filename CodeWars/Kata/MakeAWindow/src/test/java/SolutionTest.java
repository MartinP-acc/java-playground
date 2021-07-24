import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SolutionTest {

    @Test
    public void fixedTest() {
        //n =1
        assertEquals("-----\n|.|.|\n|-+-|\n|.|.|\n-----",Solution.makeAWindow(1));
        //n =3
        assertEquals("---------\n|...|...|\n|...|...|\n|...|...|\n|---+---|\n|...|...|\n|...|...|\n|...|...|\n---------",Solution.makeAWindow(3));
    }
}
