import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class DirectionReduction {

    public static String[] dirReduc(String[] arr) {

        LinkedList<String> stack = new LinkedList<>();
        LinkedList<String> reduced = new LinkedList<>();

        for (String direction : arr){

            if (!stack.isEmpty() && stack.peek().equals(direction)){
                stack.pop();
                reduced.pop();
            }else {
                reduced.push(direction);
                switch (direction){
                    case "SOUTH" : stack.push("NORTH");
                    break;
                    case "EAST" : stack.push("WEST");
                    break;
                    case "WEST" : stack.push("EAST");
                    break;
                    case "NORTH" : stack.push("SOUTH");
                    break;
                }
            }
        }
        String[] reducedInCorrectOrder = new String[reduced.size()];
        for (int i=reduced.size()-1; i>=0; i--){
            reducedInCorrectOrder[i] = reduced.pop();
        }

        return reducedInCorrectOrder;
    }
}
