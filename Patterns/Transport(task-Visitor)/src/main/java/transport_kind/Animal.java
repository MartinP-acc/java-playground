package transport_kind;

import transport_kind.visitors.TransportVisitor;

public class Animal implements Transportable{

    private String kind;
    private int weight;

    public Animal(String kind, int weight) {
        this.kind = kind;
        this.weight = weight;
    }

    @Override
    public void accept(TransportVisitor visitor) {
        visitor.visit(this);
    }

    public String getKind() {
        return kind;
    }

    public int getWeight() {
        return weight;
    }
}
