abstract public class WorkDayTemplate {

    public final void startDay(TypeOfTransport transport){
        wakeUp();
        haveBreakfast();
        int time = goToWork(transport);
        work(time);
        lunchTime();
        backHome();
    }

    private void backHome() {
        System.out.println("powrót do domu");
    }

    protected void lunchTime() {
        System.out.println("czas na lunch");
    }

    protected void work(int time){
        System.out.println("następnie po "+time+" minutach rozpoczynam pracę");
    }

    abstract protected int goToWork(TypeOfTransport transport);

    protected void haveBreakfast() {
        System.out.println("jem śniadanie");
    }

    private void wakeUp() {
        System.out.println("pobudka");
    }
}
