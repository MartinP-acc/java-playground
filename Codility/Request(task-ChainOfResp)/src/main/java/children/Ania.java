package children;

import request.MotherRequest;
import request.Shelf;

public class Ania extends Child{

    @Override
    public void processRequest(MotherRequest motherRequest) {
        if (motherRequest.getShelf().equals(Shelf.LOW)) System.out.println("Ania przyniosła słoik z niskiej półki");
        else {
            System.out.println("Ania to kurdupel przekazała robotę Tomkowi");
            getTallerChild().processRequest(motherRequest);
        }
    }
}
