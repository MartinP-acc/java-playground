import classichousebuilder.BigHouseBuilder;
import classichousebuilder.HouseCHB;
import classichousebuilder.HouseDirector;
import classichousebuilder.SmallHouseBuilder;
import house.House;
import housebuilder.HouseBP;

public class Main {

    public static void main(String[] args) {
        //from here ...
        House house1 = new House("black wall",
                "brown floor",
                "4 rooms",
                "metal roof",
                "white window",
                "wooden door",
                "small garage");
        System.out.println(house1);

        House house2 = new House(
                "black wall",
                "brown floor",
                "4 rooms",
                "metal roof");
        System.out.println(house2);
        //to this line - example how don't do this

        //example Builder Pattern - internal class
        HouseBP houseBuilder = new HouseBP.HouseBuilder()
                .buildWalls("white walls")
                .buildFloors("wooden floor")
                .buildRoof("red roof")
                .buildGarage("large garage")
                .build();
        System.out.println(houseBuilder);

        //example Classic Builder Pattern
        SmallHouseBuilder smallHouseBuilder = new SmallHouseBuilder();
        BigHouseBuilder bigHouseBuilder = new BigHouseBuilder();

        HouseDirector houseDirector = new HouseDirector(smallHouseBuilder);
        houseDirector.buildHouse();

        HouseDirector bigHouseDirector = new HouseDirector(bigHouseBuilder);
        bigHouseDirector.buildHouse();

        HouseCHB smallHouse = smallHouseBuilder.getHouse();
        HouseCHB bigHouse = bigHouseBuilder.getHouse();

        System.out.println(smallHouse.toString());
        System.out.println(bigHouse.toString());

    }
}
