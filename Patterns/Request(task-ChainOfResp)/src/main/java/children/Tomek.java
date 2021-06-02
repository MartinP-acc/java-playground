package children;

import request.MotherRequest;
import request.Shelf;

public class Tomek extends Child{

    @Override
    public void processRequest(MotherRequest motherRequest) {
        if (motherRequest.getShelf().equals(Shelf.MEDIUM)) System.out.println("Tomek przyniósł słoik z wyższej półki");
        else {
            System.out.println("to nie robota dla Tomka, przekazał fuche Antkowi");
            getTallerChild().processRequest(motherRequest);
        }
    }

}
