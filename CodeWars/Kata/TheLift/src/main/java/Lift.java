import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Lift {

    private final int capacity;
    private int floor;
    private List<Person> passengers;
    private Set<Integer> pressedFloorButtons;

    Lift(int capacity){
        this.capacity = capacity;
        this.floor = 0;
        this.passengers = new ArrayList<>();
        this.pressedFloorButtons = new HashSet<>();
    }

    public void moveUp(){
        this.floor++;
    }

    public void moveDown(){
        this.floor--;
    }

    public void takePassenger(Person person){
        passengers.add(person);
        pressedFloorButtons.add(person.getTargetFloor());
    }

    public List<Person> releasePassengers(){
        List<Person> out = passengers.stream()
                .filter(person -> person.getTargetFloor() == floor)
                .collect(Collectors.toList());
        passengers.removeAll(out);
        pressedFloorButtons.remove(floor);
        return out;
    }

    public String getDirection() {
        if (passengers.isEmpty()) return "NONE";
        if (passengers.get(0).getTargetFloor()>floor) return "UP";
        else return "DOWN";
    }

    public int getFloor() {
        return floor;
    }

    public boolean isLiftEmpty(){
        return passengers.isEmpty();
    }

    public boolean isLiftFull(){
        return passengers.size()>=capacity;
    }

    public boolean shouldReleaseHere(){
        return pressedFloorButtons.contains(floor);
    }
}
