package workshop.command;

import workshop.Robot;

public class DrillCommand implements Command{

    private Robot robot;

    public DrillCommand(Robot robot) {
        this.robot = robot;
    }

    public void execute() {
        robot.drill();
    }

    @Override
    public void undo() {
        System.out.println("zatrzymuję wiercenie");
    }
}
