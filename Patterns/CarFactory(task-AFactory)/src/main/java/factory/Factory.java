package factory;

import cars.Car;

abstract public class Factory {

    abstract public Car buildBMW(BMWModel bmwModel);
    abstract public Car buildFord(FordModel fordModel);
}
