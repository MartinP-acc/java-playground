public class HumanReadableTime {
    public static String makeReadable(int seconds) {
        int hours = seconds/3600;
        int minutes = seconds/60 - hours*60;
        seconds = seconds - minutes*60 - hours*3600;
        return (hours<10?"0":"")+hours
                +":"+(minutes<10?"0":"")+minutes
                +":"+(seconds<10?"0":"")+seconds;
    }
}
