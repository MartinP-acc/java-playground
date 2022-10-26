import javafx.event.Event;

public class IPrint {

    public static void print(Event e) {
        String type = e.getEventType().getName();
        String source = e.getSource().getClass().getSimpleName();
        String target = e.getTarget().getClass().getSimpleName();
        System.out.println("Event Type : "+type+", Event Source : "+source+", Target : "+target);
    }
}
