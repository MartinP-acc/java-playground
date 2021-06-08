package smartapp;

import java.util.ArrayList;
import java.util.List;

public class SmartAppCaretaker {

    private List<SmartAppMemento> versionList = new ArrayList<>();

    public void addMemento(SmartAppMemento smartAppMemento){
        versionList.add(smartAppMemento);
        System.out.println("Zapisana wersja :" +smartAppMemento.getVersion()+" pod indeksem :"+(versionList.size()-1));
    }

    public SmartAppMemento getMemento(int index){
        System.out.println("wczytano wersję "+versionList.get(index).getVersion());
        return versionList.get(index);
    }
}
