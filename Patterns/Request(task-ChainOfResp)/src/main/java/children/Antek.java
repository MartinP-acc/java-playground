package children;

import request.MotherRequest;
import request.Shelf;

public class Antek extends Child{

    @Override
    public void processRequest(MotherRequest motherRequest) {
        if (motherRequest.getShelf().equals(Shelf.HIGH)) System.out.println("Antek przyniósł słoik z najwyższej półki");
        else{
            System.out.println("Antka bolą plery i nie będzie się schylał");
            getTallerChild().processRequest(motherRequest);
        }
    }
}
