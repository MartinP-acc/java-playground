import factory.BlueFactionFactory;
import factory.RedFactionFactory;
import units.Unit;

public class Main {

    public static void main(String[] args) {

        RedFactionFactory redUnits = RedFactionFactory.getInstance();
        BlueFactionFactory blueUnits = BlueFactionFactory.getInstance();

        System.out.println("blue units :");
        Unit riflemanBlue = blueUnits.createRifleman();
        System.out.println("rifleman "+riflemanBlue.toString());
        Unit tankBlue = blueUnits.createTank();
        System.out.println("tank "+tankBlue.toString());

        System.out.println("\nred units:");
        Unit riflemanRed = redUnits.createRifleman();
        Unit helicopterRed = redUnits.createHelicopter();
        System.out.println("rifleman "+riflemanRed.toString());
        System.out.println("helicopter "+helicopterRed.toString());

    }
}
