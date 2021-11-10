import java.awt.*;
import java.util.HashMap;

public class Dinglemouse {

    private static final int OK = 1;
    private static final String[][] LAYOUT = new String[][]{
            {"a","b","c","d","e","1","2","3"},
            {"f","g","h","i","j","4","5","6"},
            {"k","l","m","n","o","7","8","9"},
            {"p","q","r","s","t",".","@","0"},
            {"u","v","w","x","y","z","_","/"},
            {"aA"," "}
    };
    private static HashMap<String, Point> keyPoints;

    private static void initKeyPoints(){
        keyPoints = new HashMap<>();
        for (int x=0; x<LAYOUT.length; x++){
            for (int y=0; y<LAYOUT[x].length; y++){
                keyPoints.put(LAYOUT[x][y],new Point(x,y));
            }
        }
    }

    public static int tvRemote(final String words) {
        initKeyPoints();
        int totalPresses = 0;
        Point current = new Point(0,0);
        boolean upperIsOn = false;
        for (int i=0; i<words.length(); i++){
            String letter = words.substring(i,i+1);
            Point next = keyPoints.get(letter.toLowerCase());
            if (upperIsOn!=letter.matches("[A-Z]") && letter.matches("[a-zA-Z]")){
                Point aA = keyPoints.get("aA");
                totalPresses+= Math.abs(current.x-aA.x)+Math.abs(current.y-aA.y)+OK;
                current = aA;
                upperIsOn=!upperIsOn;
            }
            totalPresses+= Math.abs(current.x-next.x)+Math.abs(current.y-next.y)+OK;
            current = next;
        }
        return totalPresses;
    }
}
