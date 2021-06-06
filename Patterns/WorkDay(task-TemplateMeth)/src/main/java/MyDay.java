public class MyDay extends WorkDayTemplate{

    @Override
    protected void lunchTime() {
        System.out.println("za duży zapierdziel na lunch");
    }

    @Override
    protected int goToWork(TypeOfTransport transport) {
        return switch (transport){
            case CAR -> 20;
            case BIKE -> 18;
            case TRAM -> 23;
        };
    }

    @Override
    protected void haveBreakfast() {
        System.out.println("kawa zamiast śniadania");
    }
}
