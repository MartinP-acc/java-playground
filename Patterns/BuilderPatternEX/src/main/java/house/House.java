package house;

public class House {
// DON'T DO THIS !
    private String walls;
    private String floors;
    private String rooms;
    private String roof;
    private String windows;
    private String doors;
    private String garage;

    public House(String walls, String floors, String rooms, String roof, String windows, String doors, String garage) {
        this.walls = walls;
        this.floors = floors;
        this.rooms = rooms;
        this.roof = roof;
        this.windows = windows;
        this.doors = doors;
        this.garage = garage;
    }

    public House(String walls, String floors, String rooms, String roof) {
        this.walls = walls;
        this.floors = floors;
        this.rooms = rooms;
        this.roof = roof;
    }

    public House(String walls, String floors, String rooms, String roof, String windows, String doors) {
        this.walls = walls;
        this.floors = floors;
        this.rooms = rooms;
        this.roof = roof;
        this.windows = windows;
        this.doors = doors;
    }

    public String getWalls() {
        return walls;
    }

    public void setWalls(String walls) {
        this.walls = walls;
    }

    public String getFloors() {
        return floors;
    }

    public void setFloors(String floors) {
        this.floors = floors;
    }

    public String getRooms() {
        return rooms;
    }

    public void setRooms(String rooms) {
        this.rooms = rooms;
    }

    public String getRoof() {
        return roof;
    }

    public void setRoof(String roof) {
        this.roof = roof;
    }

    public String getWindows() {
        return windows;
    }

    public void setWindows(String windows) {
        this.windows = windows;
    }

    public String getDoors() {
        return doors;
    }

    public void setDoors(String doors) {
        this.doors = doors;
    }

    public String getGarage() {
        return garage;
    }

    public void setGarage(String garage) {
        this.garage = garage;
    }

    @Override
    public String toString() {
        return "House{" +
                "walls='" + walls + '\'' +
                ", floors='" + floors + '\'' +
                ", rooms='" + rooms + '\'' +
                ", roof='" + roof + '\'' +
                ", windows='" + windows + '\'' +
                ", doors='" + doors + '\'' +
                ", garage='" + garage + '\'' +
                '}';
    }
}