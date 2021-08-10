import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BinarySearchTest {

    @Test
    void shouldReturnIndexOfTarget(){
        int[] list = new int[]{0,3,4,9,12,15,17,19,25,40};
        int[] targets = new int[]{0,9,15,25,40};
        assertEquals(0, BinarySearch.binarySearch(list,targets[0]));
        assertEquals(3, BinarySearch.binarySearch(list,targets[1]));
        assertEquals(5, BinarySearch.binarySearch(list,targets[2]));
        assertEquals(8, BinarySearch.binarySearch(list,targets[3]));
        assertEquals(9, BinarySearch.binarySearch(list,targets[4]));
    }

    @Test
    void shouldReturnNegativeIfTargetIsNotOnList(){
        int[] list = new int[]{0,3,4,9,12,15,17,19,25,40};
        int[] targets = new int[]{7,39};
        assertEquals(-1, BinarySearch.binarySearch(list,targets[0]));
        assertEquals(-1, BinarySearch.binarySearch(list,targets[1]));
    }

}