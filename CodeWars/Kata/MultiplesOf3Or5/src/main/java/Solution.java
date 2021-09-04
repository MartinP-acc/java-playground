public class Solution {

    public int solution(int number) {

        int mOfThree = 3;
        int mOfFive = 5;
        int sum = 0;

        while (mOfThree<number){
            sum+=mOfThree;
            mOfThree+=3;

            if (mOfFive<number && mOfFive%3!=0){
                sum+=mOfFive;
            }
            mOfFive+=5;
        }
        return sum;
    }
}
