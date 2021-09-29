import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Dinglemouse {

    private static List<Rat> rats;
    private static Point piper;

    public static int countDeafRats(char[][] townSquare) {
        findRatsAndPiper(townSquare);
        return getDeafRats();
    }

    private static void findRatsAndPiper(char[][] townSquare) {
        rats = new ArrayList<>();
        for (int x=0; x<townSquare.length; x++){
            for (int y=0; y<townSquare[x].length; y++){
                char ts = townSquare[x][y];
                if (ts=='P') piper = new Point(x,y);
                else if (ts!=' ') rats.add(new Rat(x,y,ts));
            }
        }
    }

    private static int getDeafRats() {
        int deafRats = 0;
        for (Rat rat : rats) if (rat.isDeaf(piper)) deafRats++;
        return deafRats;
    }
}

class Rat{
    Point current;
    Point next;

    Rat(int x, int y, char dir){
        this.current = new Point(x,y);
        switch (dir){
            case '←' : next = new Point(x,y-1); break;
            case '↑' : next = new Point(x-1,y); break;
            case '→' : next = new Point(x,y+1); break;
            case '↓' : next = new Point(x+1,y); break;
            case '↖' : next = new Point(x-1,y-1); break;
            case '↗' : next = new Point(x-1,y+1); break;
            case '↘' : next = new Point(x+1,y+1); break;
            case '↙' : next = new Point(x+1,y-1); break;
        }
    }

    public boolean isDeaf(Point piper){
        return current.distance(piper)<next.distance(piper);
    }
}