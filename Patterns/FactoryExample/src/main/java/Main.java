import units.Factory;
import units.FactoryUnit;
import units.Unit;
import units.UnitType;

public class Main {

    public static void main(String[] args) {

        Factory factory = FactoryUnit.getInstance();

        Unit tank = factory.createUnit(UnitType.TANK);
        Unit rifleman = factory.createUnit(UnitType.RIFLEMAN);

        System.out.println(tank.toString());
        System.out.println(rifleman.toString());

    }

}
