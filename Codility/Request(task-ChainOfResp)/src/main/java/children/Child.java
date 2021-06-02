package children;

import request.MotherRequest;
import request.Shelf;

abstract public class Child {

    private Child tallerChild;

    abstract public void processRequest(MotherRequest motherRequest);

    public Child getTallerChild() {
        return tallerChild;
    }

    public void setTallerChild(Child tallerChild) {
        this.tallerChild = tallerChild;
    }
}
