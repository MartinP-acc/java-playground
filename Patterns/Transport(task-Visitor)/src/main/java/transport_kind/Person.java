package transport_kind;

import transport_kind.visitors.TransportVisitor;

public class Person implements Transportable {

    private String name;
    private String surname;
    private boolean isRegularCustomer;

    public Person(String name, String surname, boolean isRegularCustomer) {
        this.name = name;
        this.surname = surname;
        this.isRegularCustomer = isRegularCustomer;
    }

    @Override
    public void accept(TransportVisitor visitor) {
        visitor.visit(this);
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public boolean isRegularCustomer() {
        return isRegularCustomer;
    }
}
