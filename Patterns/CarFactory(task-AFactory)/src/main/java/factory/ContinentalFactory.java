package factory;

import cars.*;

public class ContinentalFactory extends Factory{

    @Override
    public Car buildBMW(BMWModel bmwModel) {
        return switch (bmwModel){
            case X5 -> new BMW(2.4, FuelType.DIESEL,2016, SteeringWheelPosition.RIGHT);
            case E60 -> new BMW(2.0,FuelType.PETROL, 2018, SteeringWheelPosition.RIGHT);
        };
    }

    @Override
    public Car buildFord(FordModel fordModel) {
        return switch (fordModel){
            case CMAX -> new Ford(1.9, FuelType.DIESEL, 2017, SteeringWheelPosition.RIGHT);
            case FOCUS -> new Ford(1.6,FuelType.PETROL,2019,SteeringWheelPosition.RIGHT);
        };
    }
}
