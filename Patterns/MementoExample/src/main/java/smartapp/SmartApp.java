package smartapp;

public class SmartApp {

    private Double version;

    public void changeVersion(Double version){
        this.version = version;
        System.out.println("nowa wersja : "+version);
    }

    public SmartAppMemento save(){
        return new SmartAppMemento(version);
    }

    public void load(SmartAppMemento smartAppMemento){
        this.version = smartAppMemento.getVersion();
    }

}
