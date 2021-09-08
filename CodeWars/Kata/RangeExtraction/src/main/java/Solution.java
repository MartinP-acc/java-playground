public class Solution {

    public static String rangeExtraction(int[] arr) {

        StringBuilder result = new StringBuilder();
        String range = "";
        int prev = arr[0];
        result.append(prev);

        for (int i=1 ; i< arr.length; i++){

            if (arr[i-1]+1!=arr[i]){
                result.append(range).append(",").append(arr[i]);
                range = "";
                prev=arr[i];
            }
            else {
                if (prev+1==arr[i]) range = ","+arr[i];
                else range = "-"+arr[i];
            }
        }
        return result.append(range).toString();
    }
}
