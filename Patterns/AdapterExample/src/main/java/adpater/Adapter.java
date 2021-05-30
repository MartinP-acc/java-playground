package adpater;

import continental.ContinentalDevice;
import uk.UKDevice;

public class Adapter implements ContinentalDevice, UKDevice{

    UKDevice ukDevice;
    ContinentalDevice continentalDevice;

    public Adapter(UKDevice ukDevice, ContinentalDevice continentalDevice) {
        this.ukDevice = ukDevice;
        this.continentalDevice = continentalDevice;
    }

    public void turnOn() {
        ukDevice.powerOn();
    }

    public void powerOn() {
        continentalDevice.turnOn();
    }
}
