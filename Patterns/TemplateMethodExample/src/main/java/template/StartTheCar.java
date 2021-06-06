package template;

abstract public class StartTheCar {

    public final void startTheCar(){
        fastenSeatBelts();
        startTheEngine();
        setTheGear();
        go();
    }

    public void startTheEngine() {
        System.out.println("uruchomiony silnik");
    }

    public void setTheGear() {
        System.out.println("ustawiony bieg");
    }

    private void go() {
        System.out.println("wciśnięty pedał gazu");
    }

    private void fastenSeatBelts() {
        System.out.println("zapięte pasy");
    }
    

}
