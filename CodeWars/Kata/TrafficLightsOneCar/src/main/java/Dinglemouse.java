import java.util.ArrayList;
import java.util.List;

public class Dinglemouse {

    private static List<Lights> lights;
    private static int car;

    private static void initLights(String road){
        lights = new ArrayList<>();
        for (int i = 0 ; i < road.length(); i++){
            switch (road.charAt(i)){
                case 'G' : lights.add(new Lights('G',i)); break;
                case 'R' : lights.add(new Lights('R',i)); break;
                case 'C' : car = i;
            }
        }
    }

    private static void setLights(StringBuilder r){
        for (Lights l : lights){
            l.incTimer();
            r.setCharAt(l.getPosition(), l.getLight());
        }
    }

    private static void setCar(StringBuilder r){
        if (car<r.length()-1){
            if (r.charAt(car+1)!='O' && r.charAt(car+1)!='R') car++;
        }else car++;
        if (car<r.length()) r.setCharAt(car,'C');
    }

    public static String[] trafficLights(String road, int n) {

        initLights(road);
        String[] roadTimeLapse = new String[n+1];
        roadTimeLapse[0]=road;

        for (int i=1; i<=n; i++){
            StringBuilder r = new StringBuilder(".".repeat(road.length()));
            setLights(r);
            setCar(r);
            roadTimeLapse[i] = r.toString();
        }
        return roadTimeLapse;
    }
}