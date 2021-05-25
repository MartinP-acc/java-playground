package classichousebuilder;

public class HouseDirector {

    private HouseBuilder houseBuilder;

    public HouseDirector(HouseBuilder houseBuilder){
        this.houseBuilder = houseBuilder;
    }

    public void buildHouse(){
        houseBuilder.buildDoors();
        houseBuilder.buildFloors();
        houseBuilder.buildGarage();
        houseBuilder.buildRoof();
        houseBuilder.buildRooms();
        houseBuilder.buildWalls();
        houseBuilder.buildWindows();
    }

    public HouseCHB getHouseCHB(){
        return this.houseBuilder.getHouse();
    }
}
