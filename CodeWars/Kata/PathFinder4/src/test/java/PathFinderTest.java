import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.awt.*;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class PathFinderTest {

    private static Stream<Arguments> testParam(){
        return Stream.of(
                Arguments.of("",       new Point(  0, 0)),
                Arguments.of("RLrl",   new Point(  0, 0)),
                Arguments.of("r5L2l4", new Point(  4, 3)),
                Arguments.of("r5L2l4", new Point(  0, 0)),
                Arguments.of("10r5r0", new Point(-10, 5)),
                Arguments.of("10r5r0", new Point(  0, 0))
        );
    }

    @ParameterizedTest
    @MethodSource("testParam")
    void shouldReturnPropPoint(String path, Point point){
        assertEquals(point, PathFinder.iAmHere(path));
    }
}