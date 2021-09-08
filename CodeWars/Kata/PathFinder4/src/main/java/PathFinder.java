import java.awt.*;

public class PathFinder {

    private static final String[] VECTORS = new String[]{"up","right","down","left"};
    private static Point me = new Point(0,0);
    private static int index = 0;

    private static void turn(String s){
        switch (s){
            case "r" : index++; break;
            case "l" : index--; break;
            case "R" : index+=2; break;
            case "L" : index-=2; break;
        }
        if (index<0) index+=4;
        if (index>3) index-=4;
    }

    private static void move(int steps){
        switch (VECTORS[index]){
            case "up" : me.x-=steps; break;
            case "right" : me.y+=steps; break;
            case "down" : me.x+=steps; break;
            case "left" : me.y-=steps; break;
        }
    }

    public static Point iAmHere(String path) {
        System.out.println(path);
        if (path.equals("")) return me;
        path = path.replaceAll("[^0-9]"," $0 ").replaceAll("\s+","\s").trim();
        String[] moves = path.split(" ");
        for (String m : moves){
            if (m.matches("[^0-9]+")) turn(m);
            else move(Integer.parseInt(m));
        }
        return me;
    }
}
