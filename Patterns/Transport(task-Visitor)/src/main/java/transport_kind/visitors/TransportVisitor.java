package transport_kind.visitors;

import transport_kind.Animal;
import transport_kind.Person;
import transport_kind.Shipment;

public interface TransportVisitor {

    void visit (Animal animal);
    void visit (Person person);
    void visit (Shipment shipment);
}
