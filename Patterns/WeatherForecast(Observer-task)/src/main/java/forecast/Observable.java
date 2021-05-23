package forecast;

import notification.Observer;

public interface Observable {

    public void registerObserver(Observer observer);
    public void unregisterObserver(Observer observer);
    public void notifyObserver();

}
