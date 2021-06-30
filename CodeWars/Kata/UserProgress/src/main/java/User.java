import java.util.Arrays;

public class User {

    private final int[] availableRanks = new int[]{-8,-7,-6,-5,-4,-3,-2,-1,1,2,3,4,5,6,7,8};
    private int rankIndex;
    public int rank;
    public int progress;

    public User() {
        rankIndex =0;
        rank = availableRanks[rankIndex];
        progress =0;
    }

    private void incRank(){
        while (progress>=100){
            progress-=100;
            if (rankIndex<15) rankIndex++;
            else progress=0;
        }
        if (rankIndex==15) progress=0;
        rank = availableRanks[rankIndex];
    }

    public void incProgress(int rank){
        int activityIndex = Arrays.binarySearch(availableRanks,rank);
        if (activityIndex<0) throw new IllegalArgumentException("No such rank");

        int difference = activityIndex - rankIndex;
        if (difference>=-1) {
            switch (difference){
                case -1 : progress+=1;
                    break;
                case 0 : progress+=3;
                    break;
                default : progress+=difference * difference * 10;
                    break;
            };
        }
        incRank();
    }
}
