package testing;

public class Account {

    private boolean active;
    private Address defaultDeliveryAdress;

    public Account(Address defaultDeliveryAdress) {
        this.defaultDeliveryAdress = defaultDeliveryAdress;
        if(defaultDeliveryAdress!=null) activate();
        else this.active = false;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public Address getDefaultDeliveryAdress() {
        return defaultDeliveryAdress;
    }

    public void setDefaultDeliveryAdress(Address defaultDeliveryAdress) {
        this.defaultDeliveryAdress = defaultDeliveryAdress;
    }

    public Account() {
        this.active = false;
    }

    public void activate(){
        active = true;
    }

    public boolean isActive() {
        return active;
    }
}
