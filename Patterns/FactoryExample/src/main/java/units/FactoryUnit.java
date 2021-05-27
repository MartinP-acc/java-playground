package units;

public class FactoryUnit extends Factory {

    private static final FactoryUnit instance = new FactoryUnit();

    @Override
    public Unit createUnit(UnitType unitType) {
        return switch (unitType){
            case TANK -> new Tank(100,0,50);
            case RIFLEMAN -> new Rifleman(25, 0, 20);
        };
    }

    public static FactoryUnit getInstance() {
        return instance;
    }
}
