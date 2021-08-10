public class BinarySearch {

    public static int binarySearch(int[] list, int target){

        int low = 0;
        int high = list.length-1;
        int steps = 0;

        while (low<=high){
            steps++;

            int mid = (low + high) / 2;
            int guess = list[mid];
            if (guess == target){
                System.out.printf("target [%d] found in %d steps%n",target,steps);
                return mid;
            }
            else if (guess > target) high = mid - 1;
            else low = mid + 1;
        }
        System.out.printf("target [%d] is not on list or list is not sorted%n\tnumber of steps : %d%n",target,steps);
        return -1;
    }
}
