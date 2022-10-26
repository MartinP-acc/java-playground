public class Dinglemouse {

    public static int[] theLift(final int[][] queues, final int capacity) {
        Building building = new Building(queues,capacity);
        building.startSimulation();
        return building.getStopRegister();
    }

}
