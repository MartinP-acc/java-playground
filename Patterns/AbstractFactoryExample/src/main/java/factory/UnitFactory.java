package factory;

import units.Unit;

abstract public class UnitFactory {

    abstract public Unit createTank();
    abstract public Unit createRifleman();
    abstract public Unit createHelicopter();
}
