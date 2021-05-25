package classichousebuilder;

public class BigHouseBuilder implements HouseBuilder {

    private HouseCHB houseCHB;

    public BigHouseBuilder() {
        this.houseCHB = new HouseCHB();
    }

    @Override
    public void buildWalls() {
        this.houseCHB.setWalls("big walls");
    }

    @Override
    public void buildFloors() {
        this.houseCHB.setFloors("big floors");
    }

    @Override
    public void buildRoof() {
        this.houseCHB.setRoof("big roof");
    }

    @Override
    public void buildDoors() {
        this.houseCHB.setDoors("big door");
    }

    @Override
    public void buildWindows() {
        this.houseCHB.setWindows("big windows");
    }

    @Override
    public void buildGarage() {
        this.houseCHB.setGarage("big garage");
    }

    @Override
    public void buildRooms() {
        this.houseCHB.setRooms("big rooms");
    }

    @Override
    public HouseCHB getHouse() {
        return houseCHB;
    }

}
