package template;

public class StartCarWithButton extends StartTheCar{

    @Override
    public void startTheEngine() {
        System.out.println("wciśnięt przycisk i uruchomienie silnika");
    }

    @Override
    public void setTheGear() {
        System.out.println("");
    }
}
