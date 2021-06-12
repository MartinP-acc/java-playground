package transport_kind.visitors;

import transport_kind.Animal;
import transport_kind.Person;
import transport_kind.Shipment;

public class NameTransportVisitor implements TransportVisitor{

    @Override
    public void visit(Animal animal) {
        System.out.println("Gatunek transportowanego zwierzęcia : "+ animal.getKind());
    }

    @Override
    public void visit(Person person) {
        System.out.println("Pasażer - Imię : "+person.getName()+" Nazwisko :"+person.getSurname());
    }

    @Override
    public void visit(Shipment shipment) {
        System.out.println("Transportowana paczka nr: "+shipment.getSerialNr()+" prefix: "+shipment.getPrefix());
    }
}
