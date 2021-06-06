import template.*;

public class Main {

    public static void main(String[] args) {

        System.out.println("klasyczne uruchomienie:");
        StartTheCar car1 = new ClassicStart();

        car1.startTheCar();

        System.out.println("\nautomat:");

        StartTheCar car2 = new StartAutomaticGearCar();
        car2.startTheCar();

        System.out.println("\nprzycisk :");
        StartTheCar car3 = new StartCarWithButton();
        car3.startTheCar();

    }
}
