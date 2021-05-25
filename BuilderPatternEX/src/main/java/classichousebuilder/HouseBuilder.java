package classichousebuilder;

public interface HouseBuilder {

    void buildWalls();
    void buildFloors();
    void buildRoof();
    void buildDoors();
    void buildWindows();
    void buildGarage();
    void buildRooms();

    HouseCHB getHouse();
}
