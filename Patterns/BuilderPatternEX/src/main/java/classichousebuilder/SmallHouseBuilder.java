package classichousebuilder;

public class SmallHouseBuilder implements HouseBuilder {

    private HouseCHB houseCHB;

    public SmallHouseBuilder() {
        this.houseCHB = new HouseCHB();
    }

    @Override
    public void buildWalls() {
        this.houseCHB.setWalls("small walls");
    }

    @Override
    public void buildFloors() {
        this.houseCHB.setFloors("small floors");
    }

    @Override
    public void buildRoof() {
        this.houseCHB.setRoof("small roof");
    }

    @Override
    public void buildDoors() {
        this.houseCHB.setDoors("small door");
    }

    @Override
    public void buildWindows() {
        this.houseCHB.setWindows("small windows");
    }

    @Override
    public void buildGarage() {
        this.houseCHB.setGarage("small garage");
    }

    @Override
    public void buildRooms() {
        this.houseCHB.setRooms("small rooms");
    }

    @Override
    public HouseCHB getHouse() {
        return houseCHB;
    }
}
