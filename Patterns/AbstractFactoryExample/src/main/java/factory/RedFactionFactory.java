package factory;

import units.HeliUnit;
import units.RiflemanUnit;
import units.TankUnit;
import units.Unit;

public class RedFactionFactory extends UnitFactory{

    static private RedFactionFactory instance = new RedFactionFactory();

    @Override
    public Unit createTank() {
        return new TankUnit(100,40,0);
    }

    @Override
    public Unit createRifleman() {
        return new RiflemanUnit(25,15,0);
    }

    @Override
    public Unit createHelicopter() {
        return new HeliUnit(50,40,0);
    }

    static public RedFactionFactory getInstance() {
        return instance;
    }
}
