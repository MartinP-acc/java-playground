import java.awt.*;
import java.util.HashMap;

public class Dinglemouse {

    private static final int OK = 1;
    private static final char[][] LAYOUT = new char[][]{
            {'a','b','c','d','e','1','2','3'},
            {'f','g','h','i','j','4','5','6'},
            {'k','l','m','n','o','7','8','9'},
            {'p','q','r','s','t','.','@','0'},
            {'u','v','w','x','y','z','_','/'}
    };
    private static HashMap<Character, Point> keyPoints;

    public static int tvRemote(final String word) {
        initKeyPoints();
        Point currentPos = new Point(0,0);
        int presses = 0;
        for (int i=0; i<word.length(); i++){
            Point next = keyPoints.get(word.charAt(i));
            presses += Math.abs(currentPos.x-next.x)+Math.abs(currentPos.y-next.y) + OK;
            currentPos = next;
        }
        return presses;
    }

    private static void initKeyPoints(){
        keyPoints = new HashMap<>();
        for (int x=0; x<LAYOUT.length; x++){
            for (int y=0; y<LAYOUT[x].length;y++){
                keyPoints.put(LAYOUT[x][y],new Point(x,y));
            }
        }
    }
}
