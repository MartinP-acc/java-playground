import request.*;
import children.*;

public class Main {

    public static void main(String[] args) {
        MotherRequest motherRequest = new MotherRequest(Shelf.HIGH);
        Child tomek = new Tomek();
        Child ania = new Ania();
        Child antek = new Antek();
        tomek.setTallerChild(antek);
        ania.setTallerChild(tomek);
        antek.setTallerChild(ania);
        tomek.processRequest(motherRequest);

        System.out.println("-----------------------");
        MotherRequest motherRequest1 = new MotherRequest(Shelf.MEDIUM);
        antek.processRequest(motherRequest1);
    }
}
