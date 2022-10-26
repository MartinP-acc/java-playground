import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CoordinateTest {

    @Test
    void shouldReturnValidCoordinateObject(){
        assertEquals(new Coordinate('a',5),Coordinate.parseCoordinate("a5"));
        assertEquals(new Coordinate('b',10),Coordinate.parseCoordinate("b10"));
        assertEquals(new Coordinate('h',7),Coordinate.parseCoordinate("h7"));
        assertEquals(new Coordinate('j',1),Coordinate.parseCoordinate("j1"));
    }

    @Test
    void shouldReturnNeighborCoords(){
        assertEquals(new Coordinate('b',1),Coordinate.above(new Coordinate('b',2)));
        assertEquals(new Coordinate('b',3),Coordinate.below(new Coordinate('b',2)));
        assertEquals(new Coordinate('a',2),Coordinate.leftSide(new Coordinate('b',2)));
        assertEquals(new Coordinate('c',2),Coordinate.rightSide(new Coordinate('b',2)));
    }

    @Test
    void shouldThrowExceptionWhenStringIsEmpty(){
        assertThrows(IllegalArgumentException.class,() -> Coordinate.parseCoordinate(""));
    }

    @Test
    void shouldThrowExceptionWhenStringIsInvalid(){
        assertThrows(IllegalArgumentException.class,() -> Coordinate.parseCoordinate("6f"));
        assertThrows(IllegalArgumentException.class,() -> Coordinate.parseCoordinate("d#4"));
        assertThrows(IllegalArgumentException.class,() -> Coordinate.parseCoordinate("jkk"));
    }

}