import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class QuicksortTest {

    @Test
    void shouldSortIntArray(){
        assertArrayEquals(new int[]{1,2,3,4,5,6,7,8,9},Quicksort.sort(new int[]{8,3,6,2,4,9,1,7,5}));
    }

    @Test
    void shouldReturnEmptyArray(){
        assertArrayEquals(new int[]{},Quicksort.sort(new int[]{}));
    }

    @Test
    void shouldReturnArrayWithOneElement(){
        assertArrayEquals(new int[]{3},Quicksort.sort(new int[]{3}));
    }

}