import java.util.ArrayList;
import java.util.List;

public class Rank {

    private final String name;
    private final int som;

    private Rank(String name, int weight){
        this.name = name;
        this.som = calcLetterValue(name.toLowerCase())*weight;
    }

    private int calcLetterValue(String name){
        int value=0;
        for (int i=0; i<name.length(); i++){
            value+=name.charAt(i)-96;
        }
        return value+name.length();
    }

    public static String nthRank(String st, Integer[] we, int n) {

        String[] names = st.split(",");

        if (st.equals("")) return "No participants";
        if (names.length<n) return "Not enough participants";

        List<Rank> sortedNames = new ArrayList<>();
        for (int i=0; i<names.length; i++){
            sortedNames.add(new Rank(names[i],we[i]));
        }

        sortedNames.sort((o1, o2) -> {
            if (o1.som==o2.som) return o1.name.compareTo(o2.name);
            else return o2.som - o1.som;
        });

        return sortedNames.get(n-1).name;
    }

}
