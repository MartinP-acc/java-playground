package workshop.command;

import workshop.Robot;

public class CutCommand implements Command{

    private Robot robot;

    public CutCommand(Robot robot) {
        this.robot = robot;
    }

    public void execute() {
        robot.cut();
    }

    @Override
    public void undo() {
        System.out.println("zatrzymuje cięcie");
    }
}
