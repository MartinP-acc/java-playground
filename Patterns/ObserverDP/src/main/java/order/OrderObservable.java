package order;

import notification.OrderObserver;

public interface OrderObservable {

    void registerObserver(OrderObserver observer);
    void unregisterObserver(OrderObserver observer);
    void notifyObserver();

}
