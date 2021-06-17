package testing;

public class Account {

    private boolean active;

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
