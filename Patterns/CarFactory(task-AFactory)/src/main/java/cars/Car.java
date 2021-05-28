package cars;

abstract public class Car {

    private double engineCap;
    private FuelType fuelType;
    private int prodYear;
    private SteeringWheelPosition steeringWheelPosition;

    public Car(double engineCap, FuelType fuelType, int prodYear, SteeringWheelPosition steeringWheelPosition) {
        this.engineCap = engineCap;
        this.fuelType = fuelType;
        this.prodYear = prodYear;
        this.steeringWheelPosition = steeringWheelPosition;
    }

    public double getEngineCap() {
        return engineCap;
    }

    public FuelType getFuelType() {
        return fuelType;
    }

    public int getProdYear() {
        return prodYear;
    }

    public SteeringWheelPosition getSteeringWheelPosition() {
        return steeringWheelPosition;
    }

    @Override
    public String toString() {
        return "Car{" +
                "engineCap=" + engineCap +
                ", fuelType=" + fuelType +
                ", prodYear=" + prodYear +
                ", steeringWheelPosition=" + steeringWheelPosition +
                '}';
    }
}
