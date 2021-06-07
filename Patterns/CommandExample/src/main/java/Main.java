import workshop.Robot;
import workshop.WorkshopApp;
import workshop.command.CutCommand;
import workshop.command.DrillCommand;
import workshop.command.TurnOffCommand;
import workshop.command.TurnOnCommand;

public class Main {

    public static void main(String[] args) {


        Robot robot = new Robot();

        WorkshopApp workshopApp = new WorkshopApp();

        workshopApp.addNextCommand(new TurnOnCommand(robot));
        workshopApp.addNextCommand(new DrillCommand(robot));
        workshopApp.addNextCommand(new CutCommand(robot));
        workshopApp.addNextCommand(new TurnOffCommand(robot));

        workshopApp.run();

    }
}
