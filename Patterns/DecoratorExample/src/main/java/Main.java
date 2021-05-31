public class Main {

    public static void main(String[] args) {

        Terrain plain = new Plain();
        System.out.println("Równina - fuel cost : "+ plain.getFuelCost());

        Terrain hill = new Hill();
        System.out.println("wzgórze - fuel cost : "+ hill.getFuelCost());

        Terrain swampPlain = new Swamp(new Plain());
        System.out.println("bagnista równina - fuel cost : "+ swampPlain.getFuelCost());

        Terrain forestSwampHill = new Forest(new Swamp(new Hill()));
        System.out.println("bagniste zalesione wzgórze - fuel cost : "+ forestSwampHill.getFuelCost());

    }
}
