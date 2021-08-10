import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SelectionSortTest {

    @Test
    void arraysShouldBeSorted(){
        int[] input1 = new int[]{4,2,-1,30,9,-4,1};
        int[] target1 = new int[]{-4,-1,1,2,4,9,30};
        assertArrayEquals(target1,SelectionSort.sort(input1));

        int[] input2 = new int[]{0,-8,-9,-22,-30,-50};
        int[] target2 = new int[]{-50,-30,-22,-9,-8,0};
        assertArrayEquals(target1,SelectionSort.sort(input1));
    }

    @Test
    void shouldReturnEmptyArray(){
        int[] input1 = new int[]{};
        int[] target1 = new int[]{};
        assertArrayEquals(target1,SelectionSort.sort(input1));
    }

    @Test
    void shouldReturnNull(){
        assertNull(SelectionSort.sort(null));
    }

}