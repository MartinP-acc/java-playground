package transport_kind;

import transport_kind.visitors.TransportVisitor;

public class Shipment implements Transportable{

    private String prefix;
    private String serialNr;
    private boolean isLarge;

    public Shipment(String prefix, String serialNr, boolean isLarge) {
        this.prefix = prefix;
        this.serialNr = serialNr;
        this.isLarge = isLarge;
    }

    @Override
    public void accept(TransportVisitor visitor) {
        visitor.visit(this);
    }

    public String getPrefix() {
        return prefix;
    }

    public String getSerialNr() {
        return serialNr;
    }

    public boolean isLarge() {
        return isLarge;
    }
}
