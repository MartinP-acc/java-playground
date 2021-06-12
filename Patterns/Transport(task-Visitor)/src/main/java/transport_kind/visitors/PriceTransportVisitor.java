package transport_kind.visitors;

import transport_kind.Animal;
import transport_kind.Person;
import transport_kind.Shipment;

public class PriceTransportVisitor implements TransportVisitor{

    @Override
    public void visit(Animal animal) {
        System.out.println("cena za wysyłane zwierze :"+animal.getWeight()*0.2);
    }

    @Override
    public void visit(Person person) {
        int price = 6;
        if (person.isRegularCustomer()) System.out.println("stały klient - cena :"+price/2);
        else System.out.println("cena biletu : "+price);
    }

    @Override
    public void visit(Shipment shipment) {
        int price = 2;
        if (shipment.isLarge()) System.out.println("paczka wielogabarytowa - cena:"+ (price+1));
        else System.out.println("zwykła paczka - cena:"+ price);
    }
}
