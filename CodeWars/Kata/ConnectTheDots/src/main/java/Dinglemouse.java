import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Dinglemouse {

    private static List<Dot> dotsList;

    private static void sortDots(){
        dotsList.sort(Comparator.comparingInt(o -> o.label));
    }

    private static void findDots(String[] paper){
        for (int r=0; r< paper.length; r++){
            for (int i=0; i<paper[r].length(); i++){
                if (paper[r].charAt(i)!=' ') dotsList.add(new Dot(r,i,paper[r].charAt(i)));
            }
        }
    }

    private static String connectDots(String[] paper){

        for (int d = 1; d<dotsList.size(); d++ ){
            int r1 = dotsList.get(d - 1).row;
            int i1 = dotsList.get(d - 1).index;
            int r2 = dotsList.get(d).row;
            int i2 = dotsList.get(d).index;

            while (r1!=r2 || i1!=i2){
                paper[r1] = paper[r1].substring(0,i1) + "*" + paper[r1].substring(i1+1);
                if (r1<r2) r1++;
                if (r1>r2) r1--;
                if (i1<i2) i1++;
                if (i1>i2) i1--;
            }
            if (d==dotsList.size()-1) paper[r2] = paper[r2].substring(0,i2) + "*" + paper[r2].substring(i2+1);
        }
        return String.join("\n",paper)+"\n";
    }

    public static String connectTheDots(String paper) {
        dotsList = new ArrayList<>();
        String[] cutPaper = paper.split("\n");
        findDots(cutPaper);
        sortDots();
        return connectDots(cutPaper);
    }
}
