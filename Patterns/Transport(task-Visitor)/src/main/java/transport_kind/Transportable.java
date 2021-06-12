package transport_kind;

import transport_kind.visitors.TransportVisitor;

public interface Transportable {

    void accept(TransportVisitor visitor);
}
