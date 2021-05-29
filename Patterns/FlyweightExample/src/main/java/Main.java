import units.Helicopter;
import units.Rifleman;
import units.Tank;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        List<Object> allUnits = new ArrayList<Object>();

        for (int i = 0; i<10000; i++){
            allUnits.add(new Tank(i,1));
            allUnits.add(new Rifleman(i,2));
            allUnits.add(new Helicopter(i,3));
        }

        for (Object unit : allUnits) System.out.println(unit.toString());
    }
}
