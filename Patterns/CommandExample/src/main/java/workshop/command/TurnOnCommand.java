package workshop.command;

import workshop.Robot;

public class TurnOnCommand implements Command{

    private Robot robot;

    public TurnOnCommand(Robot robot) {
        this.robot = robot;
    }

    public void execute() {
        robot.turnOn();
    }

    @Override
    public void undo() {
        robot.turnOff();
    }
}
