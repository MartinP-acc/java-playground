package homework;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

class CoordinatesTest {

    Coordinates coordinates;

    @ParameterizedTest
    @ValueSource(ints = {0,20,50,75,100})
    void coordinatesShouldCreateObjectProperlyWithInts(int xy){
        //when
        coordinates = new Coordinates(xy,xy);
        //then
        assertAll(
                ()->assertEquals(xy,coordinates.getX()),
                ()->assertEquals(xy,coordinates.getY())
        );
    }

    @Test
    void coordinatesShouldThrowExceptionIfXorYGreaterThan100(){
        //when
        //then
        assertThrows(IllegalArgumentException.class,()->new Coordinates(101,7));
        assertThrows(IllegalArgumentException.class,()->new Coordinates(9,101));
    }

    @Test
    void coordinatesShouldThrowExceptionIfXorYLowerThanZero(){
        //when
        //then
        assertThrows(IllegalArgumentException.class,()->new Coordinates(-1,7));
        assertThrows(IllegalArgumentException.class,()->new Coordinates(9,-1));
    }

    @ParameterizedTest
    @ValueSource(ints = {7,19,20})
    void returnedObjectShouldBeEqualWithOriginalObject(int xy){
        //gives
        coordinates = new Coordinates(xy,xy);
        //when
        Coordinates coordinatesCopy = Coordinates.copy(coordinates,coordinates.getX(),coordinates.getY());
        //then
        assertEquals(coordinates,coordinatesCopy);
    }

}