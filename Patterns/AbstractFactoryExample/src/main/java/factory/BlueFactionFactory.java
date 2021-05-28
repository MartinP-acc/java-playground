package factory;

import units.HeliUnit;
import units.RiflemanUnit;
import units.TankUnit;
import units.Unit;

public class BlueFactionFactory extends UnitFactory {

    static private BlueFactionFactory instance = new BlueFactionFactory();

    @Override
    public Unit createTank() {
        return new TankUnit(90,45,0);
    }

    @Override
    public Unit createRifleman() {
        return new RiflemanUnit(30,20,0);
    }

    @Override
    public Unit createHelicopter() {
        return new HeliUnit(60,30,0);
    }

    static public BlueFactionFactory getInstance() {
        return instance;
    }
}
