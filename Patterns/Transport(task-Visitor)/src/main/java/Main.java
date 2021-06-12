import transport_kind.Animal;
import transport_kind.Person;
import transport_kind.Shipment;
import transport_kind.Transportable;
import transport_kind.visitors.NameTransportVisitor;
import transport_kind.visitors.PriceTransportVisitor;

import java.util.Arrays;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        Animal animal = new Animal("dog", 30);
        Person person = new Person("Dawid", "Nowak", true);
        Shipment shipment = new Shipment("PL", "4325452", false);
        List<Transportable> transportableList = Arrays.asList(animal, person, shipment);

        for (Transportable t : transportableList) t.accept(new PriceTransportVisitor());
        System.out.println("--------------------------------------------------");
        for (Transportable t : transportableList) t.accept(new NameTransportVisitor());

    }
}
