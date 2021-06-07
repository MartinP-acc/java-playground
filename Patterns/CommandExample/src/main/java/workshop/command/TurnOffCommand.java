package workshop.command;

import workshop.Robot;

public class TurnOffCommand implements Command{

    private Robot robot;

    public TurnOffCommand(Robot robot) {
        this.robot = robot;
    }

    public void execute() {
        robot.turnOff();
    }

    @Override
    public void undo() {
        robot.turnOn();
    }
}
