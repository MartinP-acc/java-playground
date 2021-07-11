import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FinderTest {

    @Test
    public void sampleTests() {

        String a = ".W.\n"+
                ".W.\n"+
                "...",

                b = ".W.\n"+
                        ".W.\n"+
                        "W..",

                c = "......\n"+
                        "......\n"+
                        "......\n"+
                        "......\n"+
                        "......\n"+
                        "......",

                d = "......\n"+
                        "......\n"+
                        "......\n"+
                        "......\n"+
                        ".....W\n"+
                        "....W.",

                e =     "...WW..W.\n" +
                        ".W.W....W\n" +
                        ".........\n" +
                        "....WW...\n" +
                        "W.W......\n" +
                        "WW..WW.W.\n" +
                        "WW.W...W.\n" +
                        "........W\n" +
                        ".W...W...",

                f =     ".WW...W..\n" +
                        "......WW.\n" +
                        "........W\n" +
                        ".WW..W...\n" +
                        "W....W...\n" +
                        "W....W..W\n" +
                        ".W.....W.\n" +
                        "W...W.WW.\n" +
                        "....W.W..";

        //assertEquals( 4,  Finder.pathFinder(a));
        //assertEquals( -1, Finder.pathFinder(b));
        //assertEquals( 10,  Finder.pathFinder(c));
        //assertEquals( -1, Finder.pathFinder(d));
        assertEquals(16,Finder.pathFinder(e));
        //assertEquals(-1,Finder.pathFinder(f));
    }

}