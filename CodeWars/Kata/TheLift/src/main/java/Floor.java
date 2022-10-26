import java.util.ArrayList;
import java.util.List;

public class Floor {

    private final int floorNr;
    public List<Person> queue;
    private boolean buttonUP = false;
    private boolean buttonDOWN = false;

    Floor(int floorNr, int[] queue){
        this.floorNr = floorNr;
        this.queue = new ArrayList<>();
        for (int target : queue){
            if (target>floorNr) buttonUP = true;
            if (target<floorNr) buttonDOWN = true;
            this.queue.add(new Person(target));
        }
    }

    public void sendToLift(Lift lift){
        queue.forEach(person -> {
            if (!lift.isLiftFull()){
                switch (lift.getDirection()){
                    case "NONE" : lift.takePassenger(person); break;
                    case "UP" : if (person.getTargetFloor()>floorNr) lift.takePassenger(person);
                    case "DOWN" : if (person.getTargetFloor()<floorNr) lift.takePassenger(person);
                }
                queue.remove(person);
            }
        });
    }

    public boolean hasPassengersToMove(){
        for (Person p : queue){
            if (p.getTargetFloor()!=floorNr) return true;
        }
        return false;
    }

    public boolean checkButtonUP(){
        return buttonUP;
    }

    public boolean checkButtonDOWN(){
        return buttonDOWN;
    }

    private void resetButtons(){
        buttonDOWN = buttonUP = false;
    }

    public void pushButtons(){
        resetButtons();
        queue.forEach(person -> {
            if (person.getTargetFloor()>floorNr) buttonUP = true;
            if (person.getTargetFloor()<floorNr) buttonDOWN = true;
        });
    }
}
