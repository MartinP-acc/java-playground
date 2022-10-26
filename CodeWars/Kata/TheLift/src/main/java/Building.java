import java.util.ArrayList;
import java.util.List;

public class Building {
    private List<Floor> floors;
    private Lift lift;
    private List<Integer> stopRegister;

    Building(int[][] queues, int cap){
        floors = new ArrayList<>();
        stopRegister = new ArrayList<>();
        this.lift = new Lift(cap);
        for (int f=0; f< queues.length; f++){
            floors.add(new Floor(f,queues[f]));
        }
        System.out.println();
    }

    public void startSimulation(){
        while (!lift.isLiftEmpty() || isAnyButtonPressed()){
            if(lift.shouldReleaseHere() || currFloor().hasPassengersToMove())
                Stop();
            switch (lift.getDirection()){
                case "UP" : lift.moveUp(); break;
                case "DOWN" : lift.moveDown(); break;
                default:
                    for (int i=0; i<floors.size(); i++){
                        if (floors.get(i).hasPassengersToMove()){
                            if (i< lift.getFloor()) lift.moveDown();
                            else lift.moveUp();
                        }
                    }
            }
            System.out.println(lift.getFloor()+" "+lift.getDirection());

        }
    }

    public boolean isAnyButtonPressed(){
        for (Floor floor :floors)
            if (floor.checkButtonUP() || floor.checkButtonDOWN()) return true;
        return false;
    }

    public void Stop(){
        currFloor().queue.addAll(lift.releasePassengers());
        currFloor().sendToLift(lift);
        currFloor().pushButtons();
        stopRegister.add(lift.getFloor());
    }

    private Floor currFloor(){
        return floors.get(lift.getFloor());
    }

    public int[] getStopRegister() {
        return stopRegister.stream().mapToInt(i -> i).toArray();
    }
}
