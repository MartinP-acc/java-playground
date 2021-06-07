package workshop;

import workshop.command.Command;

import java.util.ArrayList;
import java.util.List;

public class WorkshopApp {

    private List<Command> commands = new ArrayList<Command>();

    public void addNextCommand(Command command){
        commands.add(command);
    }

    public void run(){
        if (commands.isEmpty()) System.out.println(" --- nie ma żadnych komend ---");
        else {
            for (Command command : commands) command.execute();
        }
        commands.clear();
    }

    public void undoLastCommand(){
        for (Command command : commands){
            command.undo();
        }
        commands.clear();
    }
}
