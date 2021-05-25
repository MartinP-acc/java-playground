import flightleg.FlightLeg;

public class Main {

    public static void main(String[] args) {

        FlightLeg leg = new FlightLeg.FlightLegBuilder()
                .buildFrom("Las Vegas")
                .buildTo("Los Angeles")
                .buildPrice(50)
                .build();

        System.out.println(leg);


        FlightLeg leg2 = new FlightLeg.FlightLegBuilder()
                .buildFrom("Las Vegas")
                .buildTo("Los Angeles")
                .build();
        
        System.out.println(leg2);
    }
}
