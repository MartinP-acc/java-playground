import java.util.ArrayList;
import java.util.List;

public class Dinglemouse {

    private static List<Lights> lights;
    private static List<Integer> cars;

    private static void initLightsAndCars(String road){
        lights = new ArrayList<>();
        cars = new ArrayList<>();
        for (int i = road.length()-1 ; i >= 0; i--){
            switch (road.charAt(i)){
                case 'G' : lights.add(new Lights('G',i)); break;
                case 'R' : lights.add(new Lights('R',i)); break;
                case 'C' : cars.add(i);
            }
        }
    }

    private static void setLights(StringBuilder r){
        for (Lights l : lights){
            l.incTimer();
            r.setCharAt(l.getPosition(), l.getLight());
        }
    }

    private static void setCars(StringBuilder r){
        for (int i=0; i<cars.size(); i++) {
            boolean move = false;
            if (cars.get(i) < r.length() - 1) {
                if (r.charAt(cars.get(i) + 1) == '.') move = true;
                if (r.charAt(cars.get(i) + 1) == 'G'){
                    if (cars.get(i)>=r.length()-2) move = true;
                    else if (r.charAt(cars.get(i) + 2) == '.') move =true;
                }
            } else move = true;
            if (move) cars.set(i, cars.get(i)+1);
            if (cars.get(i) < r.length()) r.setCharAt(cars.get(i), 'C');
        }
    }

    public static String[] trafficLights(String road, int n) {

        initLightsAndCars(road);
        String[] roadTimeLapse = new String[n+1];
        roadTimeLapse[0]=road;

        System.out.println("0\t"+road);
        for (int i=1; i<=n; i++){
            StringBuilder r = new StringBuilder(".".repeat(road.length()));
            setLights(r);
            setCars(r);
            roadTimeLapse[i] = r.toString();
            System.out.println(i+"\t"+r);
        }
        return roadTimeLapse;
    }
}